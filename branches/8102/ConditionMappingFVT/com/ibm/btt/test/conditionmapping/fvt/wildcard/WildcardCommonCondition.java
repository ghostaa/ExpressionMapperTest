package com.ibm.btt.test.conditionmapping.fvt.wildcard;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class WildcardCommonCondition extends CommonTestCase {
	
	/**     <cond id="commonConToDifferentICollCondition" value="{0} &gt; 20000"/>
	 * 		<mapIf id="mapIf1" expression="{cond=commonConToDifferentICollCondition;OneIColl$*$conditionInnerKColl$testInteger}">
				<map fromExpression="OneIColl$*" to="VIPIColl.*" append="false"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*" to="NormalIColl.*" append="false"/>
			</mapElse>
	 */
	
	@Test
	public void testToDifferentICollConditionAppendFalseC(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmtC");
			from.getKeyedCollection().setDynamic(true);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",19998+i);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 3; i++) {
				assertEquals(19998+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 7; i++) {
				assertEquals(20001+i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	
	/**
	 * if( listA.*.balance > 2000 ) 
		    map listA.* to listB.* append="false"
		else if(listA.*.balance <=2000 && listA.*.balance >500)
		    map listA.* to listC.* append="false"
		else
		    map listA.* to listD.* append="false"
	 */
	@Test
	public void testToDifferentICollCondition3C(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmt3C");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i*500);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 1; i++) {
				assertEquals(i*500,to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 4; i++) {
				assertEquals(500+i*500,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(2500+i*500,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	
	/**     <cond id="commonConFromDifferentICollCondition" value="{0} &lt; 5"/>
	 * 		<mapIf expression="{cond=commonConFromDifferentICollCondition;OneIColl$*$conditionInnerKColl$testInteger}">
					<map fromExpression="VIPIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger"/>
			</mapIf>
	 */
	@Test
	public void testFromDifferentICollConditionC(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromDifferentICollConditionFmtC");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				from.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",i*10);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",i*100);
				
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i*10, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i*100, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * 	<cond id="commonConFromDifferentICollCondition" value="{0} &lt; 5"/>
	
		<cond id="commonConFromDifferentICollConditionB" value="{0} &gt;= 70"/>
	 * 			<mapIf expression="{cond=commonConFromDifferentICollCondition;OneIColl$*$conditionInnerKColl$testInteger}">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="OneIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapIf>
				<mapElseIf expression="{cond=commonConFromDifferentICollConditionB;HaveNotSizeIColl$*$conditionInnerKColl$testInteger}">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapElseIf>
				<mapElse>
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapElse>
	 * 	 
	 */
	@Test
	public void testFromDifferentConditionC(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromDifferentConditionFmtC");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 10; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i*10);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 3; i++) {
				assertEquals(7+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 2; i++) {
				assertEquals(5+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * 	<cond id="commonConFromDifferentICollCondition" value="{0} &lt; 5"/>
	
		<mapIf expression="{cond=commonConFromDifferentICollCondition;dse_index}">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="OneIColl.*.conditionInnerKColl.testInteger" append="false"/>
		</mapIf>
		<mapElse>
					<map fromExpression="dse_index" to="OneIColl.*.conditionInnerKColl.testInteger" append="false"/>
		</mapElse>
	 * 	 
	 */
	@Test
	public void testDse_indexInCommonCon(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testDse_indexInCommonConFmtC");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 	<cond id="commonConFromDifferentICollCondition" value="{0} &lt; 5"/>
		<mapIf expression="OneIColl$*$conditionInnerKColl$testFloat &lt; 7.0">
					<map fromExpression="{cond=commonConFromDifferentICollCondition;dse_index}" to="OneIColl.*.conditionInnerKColl.testBoolean" append="false"/>
		</mapIf>
		<mapElse>
					<map fromExpression="dse_index" to="OneIColl.*.conditionInnerKColl.testInteger" append="false"/>
		</mapElse>
		
	 * 	 
	 */
	@Test
	public void testDse_indexInMapping(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testDse_indexInMappingFmtC");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testBoolean",false);
				
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(true, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testBoolean"));
			}
			for (int i = 5; i < 7; i++) {
				assertEquals(false, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testBoolean"));
			}
			for (int i = 0; i < 7; i++) {
				assertEquals(null, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 7; i < 10; i++) {
				assertEquals(i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
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
