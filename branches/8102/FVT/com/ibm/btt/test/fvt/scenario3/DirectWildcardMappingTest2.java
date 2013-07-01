package com.ibm.btt.test.fvt.scenario3;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class DirectWildcardMappingTest2 extends CommonTestCase {
	/**
	 * ICOLL.*.ELE to ICOLL.*.ELE, while the ELE is a collection data
	 */
	@Test
	public void testCollectionDataLeafMapping() {
		try {
			Context source = getContextByName("CollectionDataLeafCtxt");
			Context target = getContextByName("CollectionDataLeafCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("CollectionDataLeafFormat");

			// initialize the source context
			String aValue = "WHATAWORLD", bValue = "WHATTHEHELL", cValue = "", dValue = "髦耋ÃÑ";
			Date aDate = parseStringToDate("2012-12-12"), bDate = parseStringToDate("2013-01-13"), cDate = null, dDate = parseStringToDate("1983-12-01");
			IndexedCollection SrcExtrOneLvlIColl = (IndexedCollection) source.getElementAt("SrcExtrOneLvlIColl");
			KeyedCollection kcoll0 = (KeyedCollection) SrcExtrOneLvlIColl.createElement(true);
			KeyedCollection kcoll1 = (KeyedCollection) SrcExtrOneLvlIColl.createElement(true);
			kcoll0.setDynamic(true);
			kcoll1.setDynamic(true);
			kcoll0.setValueAt("SrcSimpleKColl.SimpleField", aValue);
			kcoll1.setValueAt("SrcSimpleKColl.SimpleField", bValue);
			kcoll0.setValueAt("SrcSimpleKColl.DateField", aDate);
			kcoll1.setValueAt("SrcSimpleKColl.DateField", bDate);
			kcoll0.setValueAt("SrcSimpleIColl.0.SimpleField", cValue);
			kcoll0.setValueAt("SrcSimpleIColl.1.SimpleField", dValue);
			kcoll1.setValueAt("SimpleIColl.0.DateField", "IAMNOTMAPPED,CHECKLATER!");
			kcoll1.setValueAt("SrcSimpleIColl.0.DateField", cDate);
			kcoll1.setValueAt("SrcSimpleIColl.1.DateField", dDate);
			String aAcctId = "A0123456789", bAcctId = "B0123456789";
			byte aAcctType = (byte) 15, bAcctType = (byte) 11;
			BigDecimal aAcctBalance = new BigDecimal("1024.128"), bAcctBalance = new BigDecimal("2048.1024");
			XMLGregorianCalendar aXmlGc = parseStringToXMLGregorianCalendar("1983-01-21"), bXmlGc = parseStringToXMLGregorianCalendar("1983-03-29");
			kcoll0.setValueAt("SrcSimpleTypedRec.acctId", aAcctId);
			kcoll0.setValueAt("SrcSimpleTypedRec.acctType", aAcctType);
			kcoll1.setValueAt("SrcSimpleTypedRec.acctBalance", aAcctBalance);
			kcoll1.setValueAt("SrcSimpleTypedRec.acctOpenDate", aXmlGc);
			kcoll0.setValueAt("SrcSimpleTypedList.0.acctId", bAcctId);
			kcoll1.setValueAt("SrcSimpleTypedList.0.acctBalance", bAcctBalance);
			kcoll0.setValueAt("SrcSimpleTypedList.1.acctType", bAcctType);
			kcoll1.setValueAt("SrcSimpleTypedList.1.acctOpenDate", bXmlGc);
			SrcExtrOneLvlIColl.addElement(kcoll0);
			SrcExtrOneLvlIColl.addElement(kcoll1);

			// mapping the contents defined in format
			fmt.mapContents(source, target);

			// check the mapping result, if source values are not set, the
			// corresponding destination should be null
			Assert.assertEquals(aValue, target.getValueAt("DestExtrOneLvlIColl.0.SimpleKColl.SimpleField"));
			Assert.assertEquals(bValue, target.getValueAt("DestExtrOneLvlIColl.1.SimpleKColl.SimpleField"));
			Assert.assertEquals(aDate, target.getValueAt("DestExtrOneLvlIColl.0.SimpleKColl.DateField"));
			Assert.assertEquals(bDate, target.getValueAt("DestExtrOneLvlIColl.1.SimpleKColl.DateField"));
			Assert.assertEquals(cValue, target.getValueAt("DestExtrOneLvlIColl.0.SimpleIColl.0.SimpleField"));
			// check items that are not included in format definitions
			Assert.assertNull("Source value should NOT be mapped to target, since the item is NOT inclued in format",
					target.getValueAt("DestExtrOneLvlIColl.0.SimpleIColl.0.DateField"));
			Assert.assertEquals(dValue, target.getValueAt("DestExtrOneLvlIColl.0.SimpleIColl.1.SimpleField"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.0.SimpleIColl.0.DateField"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.1.SimpleIColl.0.SimpleField"));
			Assert.assertEquals(cDate, target.getValueAt("DestExtrOneLvlIColl.1.SimpleIColl.0.DateField"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.1.SimpleIColl.1.SimpleField"));
			Assert.assertEquals(dDate, target.getValueAt("DestExtrOneLvlIColl.1.SimpleIColl.1.DateField"));

			Assert.assertEquals(aAcctId, target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedRec.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedRec.acctAlias"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedRec.acctBalance"));
			Assert.assertEquals(aAcctType, target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedRec.acctType"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedRec.acctOpenDate"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedRec.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedRec.acctAlias"));
			Assert.assertEquals(aAcctBalance, target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedRec.acctBalance"));
			Assert.assertEquals((byte) 20, target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedRec.acctType"));
			Assert.assertEquals(aXmlGc, target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedRec.acctOpenDate"));

			Assert.assertEquals(bAcctId, target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.0.acctId"));
			Assert.assertEquals((byte) 20, target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.0.acctType"));
			Assert.assertEquals("SavingCard", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.0.acctAlias"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.0.acctBalance"));
			Assert.assertEquals(bAcctType, target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.1.acctType"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.0.SimpleTypedList.1.acctId"));
			Assert.assertEquals(bAcctBalance, target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedList.0.acctBalance"));
			Assert.assertEquals("SavingCard", target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedList.0.acctAlias"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedList.0.acctOpenDate"));
			Assert.assertEquals(bXmlGc, target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedList.1.acctOpenDate"));
			Assert.assertNull("Source value is NOT set", target.getValueAt("DestExtrOneLvlIColl.1.SimpleTypedList.1.acctBalance"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * ICOLL.*.ICOLL.*.ICOLL.*.ELE to ICOLL.*.ICOLL.*.ICOLL.*.ELE, while the ELE
	 * is a collection data
	 */
	@Test
	public void testThreeLevelICollWithCollectionDataLeafMapping() {
		try {
			Context source = getContextByName("ThreeLevelICollCtxt");
			Context target = getContextByName("ThreeLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("ThreeLevelICollCollectionDataLeafFormat");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			XMLGregorianCalendar aXmlGc = parseStringToXMLGregorianCalendar("1983-01-21"), bXmlGc = parseStringToXMLGregorianCalendar("1983-03-29");
			BigDecimal aAcctBalance = new BigDecimal("1024.128"), bAcctBalance = new BigDecimal("2048.1024");
			byte[] aArray = "ILOVEBTT".getBytes(), bArray = "YOULOVEBTT".getBytes();
			Object aPF = "THINK", bPF = "PAD";
			BigInteger aBI = new BigInteger("1048576"), bBI = new BigInteger("2048576");

			IndexedCollection SrcIColl = (IndexedCollection) source.getElementAt("SrcIColl");
			KeyedCollection k10 = (KeyedCollection) SrcIColl.createElement(false);
			KeyedCollection k11 = (KeyedCollection) SrcIColl.createElement(true);
			k10.setDynamic(true);
			k11.setDynamic(true);
			k10.setValueAt("SrcXMLGCField", aXmlGc);
			IndexedCollection SrcNestIColl0 = (IndexedCollection) k10.getElementAt("SrcNestIColl");
			KeyedCollection k100 = (KeyedCollection) SrcNestIColl0.createElement(true);
			k100.setValueAt("SrcInnerBDField", aAcctBalance);
			k100.setValueAt("SrcInnerBAField", aArray);

			IndexedCollection SrcInnerNestIColl00 = (IndexedCollection) k100.getElementAt("SrcInnerNestIColl");
			KeyedCollection k1000 = (KeyedCollection) SrcInnerNestIColl00.createElement(true);
			KeyedCollection k1001 = (KeyedCollection) SrcInnerNestIColl00.createElement(true);
			k1000.setValueAt("SrcPlainField", aPF);
			k1000.setValueAt("SrcBIField", aBI);
			k1001.setValueAt("SrcPlainField", bPF);
			k1001.setValueAt("SrcBIField", bBI);
			SrcInnerNestIColl00.addElement(k1000);
			SrcInnerNestIColl00.addElement(k1001);

			KeyedCollection k101 = (KeyedCollection) SrcNestIColl0.createElement(true);
			k101.setValueAt("SrcInnerBDField", bAcctBalance);
			k101.setValueAt("SrcInnerBAField", bArray);
			IndexedCollection SrcInnerNestIColl01 = (IndexedCollection) k101.getElementAt("SrcInnerNestIColl");
			KeyedCollection k1010 = (KeyedCollection) SrcInnerNestIColl01.createElement(true);
			KeyedCollection k1011 = (KeyedCollection) SrcInnerNestIColl01.createElement(true);
			k1010.setValueAt("SrcPlainField", aPF);
			k1010.setValueAt("SrcBIField", aBI);
			k1010.setValueAt("SrcStringField", "FT");
			k1011.setValueAt("SrcPlainField", bPF);
			k1011.setValueAt("SrcBIField", bBI);
			SrcInnerNestIColl01.addElement(k1010);
			SrcInnerNestIColl01.addElement(k1011);

			SrcNestIColl0.addElement(k100);
			SrcNestIColl0.addElement(k101);

			BigDecimal aAcctBalanceC = new BigDecimal("10214.128"), bAcctBalanceC = new BigDecimal("20481.1024");
			byte[] aArrayC = "ILOVEBTTD".getBytes(), bArrayC = "YOULOVEBTTE".getBytes();
			Object aPFC = new Date(), bPFC = "CHANGE";
			BigInteger aBIC = new BigInteger("110485761"), bBIC = new BigInteger("120485762");
			k11.setValueAt("SrcXMLGCField", bXmlGc);
			IndexedCollection SrcNestIColl1 = (IndexedCollection) k11.getElementAt("SrcNestIColl");
			KeyedCollection k110 = (KeyedCollection) SrcNestIColl1.createElement(true);
			k110.setValueAt("SrcInnerBDField", aAcctBalanceC);
			k110.setValueAt("SrcInnerBAField", aArrayC);
			IndexedCollection SrcInnerNestIColl10 = (IndexedCollection) k110.getElementAt("SrcInnerNestIColl");
			KeyedCollection k1100 = (KeyedCollection) SrcInnerNestIColl10.createElement(true);
			KeyedCollection k1101 = (KeyedCollection) SrcInnerNestIColl10.createElement(true);
			k1100.setValueAt("SrcPlainField", aPFC);
			k1100.setValueAt("SrcBIField", aBIC);
			k1100.setValueAt("SrcStringField", "CFT");
			k1101.setValueAt("SrcPlainField", bPFC);
			k1101.setValueAt("SrcBIField", bBIC);
			SrcInnerNestIColl10.addElement(k1100);
			SrcInnerNestIColl10.addElement(k1101);

			KeyedCollection k111 = (KeyedCollection) SrcNestIColl1.createElement(true);
			k111.setValueAt("SrcInnerBDField", bAcctBalanceC);
			k111.setValueAt("SrcInnerBAField", bArrayC);
			IndexedCollection SrcInnerNestIColl11 = (IndexedCollection) k111.getElementAt("SrcInnerNestIColl");
			KeyedCollection k1110 = (KeyedCollection) SrcInnerNestIColl11.createElement(true);
			KeyedCollection k1111 = (KeyedCollection) SrcInnerNestIColl11.createElement(true);
			k1110.setValueAt("SrcPlainField", aPFC);
			k1110.setValueAt("SrcBIField", aBIC);
			k1111.setValueAt("SrcPlainField", bPFC);
			k1111.setValueAt("SrcBIField", bBIC);
			SrcInnerNestIColl11.addElement(k1110);
			SrcInnerNestIColl11.addElement(k1111);
			SrcNestIColl1.addElement(k110);
			SrcNestIColl1.addElement(k111);
			SrcIColl.addElement(k10);
			SrcIColl.addElement(k11);
			source.setValueAt("SrcIColl.0.SrcNestIColl.0.SrcInnerNestIColl.0.SrcBDField", aAcctBalance);
			source.setValueAt("SrcIColl.1.SrcNestIColl.1.SrcInnerNestIColl.1.SrcBDField", bAcctBalanceC);
			// items that will not be mapped, but need to be checked in destination
			// data element
			source.setValueAt("SrcIColl.0.SrcStringField", "CHECK_IF_VALUE_IS_MAPPED_TO_TARGET!");
			source.setValueAt("SrcIColl.1.SrcStringField", "CHECK_IF_VALUE_IS_MAPPED_TO_TARGET!");
			source.setValueAt("SrcIColl.0.SrcNestIColl.0.SrcInnerXMLGCField", aXmlGc);
			source.setValueAt("SrcIColl.1.SrcNestIColl.1.SrcInnerXMLGCField", bXmlGc);

			// mapping the source data to target data
			fmt.mapContents(source, target);

			// checking the target values included in the format
			Assert.assertEquals(aXmlGc, target.getValueAt("DestIColl.0.SrcXMLGCField"));
			Assert.assertEquals(aAcctBalance, target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerBDField"));
			String aStr = new String((byte[]) target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerBAField"));
			Assert.assertEquals("ILOVEBTT", aStr);
			Assert.assertEquals(aPF, target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerNestIColl.0.SrcPlainField"));
			Assert.assertEquals(aBI, target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerNestIColl.0.SrcBIField"));
			Assert.assertEquals(bPF, target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerNestIColl.1.SrcPlainField"));
			Assert.assertEquals(bBI, target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerNestIColl.1.SrcBIField"));
			Assert.assertEquals(bAcctBalance, target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerBDField"));
			String bStr = new String((byte[]) target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerBAField"));
			Assert.assertEquals("YOULOVEBTT", bStr);
			Assert.assertEquals(aPF, target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerNestIColl.0.SrcPlainField"));
			Assert.assertEquals(aBI, target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerNestIColl.0.SrcBIField"));
			Assert.assertEquals("FT", target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerNestIColl.0.SrcStringField"));
			Assert.assertEquals(bPF, target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerNestIColl.1.SrcPlainField"));
			Assert.assertEquals(bBI, target.getValueAt("DestIColl.0.SrcNestIColl.1.SrcInnerNestIColl.1.SrcBIField"));

			Assert.assertEquals(bXmlGc, target.getValueAt("DestIColl.1.SrcXMLGCField"));
			Assert.assertEquals(aAcctBalanceC, target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerBDField"));
			String aStrC = new String((byte[]) target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerBAField"));
			Assert.assertEquals("ILOVEBTTD", aStrC);
			Assert.assertEquals(aPFC, target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.0.SrcPlainField"));
			Assert.assertEquals(aBIC, target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.0.SrcBIField"));
			Assert.assertEquals(bPFC, target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.1.SrcPlainField"));
			Assert.assertEquals(bBIC, target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.1.SrcBIField"));
			Assert.assertEquals(bAcctBalanceC, target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerBDField"));
			String bStrC = new String((byte[]) target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerBAField"));
			Assert.assertEquals("YOULOVEBTTE", bStrC);
			Assert.assertEquals(aPFC, target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerNestIColl.0.SrcPlainField"));
			Assert.assertEquals(aBIC, target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerNestIColl.0.SrcBIField"));
			Assert.assertEquals("CFT", target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.0.SrcStringField"));
			Assert.assertEquals(bPFC, target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerNestIColl.1.SrcPlainField"));
			Assert.assertEquals(bBIC, target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerNestIColl.1.SrcBIField"));

			Assert.assertNull("No value set", target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerNestIColl.0.SrcStringField"));
			Assert.assertNull("Not set value", target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerStringField"));
			Assert.assertNull("Not set value", target.getValueAt("DestIColl.1.SrcNestIColl.0.SrcInnerNestIColl.0.SrcXMLGCField"));
			Assert.assertNull("Not set value", target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerNestIColl.1.SrcXMLGCField"));

			Assert.assertNull("Not included in mapping definition", target.getValueAt("DestIColl.0.SrcStringField"));
			Assert.assertNull("Not included in mapping definition", target.getValueAt("DestIColl.1.SrcStringField"));
			Assert.assertNull("Not included in mapping definition",
					target.getValueAt("DestIColl.0.SrcNestIColl.0.SrcInnerXMLGCField"));
			Assert.assertNull("Not included in mapping definition",
					target.getValueAt("DestIColl.1.SrcNestIColl.1.SrcInnerXMLGCField"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * ICOLL.*.ICOLL.*.ICOLL.*.ICOLL.*.ICOLL.*.ELE to
	 * ICOLL.*.ICOLL.*.ICOLL.*.ICOLL.*.ICOLL.*.ELE
	 */
	@Test
	public void testSixLevelDeepMapping() {
		try {
			Context source = getContextByName("SixLevelICollCtxt");
			Context target = getContextByName("SixLevelICollCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FiveLevelFieldDataLeafFormat");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				source.setValueAt("SrcIColl." + i + ".SrcStringField", "SIMPLE_STRING" + i);
				for (int j = 0; j < 2; j++) {
					source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcBDField",
							new BigDecimal(String.valueOf((512 + i) * (128 + j) + 0.8192)));
					for (int k = 0; k < 2; k++) {
						// TODO EXCEPTION HERE(CLONE PROBLEM)
						// source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j +
						// ".SrcL3Kcoll." + k + ".SrcXMLGCField",
						// parseStringToXMLGregorianCalendar("201" + i + "-0" + (j + 1) +
						// "-1" + (k + 1)));
						source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcBIField",
								new BigInteger(String.valueOf(8192 + (i + j + k))));
						for (int ii = 0; ii < 2; ii++) {
							source.setValueAt(
									"SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii + ".SrcBIField",
									new BigInteger(String.valueOf(8192 + (i + j + k + ii))));
							for (int jj = 0; jj < 2; jj++) {
								source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
										+ ".SrcL5IColl." + jj + ".SrcPlainField", String.valueOf(8192 + (i + j + k + ii)));
								for (int kk = 0; kk < 2; kk++) {
									source.setValueAt("SrcIColl." + i + ".SrcL2IColl." + j + ".SrcL3IColl." + k + ".SrcL4IColl." + ii
											+ ".SrcL5IColl." + jj + ".SrcL6IColl." + kk,
											new BigDecimal(String.valueOf((512 + i) * (128 + j) + (k + ii * jj - kk) + 0.8192)));
								}
							}
						}
					}
				}
			}
			// System.out.println(source.getKeyedCollection());
			// mapping the values
			fmt.mapContents(source, target);
			// System.out.println(target.getKeyedCollection());

			// check the values after mapping
			for (int i = 0; i < 2; i++) {
				Assert.assertEquals("SIMPLE_STRING" + i, target.getValueAt("L1IColl." + i + ".StringField"));
				for (int j = 0; j < 2; j++) {
					Assert.assertEquals(new BigDecimal(String.valueOf((512 + i) * (128 + j) + 0.8192)),
							target.getValueAt("L1IColl." + i + ".L2IColl." + j + ".BDField"));
					for (int k = 0; k < 2; k++) {
						Assert.assertEquals(new BigInteger(String.valueOf(8192 + (i + j + k))),
								target.getValueAt("L1IColl." + i + ".L2IColl." + j + ".L3IColl." + k + ".BIField"));
						for (int ii = 0; ii < 2; ii++) {
							Assert.assertEquals(new BigInteger(String.valueOf(8192 + (i + j + k + ii))),
									target.getValueAt("L1IColl." + i + ".L2IColl." + j + ".L3IColl." + k + ".L4IColl." + ii + ".BIField"));
							for (int jj = 0; jj < 2; jj++) {
								Assert.assertEquals(
										String.valueOf(8192 + (i + j + k + ii)),
										target.getValueAt("L1IColl." + i + ".L2IColl." + j + ".L3IColl." + k + ".L4IColl." + ii + ".L5IColl." + jj
												+ ".PlainField"));
								for (int kk = 0; kk < 2; kk++) {
									Assert.assertEquals(
											new BigDecimal(String.valueOf((512 + i) * (128 + j) + (k + ii * jj - kk) + 0.8192)),
											target.getValueAt("L1IColl." + i + ".L2IColl." + j + ".L3IColl." + k + ".L4IColl." + ii + ".L5IColl."
													+ jj + ".L6IColl." + kk));
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
	 * ICOLL.*.*.*.*.*.ELE to ICOLL.*.*.*.*.*.ELE
	 */
	@Test
	public void testFiveDimensionalMatrixMapping() {
		try {
			Context source = getContextByName("FiveDimensionalMatrixCtxt");
			Context target = getContextByName("FiveDimensionalMatrixCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FiveDimensionalMatrixFormat");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 3; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".PlainField", ""
										+ i + j + k + ii + jj);
								source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BIField",
										new BigInteger("8192" + i + j + k + ii + jj));
								source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".XMLGCField",
										parseStringToXMLGregorianCalendar("201" + i + "-0" + (j + 1) + "-1" + (k + 1)));
								source.setValueAt("SrcFiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BAField", (""
										+ i + j + k + ii + jj).getBytes());
								KeyedCollection innerKColl = (KeyedCollection) source.getElementAt("SrcFiveDimensionalMatrix." + i + "." + j
										+ "." + k + "." + ii + "." + jj + ".TypedDataInnerKColl");
								innerKColl.setValueAt("StringField", String.valueOf(i * j * k + ii + jj));
								innerKColl.setValueAt("BIField", new BigInteger(String.valueOf(i * j * k + ii + jj)));
								IndexedCollection innerIColl = (IndexedCollection) source.getElementAt("SrcFiveDimensionalMatrix." + i + "."
										+ j + "." + k + "." + ii + "." + jj + ".TypedDataInnerIColl");
								for (int iii = 0; iii < 2; iii++) {
									KeyedCollection aKColl = (KeyedCollection) innerIColl.createElement(true);
									aKColl.setValueAt("StringField", String.valueOf(i * j * k + ii + jj));
									aKColl.setValueAt("BDField", new BigDecimal(String.valueOf(i * j * k + ii + jj)));
									innerIColl.addElement(aKColl);
								}
							}
						}
					}
				}
			}

			// mapping the values
			fmt.mapContents(source, target);

			// check the values after mapping
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 2; k++) {
						for (int ii = 0; ii < 3; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								// check the data field leaves
								Assert.assertEquals("" + i + j + k + ii + jj,
										target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".PlainField"));
								Assert.assertEquals(new BigInteger("8192" + i + j + k + ii + jj),
										target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BIField"));
								Assert.assertEquals(parseStringToXMLGregorianCalendar("201" + i + "-0" + (j + 1) + "-1" + (k + 1)),
										target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".XMLGCField"));
								Assert.assertEquals(
										("" + i + j + k + ii + jj),
										new String((byte[]) target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "."
												+ jj + ".BAField")));
								Assert.assertNull("Value is not set",
										target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".StringField"));
								Assert.assertNull("Value is not set",
										target.getValueAt("FiveDimensionalMatrix." + i + "." + j + "." + k + "." + ii + "." + jj + ".BDField"));

								// check the keyed collection leaf
								KeyedCollection innerKColl = (KeyedCollection) target.getElementAt("FiveDimensionalMatrix." + i + "." + j + "."
										+ k + "." + ii + "." + jj + ".TypedDataInnerKColl");
								Assert.assertEquals(String.valueOf(i * j * k + ii + jj), innerKColl.getValueAt("StringField"));
								Assert.assertEquals(new BigInteger(String.valueOf(i * j * k + ii + jj)), innerKColl.getValueAt("BIField"));
								Assert.assertNull("Default value is used.", innerKColl.getValueAt("XMLGCField"));
								Assert.assertNull("Default value is used.", innerKColl.getValueAt("BDField"));
								Assert.assertNull("Default value is used.", innerKColl.getValueAt("BAField"));

								// check the indexed collection leaf
								IndexedCollection innerIColl = (IndexedCollection) target.getElementAt("FiveDimensionalMatrix." + i + "." + j
										+ "." + k + "." + ii + "." + jj + ".TypedDataInnerIColl");
								for (int iii = 0; iii < 2; iii++) {
									KeyedCollection aKColl = (KeyedCollection) innerIColl.getElementAt(iii);
									Assert.assertEquals(String.valueOf(i * j * k + ii + jj), aKColl.getValueAt("StringField"));
									Assert.assertEquals(new BigDecimal(String.valueOf(i * j * k + ii + jj)), aKColl.getValueAt("BDField"));
									Assert.assertNull("Default value is used.", aKColl.getValueAt("XMLGCField"));
									Assert.assertNull("Default value is used.", aKColl.getValueAt("BIField"));
									Assert.assertNull("Default value is used.", aKColl.getValueAt("BAField"));
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
}
