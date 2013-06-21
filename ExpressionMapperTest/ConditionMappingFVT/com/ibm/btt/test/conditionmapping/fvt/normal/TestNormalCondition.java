package com.ibm.btt.test.conditionmapping.fvt.normal;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
//import com.ibm.btt.dojo.model.TreeModel;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class TestNormalCondition extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}
	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.INFO);
	}
	
	@Test
	public void testMapIfExample(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = ContextFactory.createContext("NormalConditionCtx");
			from.setValueAt("testInteger", 2);
			DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testNormalConditionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1, to.getValueAt("testInteger"));
			from.setValueAt("testInteger", 8);
			fmt.mapContents(from, to);
			Assert.assertEquals(2, to.getValueAt("testInteger"));
			from.setValueAt("testInteger", 12);
			fmt.mapContents(from, to);
			Assert.assertEquals(3, to.getValueAt("testInteger"));
			from.setValueAt("testInteger", 16);
			fmt.mapContents(from, to);
			Assert.assertEquals(4, to.getValueAt("testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testConditionExecutionOreder(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = ContextFactory.createContext("NormalConditionCtx");
			from.setValueAt("testInteger", 2);
			DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testConditionExecutionOrederFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1, to.getValueAt("testInteger"));
			from.setValueAt("testString", "Test");
			fmt.mapContents(from, to);
			Assert.assertEquals(1, to.getValueAt("testInteger"));
			from.setValueAt("testInteger", 6);
			fmt.mapContents(from, to);
			Assert.assertEquals(2, to.getValueAt("testInteger"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testMappingInUpInnerDown(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = ContextFactory.createContext("NormalConditionCtx");
			
			from.getKeyedCollection().setDynamic(true);
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testInteger", i*100);
				from.setValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testShort",(short)(i*200));
				from.setValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testNumber", new BigDecimal(i*300));
				from.setValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testFloat",(float)i*400);
			}
			Date theDate = parseStringToDate("2012-12-21");
			from.setValueAt("conditionInnerKColl.testDate", theDate);
			from.setValueAt("conditionInnerKColl.testBoolean",true);
			DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testMappingInUpInnerDownFmt");
			fmt.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				Assert.assertEquals(new BigInteger(new Integer(i*100).toString()), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testBigInteger"));
				Assert.assertEquals((short)(i*200), to.getValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testShort"));
				Assert.assertEquals(new BigDecimal(i*300).setScale(3), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testNumber"));
				Assert.assertEquals((float)i*400, to.getValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testFloat"));
			}
			Assert.assertEquals(1,to.getValueAt("conditionInnerKColl.testInteger"));
			Assert.assertEquals(theDate,to.getValueAt("conditionInnerKColl.testDate"));
			
			from.setValueAt("conditionInnerKColl.testBoolean",false);
			from.setValueAt("conditionInnerKColl.testLong",new Long(20l));
			theDate = parseStringToDate("2012-12-22");
			from.setValueAt("conditionInnerKColl.testDate", theDate);
			fmt.mapContents(from, to);
			Assert.assertEquals(2,to.getValueAt("conditionInnerKColl.testInteger"));
			for (int i = 0; i < 5; i++) {
				
				Assert.assertEquals(new BigInteger(new Integer(i*100).toString()), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testBigInteger"));
				Assert.assertEquals((short)(i*200), to.getValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testShort"));
				Assert.assertEquals(new BigDecimal(i*300).setScale(3), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testNumber"));
			}
			Assert.assertEquals(theDate,to.getValueAt("conditionInnerKColl.testDate"));
			
			from.setValueAt("conditionInnerKColl.testLong",new Long(5l));
			theDate = parseStringToDate("2012-12-23");
			from.setValueAt("conditionInnerKColl.testDate", theDate);
			fmt.mapContents(from, to);
			Assert.assertEquals(3,to.getValueAt("conditionInnerKColl.testInteger"));
			for (int i = 0; i < 5; i++) {
				Assert.assertEquals(new BigInteger(new Integer(i*100).toString()), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testBigInteger"));
				Assert.assertEquals((short)(i*200), to.getValueAt("OneIColl.0.TwoIColl."+i+".conditionInnerKColl.testShort"));
				Assert.assertEquals(new BigDecimal(i*300).setScale(3), to.getValueAt("OneIColl.0.TwoIColl.0.ThreeIColl."+i+".conditionInnerKColl.testNumber"));
			}
			Assert.assertEquals(theDate,to.getValueAt("conditionInnerKColl.testDate"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testRefLocalFmt(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testRefLocalFmt");
			from.setValueAt("conditionInnerKColl.testBoolean",true);
			fmt.mapContents(from, to);
			Assert.assertEquals(1,to.getValueAt("conditionInnerKColl.testInteger"));
			
			from.setValueAt("conditionInnerKColl.testBoolean",false);
			from.setValueAt("conditionInnerKColl.testLong",new Long(20l));
			fmt.mapContents(from, to);
			Assert.assertEquals(2,to.getValueAt("conditionInnerKColl.testInteger"));
			
			from.setValueAt("conditionInnerKColl.testLong",new Long(5l));
			fmt.mapContents(from, to);
			Assert.assertEquals(3,to.getValueAt("conditionInnerKColl.testInteger"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testImportFmt(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("testImportFmt");
			from.setValueAt("conditionInnerKColl.testBoolean",true);
			fmt.mapContents(from, to);
			Assert.assertEquals(1,to.getValueAt("conditionInnerKColl.testInteger"));
			
			from.setValueAt("conditionInnerKColl.testBoolean",false);
			from.setValueAt("conditionInnerKColl.testLong",new Long(20l));
			fmt.mapContents(from, to);
			Assert.assertEquals(2,to.getValueAt("conditionInnerKColl.testInteger"));
			
			from.setValueAt("conditionInnerKColl.testLong",new Long(5l));
			fmt.mapContents(from, to);
			Assert.assertEquals(3,to.getValueAt("conditionInnerKColl.testInteger"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	@Test
	public void testGotoFmt(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to = from;
			from.setValueAt("testInteger",3);
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGotoFmt");
			format.mapContents(from, to);
			Assert.assertEquals(1,to.getValueAt("testInteger"));
			format.mapContents(from, to);
			Assert.assertEquals(2,to.getValueAt("testInteger"));
			format.mapContents(from, to);
			Assert.assertEquals(1,to.getValueAt("testInteger"));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testGotoFmt1(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGotoMultipathFmt");
			from.setValueAt("testFloat",8.8f);
			
			from.setValueAt("testInteger",6);
			from.setValueAt("testBoolean",true);
			from.setValueAt("testDouble",9.9d);
			format.mapContents(from, to);
			Assert.assertEquals(6,to.getValueAt("testInteger"));
			Assert.assertEquals(88f,to.getValueAt("testFloat"));
			Assert.assertEquals(99d,to.getValueAt("testDouble"));
			
			from.setValueAt("testInteger",2);
			from.setValueAt("testDouble",9.91d);
			from.setValueAt("testBoolean",false);
			format.mapContents(from, to);
			Assert.assertEquals(6,to.getValueAt("testInteger"));
			Assert.assertEquals(880f,to.getValueAt("testFloat"));
			Assert.assertEquals(991d,to.getValueAt("testDouble"));
			
			
		
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testNOElse(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testNOElseFmt");
			
			from.setValueAt("testInteger",4);
			format.mapContents(from, to);
			assertEquals(null, to.getValueAt("testInteger"));
			
			from.setValueAt("testInteger",6);
			format.mapContents(from, to);
			assertEquals(6, to.getValueAt("testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testNOMappingInCondition(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testNOMappingInConditionFmt");
			format.mapContents(from, to);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testConditionDictionary(){
		try {
			
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testConditionDictionaryFmt");
			
			from.setValueAt("testString","a");
			format.mapContents(from, to);
			assertEquals(3, to.getValueAt("testInteger"));
			
			from.setValueAt("testString","k");
			format.mapContents(from, to);
			assertEquals(2, to.getValueAt("testInteger"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if dse_index <  3
				4 = testInteger
				OneIColl$*$conditionInnerKColl$testFloat to OneIColl.*.conditionInnerKColl.testFloat append="false"
		
	 */
	@Test
	public void testIndexAppendFalse(){
		try {
			
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testIndexAppendFalseFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
			}
			format.mapContents(from, to);
			assertEquals(4, to.getValueAt("testInteger"));
			for (int i = 0; i < 3; i++) {
				System.out.println(to.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
				//assertEquals(i*100f, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testIndexAppendIgnore(){
		try {
			
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testIndexAppendIgnoreFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testDouble", i*100d);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat", 1f);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testDouble", 2d);
			}
			format.mapContents(from, to);
			assertEquals(4, to.getValueAt("testInteger"));
			for (int i = 0; i < 3; i++) {
				assertEquals(i*100f, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
				assertEquals(i*100d, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testDouble"));
			}
			for (int j = 3; j < 5; j++) {
				assertEquals(1f, to.getValueAt("VIPIColl."+j+".conditionInnerKColl.testFloat"));
				assertEquals(2d, to.getValueAt("VIPIColl."+j+".conditionInnerKColl.testDouble"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Ignore
	public void testEndless(){
		try {
			
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testEndlessFmtA");
			
		}catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testGlobalFuntion(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGlobalFunctionFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".testDouble", 400d-i*100d);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(400-i*100d, from.getValueAt("OneIColl."+i+".testDouble"));
				assertEquals(i*100f, from.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i*100d, to.getValueAt("OneIColl."+i+".testDouble"));
				assertEquals(400-i*100f, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testGlobalFuntionWildcard(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGlobalFunctionWildcardFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger", i);
				if (i%2==0) {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString", "btta");
				}else {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString", "abc");
				}
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",1);
			}
			for (int i = 0; i < 5; i++, i++) {
				assertEquals("btta", from.getValueAt("OneIColl."+i+".conditionInnerKColl.testString"));
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++ , i++) {
				assertEquals(true, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testBoolean"));
			}
			for (int i = 1; i < 5; i++ , i++) {
				assertEquals(false, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testBoolean"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testGlobalFuntionConditionWildcard(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGlobalFunctionConditionWildcardFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger", i);
				if (i%2==0) {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString", "btta");
				}else {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString", "abc");
				}
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testBoolean",true);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testBoolean",true);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++ ) {
				if (i%2==0) {
					assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				}else {
					assertEquals(null, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				}
			}
			for (int i = 0; i < 10; i++) {
				if (i%2==0) {
					assertEquals(null, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				}else {
					assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testSameInstanceMapping(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  from;
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testSameInstanceMappingFmt");
			from.setValueAt("testInteger", 3);
			format.mapContents(from, to);
			assertEquals(3, to.getValueAt("testInteger"));
			String[] logContents = getLogContentsInLines();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < logContents.length; i++){
			 sb. append(logContents[i]);
			}
			System.out.println(sb);
			String line = "com.ibm.btt.base.DataMapperExpressionConverterFormat [INFO]############  Same instance of mapping from and to, just skip without calling map : <map fromExpression=\"testInteger\" to=\"testInteger\" />";
			Assert.assertEquals(true,sb.toString().contains(line));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testSameListInstanceMapping(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  from;
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testSameListInstanceMappingFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString", "btt");
			}
			format.mapContents(from, to);
			String[] logContents = getLogContentsInLines();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < logContents.length; i++){
			 sb. append(logContents[i]);
			}
			System.out.println(sb);
			String line = "com.ibm.btt.base.DataMapperExpressionConverterFormat [INFO]############  Same instance of mapping from and to, just skip without calling map : <map fromExpression=\"OneIColl\" to=\"OneIColl\" />";
			Assert.assertEquals(true,sb.toString().contains(line));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testSameInstanceMappingInGlobalFunction(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  from;
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testSameInstanceMappingInGlobalFunctionFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".testDouble", 400d-i*100d);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(400-i*100d, from.getValueAt("OneIColl."+i+".testDouble"));
				assertEquals(i*100f, from.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
			}
			format.mapContents(from, to);
			String[] logContents = getLogContentsInLines();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < logContents.length; i++){
			 sb. append(logContents[i]);
			}
			String line = "com.ibm.btt.base.DataMapperExpressionConverterFormat [INFO]############  Same instance of mapping from and to, just skip without calling map : <map fromExpression=\"functs_ConditionFunctions.sortByName(OneIColl, 'testDouble')\" to=\"OneIColl\" />";
			Assert.assertEquals(true,sb.toString().contains(line));
			for (int i = 0; i < 5; i++) {
				assertEquals(i*100d, to.getValueAt("OneIColl."+i+".testDouble"));
				assertEquals(400-i*100f, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testFloat"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	@Test
	public void testConditionIsTrueOrTrue(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testConditionIsTrueOrTrue");
			
			format.mapContents(from, to);
			Assert.assertEquals("VIP2",to.getValueAt("testString"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/*@Ignore
	public void testTreeModel(){
		try {
			Context from = ContextFactory.createContext("NormalConditionCtx");
			Context to =  ContextFactory.createContext("NormalConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testTreeModel");
			TreeModel root = (TreeModel) from.getElementAt("root");
			TreeModel level1_1 = new TreeModel("level1-1", "002", "Level1");
			TreeModel level2_1 = new TreeModel("level2-1", "005", "Level2");
			root.addChild(level1_1);
			root.addChild(level2_1);
			System.out.println(from.getElementAt("root"));
			format.mapContents(from, to);
			System.out.println(to.getKeyedCollection());
			System.out.println(to.getElementAt("root"));
			assertEquals(from.getElementAt("root"), to.getElementAt("root"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}*/
	/*
	@Test
	public void test(){
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	*/
}
