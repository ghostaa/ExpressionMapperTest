package com.ibm.btt.test.fvt.scenario4;

import java.util.Date;
import java.util.Iterator;

import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.btt.base.DSEInvalidArgumentException;
import com.ibm.btt.base.DataField;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.base.Settings;

public class CustomTestFunction {
	public static final String IGNORE_EXCEPTION_ENTRY = "globalFunctions.ignoreException";

	// private static BTTLog log =
	// BTTLogFactory.getLog(GlobalFunctions.class.getName());

	private static boolean isIgnoreException() {
		boolean isIgnoreException = false;
		Object retObj = Settings.getSettings().tryGetValueAt(IGNORE_EXCEPTION_ENTRY);
		if (retObj != null) {
			isIgnoreException = Boolean.parseBoolean(retObj.toString());
		}
		return isIgnoreException;
	}

	public static String concatByBar(String string1, String string2) {
		try {
			return (string1 != null) ? (string2 != null ? string1 + "-" + string2 : string1) : string2;
		} catch (RuntimeException re) {
			if (!isIgnoreException()) {
				throw re;
			}
			return null;// return null if exception happen
		}
	}

	public static String concatByBar(String string1, String string2, String string3) {
		try {
			int i = 0;
			if (string1 != null) {
				i = 1;
			}
			if (string2 != null) {
				i |= 2;
			}
			if (string3 != null) {
				i |= 4;
			}
			switch (i) {
			case 1:
				return string1;
			case 2:
				return string2;
			case 3:
				return string1 + "-" + string2;
			case 4:
				return string3;
			case 5:
				return string1 + "-" + string3;
			case 6:
				return string2 + "-" + string3;
			case 7:
				return string1 + "-" + string2 + "-" + string3;
			default:
				return null;
			}
		} catch (RuntimeException re) {
			if (!isIgnoreException()) {
				throw re;
			}
			return null;// return null if exception happen
		}
	}

	public static int sum(int i, int j) {
		try {
			return i + j;
		} catch (RuntimeException re) {
			if (!isIgnoreException()) {
				throw re;
			}
			return -1;// return -1 if exception happen
		}
	}

	public static long compareDateToDay(Date dateFrom, Date dateTo) {
		try {

			long l = dateTo.getTime() - dateFrom.getTime();
			long day = l / (24 * 60 * 60 * 1000);

			return day;
		} catch (RuntimeException re) {
			if (!isIgnoreException()) {
				throw re;
			}
			return -1;// return -1 if exception happen
		}
	}

	/**
	 * 
	 * @Title: compareDateToDay
	 * @Description: TODO
	 * @param XMLCalendarFrom
	 * @param XMLCalendarTo
	 * @return long Mar 14, 2012 2:55:08 PM
	 */
	public static long compareDateToDay(XMLGregorianCalendar XMLCalendarFrom, XMLGregorianCalendar XMLCalendarTo) {
		Date date1 = XMLCalendarFrom.toGregorianCalendar().getTime();
		Date date2 = XMLCalendarTo.toGregorianCalendar().getTime();
		return compareDateToDay(date1, date2);

	}

	/**
	 * 
	 * @Title: getValueInICollKoll
	 * @Description: TODO
	 * @param iColl
	 * @param row
	 * @param columnName
	 * @return
	 * @throws Exception
	 *           Object Mar 14, 2012 2:54:56 PM
	 */
	public static Object getValueInICollKoll(IndexedCollection iColl, int row, String columnName) throws Exception {
		try {
			if (iColl == null) {
				return null;
			}
			if (iColl.tryGetElementAt(row) == null) {
				return null;
			} else {
				Object o = (Object) iColl.tryGetElementAt(row);
				if (o instanceof KeyedCollection) {
					KeyedCollection kColl = (KeyedCollection) o;
					if (kColl.tryGetValueAt(columnName) != null) {
						return kColl.getValueAt(columnName);
					} else {
						return null;
					}
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			if (!isIgnoreException()) {
				throw e;
			}
			return null;// return null if exception happen
		}
	}

	public static IndexedCollection filterICollByKeywords(IndexedCollection iColl) throws Exception {
		String str = "aaa";
		return filterICollByKeywords(iColl, str);
	}

	public static IndexedCollection filterICollByKeywords(IndexedCollection iColl, String str1, String str2) throws Exception {
		filterICollByKeywords(iColl, str1);
		return filterICollByKeywords(iColl, str2);
	}

	public static IndexedCollection filterICollByKeywords(IndexedCollection iColl, Object object) throws Exception {
		return filterICollByKeywords(iColl, object.toString());
	}

	public static IndexedCollection filterICollByKeywords(IndexedCollection iColl, String str) throws Exception {
		try {
			if (iColl == null) {
				return null;
			}

			for (int i = 0; i < iColl.size(); i++) {
				Iterator<?> iterator = iColl.getElements().iterator();
				while (iterator.hasNext()) {
					Object object = iterator.next();
					if (object instanceof KeyedCollection) {
						KeyedCollection subKColl = (KeyedCollection) object;
						subKColl = filterKeyedCollectionByKeywoods(subKColl, str);
					} else if (object instanceof IndexedCollection) {
						IndexedCollection subIColl = (IndexedCollection) object;
						subIColl = filterICollByKeywords(subIColl, str);
					} else if (object instanceof DataField) {
						DataField subDataField = (DataField) object;
						subDataField = filterDataFieldByKeywords(subDataField, str);
					}
				}
			}

			return null;
		} catch (Exception e) {
			if (!isIgnoreException()) {
				throw e;
			}
			return null;// return null if exception happen
		}
	}

	public static KeyedCollection filterKeyedCollectionByKeywoods(KeyedCollection kColl, String str) throws Exception {
		Iterator<?> it2 = kColl.getElements().keySet().iterator();
		while (it2.hasNext()) {
			Object object = kColl.getElementAt((String)it2.next());
			if (object instanceof KeyedCollection) {
				KeyedCollection subKColl = (KeyedCollection) object;
				subKColl = filterKeyedCollectionByKeywoods(subKColl, str);
			} else if (object instanceof IndexedCollection) {
				IndexedCollection subIColl = (IndexedCollection) object;
				subIColl = filterICollByKeywords(subIColl, str);
			} else if (object instanceof DataField) {
				DataField subDataField = (DataField) object;
				subDataField = filterDataFieldByKeywords(subDataField, str);
			}
		}
		return null;
	}

	public static DataField filterDataFieldByKeywords(DataField dataField, String str) throws DSEInvalidArgumentException {
		Object object = dataField.getValue();
		if (object != null) {
			dataField.setValue(dataField.getValue().toString().replace(str, ""));
		}

		return dataField;
	}

}
