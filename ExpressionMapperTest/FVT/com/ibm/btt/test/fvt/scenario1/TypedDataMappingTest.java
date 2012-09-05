package com.ibm.btt.test.fvt.scenario1;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class TypedDataMappingTest extends CommonTestCase {

	/**
	 * Enable log verification
	 */
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	/**
	 * set the default trace level to ERROR
	 */
	protected void setDefaultTraceLevel() {
		this.setTraceLevel(TestingVerificationLogImpl.INFO);
	}

	/**
	 * source and target have same type definitions including the parameters of
	 * data and type
	 */
	@Test
	public void testMappingBtwnDataOfSameTypeAndParams() {
		try {
			Context source = getContextByName("TypeDataCtxt");
			Context target = getContextByName("TypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("TypeDataDefFmt1");

			// initialize related data in source context
			Date theDate = parseStringToDate("2006-07-18");
			source.setValueAt("mixedRec.dateMinus", theDate);
			BigDecimal bdVal = new BigDecimal("1048576.8192").setScale(3, BigDecimal.ROUND_HALF_UP);
			source.setValueAt("mixedRec.numberData.deepNumberData.numberDP3", bdVal);

			// mapping the source to target context
			fmt.mapContents(source, target);

			// checking the mapped values
			Assert.assertEquals(theDate, target.getValueAt("mixedRec.dateMinus"));
			Assert.assertEquals(bdVal, target.getValueAt("mixedRec.numberData.deepNumberData.numberDP3"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 1 source and target have same type definitions, but the parameters of data
	 * are different<br>
	 * 2 type mismatch mapping
	 */
	@Test
	public void testMappingBtwnDataOfSameTypeWithParamsDifferent() {
		try {
			Context source = getContextByName("TypeDataCtxt");
			Context target = getContextByName("TypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("TypeDataDefFmt2");

			// initialize related data in source context
			Date theDate = parseStringToDate("2006-07-18");
			source.setValueAt("mixedRec.dateMinus", theDate);
			BigDecimal bdVal = new BigDecimal("1048576.81921048576").setScale(10, BigDecimal.ROUND_HALF_UP);
			source.setValueAt("mixedRec.numberData.numberDP9", bdVal);

			// mapping the source to target context
			fmt.mapContents(source, target);

			// checking the mapped values
			Assert.assertEquals(theDate, target.getValueAt("dateSlash"));
			Assert.assertEquals(bdVal, target.getValueAt("mixedRec.numberData.deepNumberData.numberDP3"));

			DataMapperExpressionConverterFormat selfFmt = getFormatByName("TypeDataDefFmt3");

			selfFmt.mapContents(target, target);
			Assert.assertEquals("ONLY value is mapped from source element, the parameters should not be mapped.", "07/18/2006",
					target.getValueAt("str1"));
			Assert.assertEquals("ONLY value is mapped from source element, the parameters should not be mapped.", "1,04,85,76.819",
					target.getValueAt("str2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}


	/**
	 * from KCOLL to KCOLL mapping (full auto match dataName)
	 */
	@Test
	public void testMappingOfKCollTypedData() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("KeyedCollectionTypeDataDefFmt");

			// initialize source values
			source.setValueAt("KCOLLDATA.ccAcctData.acctId", "CC41022519830915583700");
			source.setValueAt("KCOLLDATA.ccAcctData.acctName", "髦耋");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBillDate", billDate);
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias", "髦耋ÃÑ");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance", ccVBalance);
			source.setValueAt("KCOLLDATA.svAcctData.acctId", "SV4102251983091505680X");
			source.setValueAt("KCOLLDATA.svAcctData.acctAlias", "副卡");
			BigDecimal svBalance = new BigDecimal("5242880.8192");
			source.setValueAt("KCOLLDATA.svAcctData.acctBalance", svBalance);
			XMLGregorianCalendar openDate = parseStringToXMLGregorianCalendar("2012-12-12");
			source.setValueAt("KCOLLDATA.svAcctData.acctOpenDate", openDate);
			// remove the target default values
			target.setValueAt("KCOLLDATA.ccAcctData.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.svAcctData.acctType", Byte.MIN_VALUE);
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctId"));
			Assert.assertEquals("MainCard", target.getValueAt("KCOLLDATA.ccAcctData.acctName"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId"));
			Assert.assertEquals("ViceCard", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.svAcctData.acctType"));

			// mapping the values
			fmt.mapContents(source, target);

			// checking values after the mapping
			Assert.assertEquals("CC41022519830915583700", target.getValueAt("KCOLLDATA.ccAcctData.acctId"));
			Assert.assertEquals("髦耋", target.getValueAt("KCOLLDATA.ccAcctData.acctName"));
			Assert.assertEquals(ccMBalance, target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertEquals(billDate, target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertEquals("CC41022519830915583701", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId"));
			Assert.assertEquals("髦耋ÃÑ", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertEquals(ccVBalance, target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance"));
			Assert.assertEquals("SV4102251983091505680X", target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("副卡", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertEquals(svBalance, target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertEquals(openDate, target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("KCOLLDATA.ccAcctData.acctType"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
			Assert.assertEquals(new Byte((byte) 20), target.getValueAt("KCOLLDATA.svAcctData.acctType"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * from KCOLL to KCOLL mapping (part auto match dataName)
	 */
	@Test
	public void testMappingOfKCollTypedDataWithCrossFmt() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("KeyedCollectionTypeDataDefCrossMatchFmt");

			// initialize source values
			source.setValueAt("KCOLLDATA.ccAcctData.acctId", "CC41022519830915583700");
			source.setValueAt("KCOLLDATA.ccAcctData.acctName", "髦耋");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBillDate", billDate);
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias", "髦耋ÃÑ");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance", ccVBalance);
			source.setValueAt("KCOLLDATA.svAcctData.acctId", "SV4102251983091505680X");
			source.setValueAt("KCOLLDATA.svAcctData.acctAlias", "副卡");
			BigDecimal svBalance = new BigDecimal("5242880.8192");
			source.setValueAt("KCOLLDATA.svAcctData.acctBalance", svBalance);
			XMLGregorianCalendar openDate = parseStringToXMLGregorianCalendar("2012-12-12");
			source.setValueAt("KCOLLDATA.svAcctData.acctOpenDate", openDate);
			// remove the target default values
			target.setValueAt("KCOLLDATA.ccAcctData.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.svAcctData.acctType", Byte.MIN_VALUE);
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctId"));
			Assert.assertEquals("MainCard", target.getValueAt("KCOLLDATA.ccAcctData.acctName"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId"));
			Assert.assertEquals("ViceCard", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.svAcctData.acctType"));

			// mapping the values
			fmt.mapContents(source, target);

			// checking values after the mapping
			Assert.assertEquals("SV4102251983091505680X", target.getValueAt("KCOLLDATA.ccAcctData.acctId"));
			// default value is used
			Assert.assertEquals("MainCard", target.getValueAt("KCOLLDATA.ccAcctData.acctName"));
			Assert.assertEquals(svBalance, target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertNull("default value is used", target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertNull("default value is used", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId"));
			Assert.assertEquals("ViceCard", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertNull("default value is used", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance"));
			Assert.assertEquals("CC41022519830915583700", target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertEquals(ccMBalance, target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertNull("default value is used", target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));
			Assert.assertEquals(new Byte((byte) 20), target.getValueAt("KCOLLDATA.ccAcctData.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * from detailed fields to detailed fields (detail specified)
	 */
	@Test
	public void testMappingOfKCollTypedDataWithCrossDetailedFmt() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("KeyedCollectionTypeDataDefCrossMatchDetailedFmt");

			// initialize source values
			source.setValueAt("KCOLLDATA.ccAcctData.acctId", "CC41022519830915583700");
			source.setValueAt("KCOLLDATA.ccAcctData.acctName", "髦耋");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBillDate", billDate);
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias", "髦耋ÃÑ");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance", ccVBalance);
			source.setValueAt("KCOLLDATA.svAcctData.acctId", "SV4102251983091505680X");
			source.setValueAt("KCOLLDATA.svAcctData.acctAlias", "副卡");
			BigDecimal svBalance = new BigDecimal("5242880.8192");
			source.setValueAt("KCOLLDATA.svAcctData.acctBalance", svBalance);
			XMLGregorianCalendar openDate = parseStringToXMLGregorianCalendar("2012-12-12");
			source.setValueAt("KCOLLDATA.svAcctData.acctOpenDate", openDate);
			// remove the target default values
			target.setValueAt("KCOLLDATA.ccAcctData.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType", Byte.MIN_VALUE);
			target.setValueAt("KCOLLDATA.svAcctData.acctType", Byte.MIN_VALUE);
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctId"));
			Assert.assertEquals("MainCard", target.getValueAt("KCOLLDATA.ccAcctData.acctName"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId"));
			Assert.assertEquals("ViceCard", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("SavingCard", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertNull(target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("KCOLLDATA.svAcctData.acctType"));

			// mapping the values
			fmt.mapContents(source, target);

			// checking values after the mapping
			Assert.assertEquals("CC41022519830915583700", target.getValueAt("KCOLLDATA.svAcctData.acctId"));
			Assert.assertEquals("髦耋ÃÑ", target.getValueAt("KCOLLDATA.svAcctData.acctAlias"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("KCOLLDATA.svAcctData.acctType"));
			Assert.assertEquals(ccMBalance, target.getValueAt("KCOLLDATA.svAcctData.acctBalance"));
			Assert.assertEquals(billDate, target.getValueAt("KCOLLDATA.svAcctData.acctOpenDate"));

			Assert.assertEquals("副卡", target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias"));
			Assert.assertEquals(openDate, target.getValueAt("KCOLLDATA.ccAcctData.acctBillDate"));
			Assert.assertEquals(svBalance, target.getValueAt("KCOLLDATA.ccAcctData.acctBalance"));
			Assert.assertEquals(new Byte((byte) 20), target.getValueAt("KCOLLDATA.ccAcctData.viceAcct.acctType"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * from ICOLL to ICOLL mapping (full auto match dataName)
	 */
	@Test
	public void testMappingOfICollTypedData() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("IndexedCollectionTypeDataDefFmt");

			// initialize source values
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctId", "CC41022519830915583700");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctName", "髦耋φΤσΣξΞÉËåæ");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctBillDate", billDate);
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctAlias", "髦耋ÃÑφΤσΣξ");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctBalance", ccVBalance);
			target.setValueAt("ICOLLDATA.ccAcctList.0.acctType", Byte.MIN_VALUE);
			target.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.ccAcctList.1.acctId", "CC41022519830915583800");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctName", "髦耋φΤσΣξΞÉËåæóöØÐÍÏìï");
			BigDecimal ccMBalance1 = new BigDecimal("2048576.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctBalance", ccMBalance1);
			XMLGregorianCalendar billDate1 = parseStringToXMLGregorianCalendar("2010-12-13");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctBillDate", billDate1);
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctId", "CC41022519830915583801");
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctAlias", "髦耋ÃÑñãζηθκιλμωΩΨ");
			BigDecimal ccVBalance1 = new BigDecimal("9096.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctBalance", ccVBalance1);
			target.setValueAt("ICOLLDATA.ccAcctList.1.acctType", Byte.MIN_VALUE);
			target.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.svAcctList.0.acctId", "SV4102251983091505680X");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctAlias", "髦耋ÃÑñãζηθκιλμωΩΨφΤσΣξΞ");
			BigDecimal svBalance = new BigDecimal("1242880.8192");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctBalance", svBalance);
			XMLGregorianCalendar openDate = parseStringToXMLGregorianCalendar("2012-12-12");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctOpenDate", openDate);
			target.setValueAt("ICOLLDATA.svAcctList.0.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.svAcctList.1.acctId", "SV4102251983091505680X");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctAlias", "髦耋ÃΨφΤσΣξΞÉËåæóöØÐÍÏìï");
			BigDecimal svBalance1 = new BigDecimal("5242880.8192");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctBalance", svBalance1);
			XMLGregorianCalendar openDate1 = parseStringToXMLGregorianCalendar("2012-12-14");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctOpenDate", openDate1);
			target.setValueAt("ICOLLDATA.svAcctList.1.acctType", Byte.MIN_VALUE);

			// mapping the source to target
			fmt.mapContents(source, target);

			// verify the target values by dataNames
			Assert.assertEquals("CC41022519830915583700", target.getValueAt("ICOLLDATA.ccAcctList.0.acctId"));
			Assert.assertEquals("髦耋φΤσΣξΞÉËåæ", target.getValueAt("ICOLLDATA.ccAcctList.0.acctName"));
			Assert.assertEquals(ccMBalance, target.getValueAt("ICOLLDATA.ccAcctList.0.acctBalance"));
			Assert.assertEquals(billDate, target.getValueAt("ICOLLDATA.ccAcctList.0.acctBillDate"));
			Assert.assertEquals("CC41022519830915583701", target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctId"));
			Assert.assertEquals("髦耋ÃÑφΤσΣξ", target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctAlias"));
			Assert.assertEquals(ccVBalance, target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctBalance"));

			Assert.assertEquals("CC41022519830915583800", target.getValueAt("ICOLLDATA.ccAcctList.1.acctId"));
			Assert.assertEquals("髦耋φΤσΣξΞÉËåæóöØÐÍÏìï", target.getValueAt("ICOLLDATA.ccAcctList.1.acctName"));
			Assert.assertEquals(ccMBalance1, target.getValueAt("ICOLLDATA.ccAcctList.1.acctBalance"));
			Assert.assertEquals(billDate1, target.getValueAt("ICOLLDATA.ccAcctList.1.acctBillDate"));
			Assert.assertEquals("CC41022519830915583801", target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctId"));
			Assert.assertEquals("髦耋ÃÑñãζηθκιλμωΩΨ", target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctAlias"));
			Assert.assertEquals(ccVBalance1, target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctBalance"));

			Assert.assertEquals("SV4102251983091505680X", target.getValueAt("ICOLLDATA.svAcctList.0.acctId"));
			Assert.assertEquals("髦耋ÃÑñãζηθκιλμωΩΨφΤσΣξΞ", target.getValueAt("ICOLLDATA.svAcctList.0.acctAlias"));
			Assert.assertEquals(svBalance, target.getValueAt("ICOLLDATA.svAcctList.0.acctBalance"));
			Assert.assertEquals(openDate, target.getValueAt("ICOLLDATA.svAcctList.0.acctOpenDate"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("ICOLLDATA.ccAcctList.0.acctType"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctType"));
			Assert.assertEquals(new Byte((byte) 20), target.getValueAt("ICOLLDATA.svAcctList.0.acctType"));
			Assert.assertEquals("SV4102251983091505680X", target.getValueAt("ICOLLDATA.svAcctList.1.acctId"));
			Assert.assertEquals("髦耋ÃΨφΤσΣξΞÉËåæóöØÐÍÏìï", target.getValueAt("ICOLLDATA.svAcctList.1.acctAlias"));
			Assert.assertEquals(svBalance1, target.getValueAt("ICOLLDATA.svAcctList.1.acctBalance"));
			Assert.assertEquals(openDate1, target.getValueAt("ICOLLDATA.svAcctList.1.acctOpenDate"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("ICOLLDATA.ccAcctList.1.acctType"));
			Assert.assertEquals(new Byte((byte) 10), target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctType"));
			Assert.assertEquals(new Byte((byte) 20), target.getValueAt("ICOLLDATA.svAcctList.1.acctType"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * from ICOLL to ICOLL mapping<br>
	 * from detailed fields to detailed fields (detail specified)
	 */
	@Test
	public void testMappingOfICollTypedDataWithCrossDetailedFmt() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("IndexedCollectionTypeDataDefCrossMatchFmt");

			// initialize source values
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctId", "CC41022519830915583700");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctName", "髦耋φΤσΣξΞÉËåæ");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("ICOLLDATA.ccAcctList.0.acctBillDate", billDate);
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctAlias", "髦耋ÃÑφΤσΣξ");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctBalance", ccVBalance);
			target.setValueAt("ICOLLDATA.ccAcctList.0.acctType", Byte.MIN_VALUE);
			target.setValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.ccAcctList.1.acctId", "CC41022519830915583800");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctName", "髦耋φΤσΣξΞÉËåæóöØÐÍÏìï");
			BigDecimal ccMBalance1 = new BigDecimal("2048576.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctBalance", ccMBalance1);
			XMLGregorianCalendar billDate1 = parseStringToXMLGregorianCalendar("2010-12-13");
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctBillDate", billDate1);
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctId", "CC41022519830915583801");
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctAlias", "髦耋ÃÑñãζηθκιλμωΩΨ");
			BigDecimal ccVBalance1 = new BigDecimal("9096.8192");
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctBalance", ccVBalance1);
			source.setValueAt("ICOLLDATA.ccAcctList.1.acctType", Byte.MIN_VALUE);
			System.out.println("================" + target.getValueAt("ICOLLDATA.ccAcctList.1.acctType").getClass());
			source.setValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.svAcctList.0.acctId", "SV4102251983091505680X");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctAlias", "髦耋ÃÑñãζηθκιλμωΩΨφΤσΣξΞ");
			BigDecimal svBalance = new BigDecimal("1242880.8192");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctBalance", svBalance);
			XMLGregorianCalendar openDate = parseStringToXMLGregorianCalendar("2012-12-12");
			source.setValueAt("ICOLLDATA.svAcctList.0.acctOpenDate", openDate);
			source.setValueAt("ICOLLDATA.svAcctList.0.acctType", Byte.MIN_VALUE);

			source.setValueAt("ICOLLDATA.svAcctList.1.acctId", "SV4102251983091505680X");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctAlias", "髦耋ÃΨφΤσΣξΞÉËåæóöØÐÍÏìï");
			BigDecimal svBalance1 = new BigDecimal("5242880.8192");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctBalance", svBalance1);
			XMLGregorianCalendar openDate1 = parseStringToXMLGregorianCalendar("2012-12-14");
			source.setValueAt("ICOLLDATA.svAcctList.1.acctOpenDate", openDate1);
			source.setValueAt("ICOLLDATA.svAcctList.1.acctType", Byte.MIN_VALUE);

			// mapping the source to target
			fmt.mapContents(source, target);
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.0.acctId"),
					target.getValueAt("ICOLLDATA.svAcctList.0.acctId"));
			Assert.assertEquals("CC41022519830915583700", target.getValueAt("ICOLLDATA.svAcctList.0.acctId"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctAlias"),
					target.getValueAt("ICOLLDATA.svAcctList.0.acctAlias"));
			Assert.assertEquals("髦耋ÃÑφΤσΣξ", target.getValueAt("ICOLLDATA.svAcctList.0.acctAlias"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.1.acctType").getClass(),
					target.getValueAt("ICOLLDATA.svAcctList.0.acctType").getClass());
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.1.acctType"),
					target.getValueAt("ICOLLDATA.svAcctList.0.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("ICOLLDATA.svAcctList.0.acctType"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.1.acctBalance"),
					target.getValueAt("ICOLLDATA.svAcctList.1.acctBalance"));
			Assert.assertEquals(ccMBalance1, target.getValueAt("ICOLLDATA.svAcctList.1.acctBalance"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.ccAcctList.1.acctBillDate"),
					target.getValueAt("ICOLLDATA.svAcctList.1.acctOpenDate"));
			Assert.assertEquals(billDate1, target.getValueAt("ICOLLDATA.svAcctList.1.acctOpenDate"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.svAcctList.0.acctAlias"),
					target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctAlias"));
			Assert.assertEquals("髦耋ÃÑñãζηθκιλμωΩΨφΤσΣξΞ", target.getValueAt("ICOLLDATA.ccAcctList.1.viceAcct.acctAlias"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.svAcctList.0.acctOpenDate"),
					target.getValueAt("ICOLLDATA.ccAcctList.1.acctBillDate"));
			Assert.assertEquals(openDate, target.getValueAt("ICOLLDATA.ccAcctList.1.acctBillDate"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.svAcctList.1.acctBalance"),
					target.getValueAt("ICOLLDATA.ccAcctList.0.acctBalance"));
			Assert.assertEquals(svBalance1, target.getValueAt("ICOLLDATA.ccAcctList.0.acctBalance"));
			Assert.assertEquals(source.getValueAt("ICOLLDATA.svAcctList.1.acctType"),
					target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctType"));
			Assert.assertEquals(Byte.MIN_VALUE, target.getValueAt("ICOLLDATA.ccAcctList.0.viceAcct.acctType"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

}
