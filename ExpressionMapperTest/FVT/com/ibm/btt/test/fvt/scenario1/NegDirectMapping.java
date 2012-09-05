package com.ibm.btt.test.fvt.scenario1;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegDirectMapping extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}
	
	/**
	 * the form attribute is not used
	 */
	@Test
	public void testMappingBtwnDataOfSameTypeWithErrorFmt() {
		try {
			Context source = getContextByName("TypeDataCtxt");
			Context target = getContextByName("TypeDataCtxt");

			// initialize related data in source context
			BigDecimal bdVal = new BigDecimal("1048576.8192").setScale(3, BigDecimal.ROUND_HALF_UP);
			source.setValueAt("mixedRec.numberData.deepNumberData.numberDP3", bdVal);
			DataMapperExpressionConverterFormat errFmt = getFormatByName("TypeDataDefFmtExpressionError");
			errFmt.mapContents(source, target);
			String logContents = getLogContents();
			String funcVp = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'mixedRec.numberData.deepNumberData.numberDP3'.";
			String mapperVp = "<map fromExpression=\"mixedRec.numberData.deepNumberData.numberDP3\" to=\"mixedRec.numberData.deepNumberData.numberDP3\" />";
			Assert.assertTrue("Functional error reported", logContents.indexOf(funcVp) > -1);
			Assert.assertTrue("DataMapperExpressionConverterFormat execution error reported", logContents.indexOf(mapperVp) > 0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
	
	/**
	 * Data element type miss match mapping
	 */
	@Test
	public void testDataEleMissMatchMapping1() {
		try {
			Context source = getContextByName("TypeDataCtxt");
			Context target = getContextByName("TypeDataCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_DataEleTypeMissMatchFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'dateMinus' with element type 'DataField' to 'numberData' with element type 'KeyedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"mixedRec.dateMinus\" to=\"mixedRec.numberData\" />] from context [TypeDataCtxt] to [TypeDataCtxt]:From data element [dateMinus](DataField) is not match with the To data element [numberData](KeyedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * mapping typed data to a different typed data
	 */
	@Test
	public void testDataEleMissMatchMapping2() {
		try {
			Context source = getContextByName("CollectionTypeDataCtxt");
			Context target = getContextByName("CollectionTypeDataCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_DataEleTypeMissMatchFmt2");

			// initialize source values
			source.setValueAt("KCOLLDATA.ccAcctData.acctId", "CC41022519830915583700");
			source.setValueAt("KCOLLDATA.ccAcctData.acctName", "é«¦è€‹");
			BigDecimal ccMBalance = new BigDecimal("1048576.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBalance", ccMBalance);
			XMLGregorianCalendar billDate = parseStringToXMLGregorianCalendar("2010-12-12");
			source.setValueAt("KCOLLDATA.ccAcctData.acctBillDate", billDate);
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctId", "CC41022519830915583701");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctAlias", "é«¦è€‹ÃƒÃ‘");
			BigDecimal ccVBalance = new BigDecimal("4096.8192");
			source.setValueAt("KCOLLDATA.ccAcctData.viceAcct.acctBalance", ccVBalance);
			source.setValueAt("KCOLLDATA.svAcctData.acctId", "SV4102251983091505680X");
			source.setValueAt("KCOLLDATA.svAcctData.acctAlias", "å‰¯å�¡");
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

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'ccAcctData' with element type 'KeyedCollection' to 'ccAcctList' with element type 'IndexedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"KCOLLDATA.ccAcctData\" to=\"ICOLLDATA.ccAcctList\" />] from context [CollectionTypeDataCtxt] to [CollectionTypeDataCtxt]:From data element [ccAcctData](KeyedCollection) is not match with the To data element [ccAcctList](IndexedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
	/**
	 * 
	* @Title: testEndlessLoopAppendTrue 
	* @Description: TODO  The test case is endless loop,so it is ignored.
	* void    
	* Mar 16, 2012 11:50:26 AM
	 */
	@Ignore
	@Test(timeout=2000)
	public void testEndlessLoopAppendTrue() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			Context to = from;
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testEndlessLoopICollFmt");
			fmt.mapContents(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testExceptionDatafieldToKCollTrue() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testExceptionDatafieldToKCollFmt");
			fmt.mapContents(from, to);
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'testField' with element type 'DataField' to 'OneLevelKColl' with element type 'KeyedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map from=\"testField\" to=\"OneLevelKColl\" />] from context [testMultiLevelKCollCtx] to [testMultiLevelKCollCtx]:From data element [testField](DataField) is not match with the To data element [OneLevelKColl](KeyedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	
	private void initializeSourceContext(Context source) throws Exception {
		Date theDate = parseStringToDate("2006-07-18");
		source.setValueAt("mixedRec.dateMinus", theDate);
		BigDecimal bdVal = new BigDecimal("1048576.8192").setScale(3, BigDecimal.ROUND_HALF_UP);
		source.setValueAt("mixedRec.numberData.deepNumberData.numberDP3", bdVal);
	}
}
