package com.ibm.btt.test.fvt.scenario4;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class FunctionWildcardMappingTest1 extends CommonTestCase {

	/**
	 * @Title: testOneLevelIColl
	 * @Description:  Function(ICOLL1.*.DataElement, ICOLL1.*.DataElement)
	 *               void
	 * @throws Mar
	 *           13, 2012 5:31:46 PM
	 */
	@Test
	public void testOneLevelIColl() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			int i1 = 11;
			int i2 = 12;
			XMLGregorianCalendar dateFrom1 = parseStringToXMLGregorianCalendar("2012-12-12");
			XMLGregorianCalendar dateTo1 = parseStringToXMLGregorianCalendar("2012-12-16");
			XMLGregorianCalendar dateFrom2 = parseStringToXMLGregorianCalendar("2012-03-01");
			XMLGregorianCalendar dateTo2 = parseStringToXMLGregorianCalendar("2012-03-31");

			Context from = getContextByName("SrcOneLvlICollKCollCtx");
			Context to = getContextByName("DestOneLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			KeyedCollection kColl1 = (KeyedCollection) iColl.createElement(true);
			kColl1.setValueAt("SrcStringField", stringField1);
			kColl1.setValueAt("SrcIntegerField", i1);
			kColl1.setValueAt("SrcXMLGCFieldFrom", dateFrom1);
			kColl1.setValueAt("SrcXMLGCFieldTo", dateTo1);
			KeyedCollection kColl2 = (KeyedCollection) iColl.createElement(true);
			kColl2.setValueAt("SrcStringField", stringField2);
			kColl2.setValueAt("SrcIntegerField", i2);
			kColl2.setValueAt("SrcXMLGCFieldFrom", dateFrom2);
			kColl2.setValueAt("SrcXMLGCFieldTo", dateTo2);

			iColl.addElement(kColl1);
			iColl.addElement(kColl2);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncOneLevelICollFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-" + stringField1, to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(i1 + i1, to.getValueAt("DestOneLvlIColl.0.IntegerField"));
			Assert.assertEquals("4", to.getValueAt("DestOneLvlIColl.0.PlainField"));
			Assert.assertEquals(stringField2 + "-" + stringField2, to.getValueAt("DestOneLvlIColl.1.StringField"));
			Assert.assertEquals(i2 + i2, to.getValueAt("DestOneLvlIColl.1.IntegerField"));
			Assert.assertEquals("30", to.getValueAt("DestOneLvlIColl.1.PlainField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testOneLevelICollConstant
	 * @Description:  Function(ICOLL.*.DataElement,CONSTANT) void
	 * @throws Mar
	 *           13, 2012 5:32:39 PM
	 */
	@Test
	public void testOneLevelICollConstant() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			int i1 = 11;
			int i2 = 12;
			Context from = getContextByName("SrcOneLvlICollKCollCtx");
			Context to = getContextByName("DestOneLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			KeyedCollection kColl1 = (KeyedCollection) iColl.createElement(true);
			kColl1.setValueAt("SrcStringField", stringField1);
			kColl1.setValueAt("SrcIntegerField", i1);
			KeyedCollection kColl2 = (KeyedCollection) iColl.createElement(true);
			kColl2.setValueAt("SrcStringField", stringField2);
			kColl2.setValueAt("SrcIntegerField", i2);

			iColl.addElement(kColl1);
			iColl.addElement(kColl2);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncOneLevelICollConstantFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-isConstant", to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(i1 + 5, to.getValueAt("DestOneLvlIColl.0.IntegerField"));
			Assert.assertEquals(stringField2 + "-isConstant", to.getValueAt("DestOneLvlIColl.1.StringField"));
			Assert.assertEquals(i2 + 5, to.getValueAt("DestOneLvlIColl.1.IntegerField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testOneLevelICollDataElement
	 * @Description:  Function(ICOLL.*.DataElement, DataElement) void
	 * @throws Mar
	 *           13, 2012 5:41:24 PM
	 */
	@Test
	public void testOneLevelICollDataElement() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			String stringField3 = "fieldString3";
			int i1 = 11;
			int i2 = 12;
			int i3 = 13;
			XMLGregorianCalendar dateFrom1 = parseStringToXMLGregorianCalendar("2012-01-01");
			XMLGregorianCalendar dateFrom2 = parseStringToXMLGregorianCalendar("2012-02-01");
			XMLGregorianCalendar today = parseStringToXMLGregorianCalendar("2012-03-13");

			Context from = getContextByName("SrcOneLvlICollKCollCtx");
			Context to = getContextByName("DestOneLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			KeyedCollection kColl1 = (KeyedCollection) iColl.createElement(true);
			kColl1.setValueAt("SrcStringField", stringField1);
			kColl1.setValueAt("SrcIntegerField", i1);
			kColl1.setValueAt("SrcXMLGCFieldFrom", dateFrom1);
			KeyedCollection kColl2 = (KeyedCollection) iColl.createElement(true);
			kColl2.setValueAt("SrcStringField", stringField2);
			kColl2.setValueAt("SrcIntegerField", i2);
			kColl2.setValueAt("SrcXMLGCFieldFrom", dateFrom2);

			iColl.addElement(kColl1);
			iColl.addElement(kColl2);

			from.setValueAt("SrcStringField", stringField3);
			from.setValueAt("SrcIntegerField", i3);
			from.setValueAt("SrcXMLGCField", today);
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncOneLevelICollDataElementFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-" + stringField3, to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(i1 + i3, to.getValueAt("DestOneLvlIColl.0.IntegerField"));
			Assert.assertEquals("72", to.getValueAt("DestOneLvlIColl.0.PlainField"));
			Assert.assertEquals(stringField2 + "-" + stringField3, to.getValueAt("DestOneLvlIColl.1.StringField"));
			Assert.assertEquals(i2 + i3, to.getValueAt("DestOneLvlIColl.1.IntegerField"));
			Assert.assertEquals("41", to.getValueAt("DestOneLvlIColl.1.PlainField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testTwoLevelIColl
	 * @Description:  Function(ICOLL1.*.ICOLL3.*.DataElement,
	 *               ICOLL1.*.ICOLL3.*.DataElement) void
	 * @throws Mar
	 *           13, 2012 6:38:13 PM
	 */
	@Test
	public void testTwoLevelIColl() {
		try {
			String stringField1 = "fieldString1";
			String stringField11 = "fieldString11";
			String stringField2 = "fieldString2";
			String stringField22 = "fieldString22";
			int i1 = 11;
			int i11 = 111;
			int i2 = 12;
			int i22 = 122;
			XMLGregorianCalendar dateFrom1 = parseStringToXMLGregorianCalendar("2012-12-12");
			XMLGregorianCalendar dateTo1 = parseStringToXMLGregorianCalendar("2012-12-16");
			XMLGregorianCalendar dateFrom11 = parseStringToXMLGregorianCalendar("2012-12-13");
			XMLGregorianCalendar dateTo11 = parseStringToXMLGregorianCalendar("2012-12-16");
			XMLGregorianCalendar dateFrom2 = parseStringToXMLGregorianCalendar("2012-03-01");
			XMLGregorianCalendar dateTo2 = parseStringToXMLGregorianCalendar("2012-03-31");
			XMLGregorianCalendar dateFrom22 = parseStringToXMLGregorianCalendar("2012-03-01");
			XMLGregorianCalendar dateTo22 = parseStringToXMLGregorianCalendar("2012-03-30");

			Context from = getContextByName("SrcTwoLvlICollKCollCtx");
			Context to = getContextByName("DestTwoLvlICollKCollCtx");

			IndexedCollection iCollOne = (IndexedCollection) from.getElementAt("SrcTwoLvlIColl");

			KeyedCollection kCollOne1 = (KeyedCollection) iCollOne.createElement(true);
			IndexedCollection iCollTwo11 = (IndexedCollection) kCollOne1.getElementAt("SrcNestIColl");
			KeyedCollection kCollTwo11 = (KeyedCollection) iCollTwo11.createElement(true);
			kCollTwo11.setValueAt("SrcStringField", stringField1);
			kCollTwo11.setValueAt("SrcIntegerField", i1);
			kCollTwo11.setValueAt("SrcXMLGCFieldFrom", dateFrom1);
			kCollTwo11.setValueAt("SrcXMLGCFieldTo", dateTo1);
			KeyedCollection kCollTwo12 = (KeyedCollection) iCollTwo11.createElement(true);
			kCollTwo12.setValueAt("SrcStringField", stringField11);
			kCollTwo12.setValueAt("SrcIntegerField", i11);
			kCollTwo12.setValueAt("SrcXMLGCFieldFrom", dateFrom11);
			kCollTwo12.setValueAt("SrcXMLGCFieldTo", dateTo11);

			KeyedCollection kCollOne2 = (KeyedCollection) iCollOne.createElement(true);
			IndexedCollection iCollTwo21 = (IndexedCollection) kCollOne2.getElementAt("SrcNestIColl");
			KeyedCollection kCollTwo21 = (KeyedCollection) iCollTwo21.createElement(true);
			kCollTwo21.setValueAt("SrcStringField", stringField2);
			kCollTwo21.setValueAt("SrcIntegerField", i2);
			kCollTwo21.setValueAt("SrcXMLGCFieldFrom", dateFrom2);
			kCollTwo21.setValueAt("SrcXMLGCFieldTo", dateTo2);
			KeyedCollection kCollTwo22 = (KeyedCollection) iCollTwo21.createElement(true);
			kCollTwo22.setValueAt("SrcStringField", stringField22);
			kCollTwo22.setValueAt("SrcIntegerField", i22);
			kCollTwo22.setValueAt("SrcXMLGCFieldFrom", dateFrom22);
			kCollTwo22.setValueAt("SrcXMLGCFieldTo", dateTo22);

			iCollOne.addElement(kCollOne1);
			iCollOne.addElement(kCollOne2);
			iCollTwo11.addElement(kCollTwo11);
			iCollTwo11.addElement(kCollTwo12);

			iCollTwo21.addElement(kCollTwo21);
			iCollTwo21.addElement(kCollTwo22);

			// IndexedCollection iColl=(IndexedCollection)kColl1.getElementAt("");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncTwoLevelICollFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-" + stringField1, to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.StringField"));
			Assert.assertEquals(i1 + i1, to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.IntegerField"));
			Assert.assertEquals("4", to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.PlainField"));
			Assert.assertEquals(stringField11 + "-" + stringField11, to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.StringField"));
			Assert.assertEquals(i11 + i11, to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.IntegerField"));
			Assert.assertEquals("3", to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.PlainField"));

			Assert.assertEquals(stringField2 + "-" + stringField2, to.getValueAt("DestTwoLvlIColl.1.NestIColl.0.StringField"));
			Assert.assertEquals(i2 + i2, to.getValueAt("DestTwoLvlIColl.1.NestIColl.0.IntegerField"));
			Assert.assertEquals("30", to.getValueAt("DestTwoLvlIColl.1.NestIColl.0.PlainField"));
			Assert.assertEquals(stringField22 + "-" + stringField22, to.getValueAt("DestTwoLvlIColl.1.NestIColl.1.StringField"));
			Assert.assertEquals(i22 + i22, to.getValueAt("DestTwoLvlIColl.1.NestIColl.1.IntegerField"));
			Assert.assertEquals("29", to.getValueAt("DestTwoLvlIColl.1.NestIColl.1.PlainField"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testTwoLevelICollConstant
	 * @Description:  Function(ICOLL1.*.ICOLL3.*.DataElement, CONSTANT) void
	 * @throws Mar
	 *           14, 2012 11:21:38 AM
	 */
	@Test
	public void testTwoLevelICollConstant() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			int i1 = 11;
			int i2 = 12;
			Context from = getContextByName("SrcTwoLvlICollKCollCtx");
			Context to = getContextByName("DestTwoLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcTwoLvlIColl");
			KeyedCollection kColla = (KeyedCollection) iColl.createElement(true);
			IndexedCollection iColla = (IndexedCollection) kColla.getElementAt("SrcNestIColl");
			KeyedCollection kColla1 = (KeyedCollection) iColla.createElement(true);
			kColla1.setValueAt("SrcStringField", stringField1);
			kColla1.setValueAt("SrcIntegerField", i1);
			KeyedCollection kColla2 = (KeyedCollection) iColla.createElement(true);
			kColla2.setValueAt("SrcStringField", stringField2);
			kColla2.setValueAt("SrcIntegerField", i2);
			iColl.addElement(kColla);
			iColla.addElement(kColla1);
			iColla.addElement(kColla2);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncTwoLevelICollConstantFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-isConstant", to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.StringField"));
			Assert.assertEquals(i1 + 5, to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.IntegerField"));
			Assert.assertEquals(stringField2 + "-isConstant", to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.StringField"));
			Assert.assertEquals(i2 + 5, to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.IntegerField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testTwoLevelICollDataElement
	 * @Description:  Function(ICOLL1.*.ICOLL3.*.DataElement, DataElement)
	 *               void
	 * @throws Mar
	 *           14, 2012 11:34:31 AM
	 */
	@Test
	public void testTwoLevelICollDataElement() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			String stringField3 = "fieldString3";
			int i1 = 11;
			int i2 = 12;
			int i3 = 13;
			XMLGregorianCalendar dateFrom1 = parseStringToXMLGregorianCalendar("2012-01-01");
			XMLGregorianCalendar dateFrom2 = parseStringToXMLGregorianCalendar("2012-02-01");
			XMLGregorianCalendar today = parseStringToXMLGregorianCalendar("2012-03-13");

			Context from = getContextByName("SrcTwoLvlICollKCollCtx");
			Context to = getContextByName("DestTwoLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcTwoLvlIColl");
			KeyedCollection kColla = (KeyedCollection) iColl.createElement(true);
			IndexedCollection iColla1 = (IndexedCollection) kColla.getElementAt("SrcNestIColl");
			KeyedCollection kColla1 = (KeyedCollection) iColla1.createElement(true);

			kColla1.setValueAt("SrcStringField", stringField1);
			kColla1.setValueAt("SrcIntegerField", i1);
			kColla1.setValueAt("SrcXMLGCFieldFrom", dateFrom1);
			KeyedCollection kColla2 = (KeyedCollection) iColla1.createElement(true);
			kColla2.setValueAt("SrcStringField", stringField2);
			kColla2.setValueAt("SrcIntegerField", i2);
			kColla2.setValueAt("SrcXMLGCFieldFrom", dateFrom2);

			iColl.addElement(kColla);
			iColla1.addElement(kColla1);
			iColla1.addElement(kColla2);

			from.setValueAt("SrcStringField", stringField3);
			from.setValueAt("SrcIntegerField", i3);
			from.setValueAt("SrcXMLGCField", today);
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncTwoLevelICollDataElementFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-" + stringField3, to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.StringField"));
			Assert.assertEquals(i1 + i3, to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.IntegerField"));
			Assert.assertEquals("72", to.getValueAt("DestTwoLvlIColl.0.NestIColl.0.PlainField"));
			Assert.assertEquals(stringField2 + "-" + stringField3, to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.StringField"));
			Assert.assertEquals(i2 + i3, to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.IntegerField"));
			Assert.assertEquals("41", to.getValueAt("DestTwoLvlIColl.0.NestIColl.1.PlainField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testOneLevelICollFuncOneLevelIColl
	 * @Description:  Function(ICOLL1.*.DataElement,
	 *               Function(ICOLL1.*.DataElement,ICOLL1.*.DataElement)) void
	 * @throws Mar
	 *           14, 2012 1:22:37 PM
	 */
	@Test
	public void testOneLevelICollFuncOneLevelIColl() {
		try {
			String stringField1 = "fieldString1";
			String stringField2 = "fieldString2";
			int i1 = 11;
			int i2 = 12;

			Context from = getContextByName("SrcOneLvlICollKCollCtx");
			Context to = getContextByName("DestOneLvlICollKCollCtx");

			IndexedCollection iColl = (IndexedCollection) from.getElementAt("SrcOneLvlIColl");
			KeyedCollection kColl1 = (KeyedCollection) iColl.createElement(true);
			kColl1.setValueAt("SrcStringField", stringField1);
			kColl1.setValueAt("SrcIntegerField", i1);
			KeyedCollection kColl2 = (KeyedCollection) iColl.createElement(true);
			kColl2.setValueAt("SrcStringField", stringField2);
			kColl2.setValueAt("SrcIntegerField", i2);

			iColl.addElement(kColl1);
			iColl.addElement(kColl2);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncOneLevelICollFuncOneLevelICollFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1 + "-" + stringField1 + "-" + stringField1,
					to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(i1 + i1 + i1, to.getValueAt("DestOneLvlIColl.0.IntegerField"));
			Assert.assertEquals(stringField2 + "-" + stringField2 + "-" + stringField2,
					to.getValueAt("DestOneLvlIColl.1.StringField"));
			Assert.assertEquals(i2 + i2 + i2, to.getValueAt("DestOneLvlIColl.1.IntegerField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testTwoLevelICollFuncOneLevel
	 * @Description:  Fucntion(ICOLL*.DataElemet) void
	 * @throws Mar
	 *           14, 2012 2:53:23 PM
	 */
	@Test
	public void testTwoLevelICollFuncOneLevel() {
		try {
			String stringField1 = "fieldString1";
			int i1 = 11;

			Context from = getContextByName("SrcTwoLvlICollKCollCtx");
			Context to = getContextByName("DestOneLvlICollKCollCtx");

			IndexedCollection iColla = (IndexedCollection) from.getElementAt("SrcTwoLvlIColl");
			KeyedCollection kColla = (KeyedCollection) iColla.createElement(true);
			IndexedCollection iCollaa = (IndexedCollection) kColla.getElementAt("SrcNestIColl");
			KeyedCollection kCollaa = (KeyedCollection) iCollaa.createElement(true);
			kCollaa.setValueAt("SrcStringField", stringField1);
			kCollaa.setValueAt("SrcIntegerField", i1);
			iColla.addElement(kColla);
			iCollaa.addElement(kCollaa);

			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("FuncTwoLevelICollFuncOneLevelFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(stringField1, to.getValueAt("DestOneLvlIColl.0.StringField"));
			Assert.assertEquals(i1, to.getValueAt("DestOneLvlIColl.0.IntegerField"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	/**
	 * 
	 * @Title: testFuncFiveLevelDeepMapping
	 * @Description: 
	 *               Function(ICOLL1.*.ICOLL2.*.ICOLL3.*.ICOLL4.*.ICOLL5.*.DataElement
	 *               , ICOLL1.*.ICOLL2.*.ICOLL3.*.ICOLL4.*.ICOLL5.*.DataElement)
	 *               void Mar 15, 2012 10:44:52 AM
	 */
	@Test
	public void testFuncFiveLevelDeepMapping() {
		try {
			String srcPlainField = "testSrcPlainField";
			Context source = getContextByName("SixLevelICollCtxt");
			Context target = getContextByName("SixLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FuncFiveLevelICollDataElementFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
										+ ".SrcL5IColl." + jj + ".SrcPlainField", srcPlainField);

							}
						}
					}
				}
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								Assert.assertEquals(
										srcPlainField + "-" + srcPlainField,
										target.getValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
												+ ".SrcL5IColl." + jj + ".SrcPlainField"));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testFuncSixLevelDeepMapping
	 * @Description: 
	 *               Function(ICOLL1.*.ICOLL2.*.ICOLL3.*.ICOLL4.*.ICOLL5.*.ICOLL6
	 *               .*.DataElement, CONSTANT) void Mar 15, 2012 10:45:25 AM
	 */
	@Test
	public void testFuncSixLevelDeepMapping() {
		try {
			BigDecimal srcBDField = new BigDecimal(123);
			Context source = getContextByName("SixLevelICollCtxt");
			Context target = getContextByName("SixLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FuncSixLevelICollDataElementFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								for (int kk = 0; kk < 2; kk++) {
									source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
											+ ".SrcL5IColl." + jj + ".SrcL6IColl." + kk, srcBDField);
								}
							}
						}
					}
				}
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								for (int kk = 0; kk < 2; kk++) {
									Assert.assertEquals(
											new BigDecimal("128.000000000"),
											target.getValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
													+ ".SrcL5IColl." + jj + ".SrcL6IColl." + kk));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testFuncFourLevelDeepMapping
	 * @Description:  Function(ICOLL1.*.ICOLL3.*.DataElement, DataElement)
	 *               void Mar 15, 2012 1:37:36 PM
	 */
	@Test
	public void testFuncFourLevelDeepMapping() {
		try {
			BigInteger bigInteger = new BigInteger("123");
			Context source = getContextByName("SixLevelICollCtxt");
			Context target = getContextByName("SixLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FuncFourLevelICollDataElementFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				source.setValueAt("SrcIntegerField", 123);
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							source.setValueAt(
									"SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii + ".SrcBIField", bigInteger);
						}
					}
				}
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 2; ii++) {
							Assert.assertEquals(
									new BigInteger("246"),
									target.getValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
											+ ".SrcBIField"));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

}
