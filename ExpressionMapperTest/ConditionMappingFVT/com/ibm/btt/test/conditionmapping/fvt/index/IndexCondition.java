package com.ibm.btt.test.conditionmapping.fvt.index;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class IndexCondition extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}
	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.INFO);
	}
	
	/**
	 * if dse_index <  3
		OneIColl.* to VIPIColl.* append=false
	   else 
	   	OneIColl.* to NormalIColl.* append=false
	 */
	@Test
	public void testindexToDifferentICollConditionAppendFalse(){
		try {
			Context from = ContextFactory.createContext("IndexConditionCtx");
			Context to =  ContextFactory.createContext("IndexConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToDifferentICollConditionAppendFalseFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(i*100f, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 0; i < 7; i++) {
				assertEquals(i*100f + 300f, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if dse_index <  3
		OneIColl.* to VIPIColl.* append=Ignore
	   else 
	   	OneIColl.* to NormalIColl.* append=Ignore
	 */
	@Test
	public void testindexToDifferentICollConditionAppendIgnore(){
		try {
			
			Context from = ContextFactory.createContext("IndexConditionCtx");
			Context to =  ContextFactory.createContext("IndexConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToDifferentICollConditionAppendIgnoreFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat", i*100f);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat", 1f);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat", 2f);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(i*100f, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
				assertEquals(2f, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(i*100f, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
				assertEquals(1f, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if dse_index <  3
		OneIColl.*.conditionInnerKColl.testInteger to VIPIColl.*.conditionInnerKColl.testInteger append=false
	   else
	   	OneIColl.*.conditionInnerKColl.testInteger to NormalIColl.*.conditionInnerKColl.testInteger append=false
	 */
	@Test
	public void testindexToDifferentICollFieldConditionAppendFalse(){
		try {
			
			Context from = ContextFactory.createContext("IndexConditionCtx");
			Context to =  ContextFactory.createContext("IndexConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToDifferentICollFieldConditionAppendFalseFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger", i*100);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 1);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 1);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(i*100, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			for (int i = 0; i < 7; i++) {
				assertEquals(i*100 + 300, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if dse_index <  3
		OneIColl.*.conditionInnerKColl.testInteger to VIPIColl.*.conditionInnerKColl.testInteger append=ignore
	   else
	   	OneIColl.*.conditionInnerKColl.testInteger to NormalIColl.*.conditionInnerKColl.testInteger append=ignore
	 */
	@Test
	public void testindexToDifferentICollFieldConditionAppendIgnore(){
		try {
			
			Context from = ContextFactory.createContext("IndexConditionCtx");
			Context to =  ContextFactory.createContext("IndexConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToDifferentICollFieldConditionAppendIgnoreFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger", i*100);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 1);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 2);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(i*100, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(1, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 3; i++) {
				assertEquals(2, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(i*100, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void testIndexToNestDifferentICollFieldConditionAppendFalse(){
		try {
			
			Context from = ContextFactory.createContext("IndexConditionCtx");
			Context to =  ContextFactory.createContext("IndexConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToNestDifferentICollFieldConditionAppendFalseFmt");
			from.setValueAt("testInteger", 230);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger", i);
			}
			format.mapContents(from, to);
			assertEquals(230, to.getValueAt("testInteger"));
			for (int i = 0; i < 3; i++) {
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 7; i++) {
				assertEquals(i+3, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 *
		<map fromExpression="dse_index" to="VIPIColl.*.conditionInnerKColl.testBoolean" append="ignore"/>
	 */
	@Test
	public void testindexToICollFieldNOConditionAppendIgnore(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexToICollFieldNOConditionAppendFalseFmt");
			
			for (int i = 0; i < 10; i++) {
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 999);
			}
			
			format.mapContents(from, to);
			String[] logContents = getLogContentsInLines();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < logContents.length; i++){
			 sb. append(logContents[i]);
			}
			String line = "#FUNC [INFO]    => Begin mapping from context 'WildcardConditionCtx' to context 'WildcardConditionCtx'#FUNC [INFO]    Mapping from 'dse_index' to 'VIPIColl.*.conditionInnerKColl.testInteger'com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"dse_index\" to=\"VIPIColl.*.conditionInnerKColl.testInteger\" append=\"false\" />] from context [WildcardConditionCtx] to [WildcardConditionCtx]:Script Exception from ExpressionEval dse_index#FUNC [INFO]    <= End of mapping";
			System.out.println("==="+sb+"===");
			Assert.assertEquals(true,sb.toString().contains(line));
			for (int i = 0; i < 10; i++) {
				assertEquals(999,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	
	
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
