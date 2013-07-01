package com.ibm.btt.test.conditionmapping.fvt.wildcard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import junit.framework.Assert;

public class WildcardAndIndexCondition extends CommonTestCase {
	
	/**
	 * <mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 20">
					<mapIf expression="(HaveNotSizeIColl$*$conditionInnerKColl$testInteger &gt; 10) && (dse_index &lt; 2) ">
						<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger + HaveNotSizeIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="false"/>
					</mapIf>
					<mapElse>
						<map fromExpression="HaveNotSizeIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="false"/>
					</mapElse>
		</mapIf>
	 */
	@Test
	public void testWildcardAndIndexFromDifferentIColl(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAndIndexFromDifferentICollFmt");
			
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",30);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",10);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",40);

			from.setValueAt("HaveNotSizeIColl.0.conditionInnerKColl.testInteger",20);
			from.setValueAt("HaveNotSizeIColl.1.conditionInnerKColl.testInteger",11);
			from.setValueAt("HaveNotSizeIColl.2.conditionInnerKColl.testInteger",15);
			format.mapContents(from, to);
			
			assertEquals(50,to.getValueAt("NormalIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(15,to.getValueAt("NormalIColl.1.conditionInnerKColl.testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * <mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &lt; 100">
				<map fromExpression="des_index" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
		</mapElse>
	 */
	@Test
	public void testWildcardConditionIndexMappingFmt(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardConditionIndexMappingFmt");
			
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",90);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",110);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",80);
			for (int i = 0; i < 3; i++) {
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 999);
			}
			format.mapContents(from, to);
			
			assertEquals(0,to.getValueAt("NormalIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(110,to.getValueAt("NormalIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(2,to.getValueAt("NormalIColl.2.conditionInnerKColl.testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * <mapIf expression="dse_index &lt; 5">
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
		</mapElse>
	 */
	@Test
	public void testIndexConditionWildcardMappingFmt(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexConditionWildcardMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 999);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 999);
			}
			
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * <mapIf expression="(OneIColl$*$conditionInnerKColl$testInteger &gt; 5) && (dse_index &lt; 5)">
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapElse>
	 */
	@Test
	public void testIndexWildcardConditionWildcardMapping(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexWildcardConditionWildcardMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 999);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 888);
			}
			
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(999,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 3; i++) {
				assertEquals(888,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 *<mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &lt; 5">
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger  + dse_index " to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
			</mapElse>
	 */
	@Test
	public void testWildcardConditionIndexildcardMapping(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardConditionIndexildcardMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 999);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 999);
			}
			
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
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
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("indexConditionAndIndexMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 999);
			}
			
			format.mapContents(from, to);
			assertEquals(0,to.getValueAt("VIPIColl.0.conditionInnerKColl.testInteger"));
			for (int i = 1; i < 10; i++) {
				System.out.println(to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
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
