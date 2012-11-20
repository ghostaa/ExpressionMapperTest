package com.ibm.btt.test.conditionmapping.fvt.wildcard;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class WildcardCondition extends CommonTestCase {
	
	/**
	 * 		<mapIf id="mapIf1" expression="testInteger &gt; 10000">
				<map fromExpression="OneIColl$*" to="VIPIColl.*" append="false"/>
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*" to="NormalIColl.*" append="false"/>
			</mapElse>
	 */
	
	@Test
	public void testNormalConditionToDifferentList(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("normalConditionToDifferentListFmt");
			from.getKeyedCollection().setDynamic(true);
			from.setValueAt("testInteger",20000);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 10; i++) {
				assertEquals(i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			from.setValueAt("testInteger",200);
			to =  ContextFactory.createContext("WildcardConditionCtx");
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 		<mapIf id="mapIf1" expression="testInteger &gt; 10000">
				<map fromExpression="VIPIColl$*" to="OneIColl.*" append="false"/>
			</mapIf>
			<mapElse>
				<map fromExpression="NormalIColl$*" to="OneIColl.*" append="false"/>
			</mapElse>
	 */
	
	@Test
	public void testNormalConditionFromDifferentListFmt(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("normalConditionFromDifferentListFmt");
			from.getKeyedCollection().setDynamic(true);
			from.setValueAt("testInteger",20000);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("VIPIColl."+i+".conditionInnerKColl.testString","vip"+i);
				from.setValueAt("NormalIColl."+i+".conditionInnerKColl.testString","normal"+i);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 10; i++) {
				assertEquals("vip"+i,to.getValueAt("OneIColl."+i+".conditionInnerKColl.testString"));
			}
			
			from.setValueAt("testInteger",200);
			to =  ContextFactory.createContext("WildcardConditionCtx");
			format.mapContents(from, to);
			
			for (int i = 0; i < 10; i++) {
				assertEquals("normal"+i,to.getValueAt("OneIColl."+i+".conditionInnerKColl.testString"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 		<mapIf id="mapIf1" expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 10000">
				<mapIf expression="testInteger &gt; 3">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapIf>
			</mapIf>
	 */
	
	@Test
	public void testNormalConditionAndWildcardMixedUseFmt1(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("normalConditionAndWildcardMixedUseFmt1");
			from.getKeyedCollection().setDynamic(true);
			//testInteger > 3
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",10000+i);
			}
			from.setValueAt("testInteger",5);
			format.mapContents(from, to);
			
			for (int i = 0; i < 10; i++) {
				assertEquals(10000+i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			//testInteger < 3
			to =  ContextFactory.createContext("WildcardConditionCtx");
			from.setValueAt("testInteger",2);
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(10000+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 		<mapIf expression="testInteger &gt; 3">
				<mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 10000">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapIf>
				<mapElse>
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapElse>
			</mapIf>
	 */
	
	@Test
	public void testNormalConditionAndWildcardMixedUseFmt3(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("normalConditionAndWildcardMixedUseFmt3");
			from.getKeyedCollection().setDynamic(true);
			from.setValueAt("testInteger",5);
			//OneIColl$*$conditionInnerKColl$testInteger > 10000
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",10001+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(10001+i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			//OneIColl$*$conditionInnerKColl$testInteger <  10000
			to =  ContextFactory.createContext("WildcardConditionCtx");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",100+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(100+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			//OneIColl$*$conditionInnerKColl$testInteger &gt; or &lt;  10000
			to =  ContextFactory.createContext("WildcardConditionCtx");
			for (int i = 0; i < 10; i++,i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",10001+i);
			}
			for (int i = 1; i < 10; i++,i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",100+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(10001+i+i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(101+i+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * 		<mapIf id="mapIf1" expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 20000">
				<map fromExpression="OneIColl$*" to="VIPIColl.*" append = "false" />
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*" to="NormalIColl.*" append = "false"/>
	   		</mapElse>
	 */
	
	@Test
	public void testToDifferentICollConditionAppendFalse(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmt");
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
	 * 		<mapIf id="mapIf1" expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 20000">
				<map from="OneIColl.*" to="VIPIColl.*" append="false"/>
			</mapIf>
			<mapElse>
				<map from="OneIColl.*" to="NormalIColl.*" append="false"/>
			</mapElse>
	 */
	
	@Test
	public void testToDifferentICollConditionAppendFalse2(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionUseFromFmt");
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
	 * 		<mapIf id="mapIf1" expression="OneIColl$*$conditionInnerKColl$testInteger &gt; 5">
				<map fromExpression="OneIColl$*" to="VIPIColl.*" append = "ignore" />
			</mapIf>
			<mapElse>
				<map fromExpression="OneIColl$*" to="NormalIColl.*" append = "ignore"/>
	   		</mapElse>
	 */
	@Test
	public void testToDifferentICollConditionAppendIgnore(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionIgnoreFmt");
			from.getKeyedCollection().setDynamic(true);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",1);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",2);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 6; i++) {
				assertEquals(i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 6; i < 10; i++) {
				assertEquals(1,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 6; i++) {
				assertEquals(2,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 6; i < 10; i++) {
				assertEquals(i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if( listA.*.balance > 2000 ) 
   		 map listA.* to listB.*
	 */
	@Test
	public void testToDifferentICollCondition2(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmt2");
			from.getKeyedCollection().setDynamic(true);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",19998+i);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",2);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 3; i++) {
				assertEquals(2,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 3; i < 10; i++) {
				assertEquals(19998+i,to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if( listA.*.balance > 2000 ) 
   		 map listA.* to listB.* append=false
	 */
	@Test
	public void testToDifferentICollCondition22(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmt22");
			from.getKeyedCollection().setDynamic(true);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",19998+i);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",2);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 6; i++) {
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
	public void testToDifferentICollCondition3(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("toDifferentICollConditionFmt3");
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
	/**
	 * 		<mapIf expression="{cond=commonConNumber;OneIColl$*$conditionInnerKColl$testInteger}">
				<map fromExpression="2" to="testInteger" />
			</mapIf>
			<mapElse>
				<map fromExpression="4" to="testInteger" />
			</mapElse>
			
			<cond id="commonConNumber" value="{0} == 100"/>
	 */
	@Test
	public void testWildcardGlobalCondition(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardGlobalConditionFmt");
			from.getKeyedCollection().setDynamic(true);
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",100);
			}
			format.mapContents(from, to);
			assertEquals(2, to.getValueAt("testInteger"));
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",333);
			}
			format.mapContents(from, to);
			assertEquals(4, to.getValueAt("testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	/**
	 *    from * to *
	 */
	
	@Test
	public void testWildcardToWildcard(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardToWildcardFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",100+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(100+i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals(null, to.getValueAt("VIPIColl.0.conditionInnerKColl.testInteger"));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * 			<mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &lt; 100">
					<map from="*" to="*"/>
				</mapIf>
				<mapElse>
					<map fromExpression="4" to="testInteger" />
				</mapElse>
	 */
	@Test
	public void testConditionWildcardToWildcard(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("conditionWildcardToWildcardFmt");
			from.getKeyedCollection().setDynamic(true);
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",9+i);
				from.setValueAt("testInteger",1);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(9+i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals(1, to.getValueAt("testInteger"));
			
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",99+i);
				from.setValueAt("testInteger",1);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(99+i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals(4, to.getValueAt("testInteger"));
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * if listA.*.Integer <5
	 *   map  listB.* to listC.* 
	 */
	@Test
	public void testFromDifferentICollCondition(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromDifferentICollConditionFmt");
			
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
	 * if( listA.*.balance > 2000 ) 
		    map listA.* to listB.* append="false"
		else if(listF.*.balance >1000)
		    map listA.* to listC.* append="false"
		else
		    map listA.* to listD.* append="false"
	 * 	 
	 */
	@Test
	public void testFromDifferentCondition(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromDifferentConditionFmt");
			
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
	 * if( listA.*.balance > 2000 ) 
		    map listA.* to listB.* 
		else if(listF.*.balance >1000)
		    map listA.* to listC.* 
		else
		    map listA.* to listD.* 
	 * 	 
	 */
	@Test
	public void testFromDifferentCondition2(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromDifferentConditionFmt2");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i*10);
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",1);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",2);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",3);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(1, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 7; i++) {
				assertEquals(2, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 7; i < 10; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(3, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 7; i++) {
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 7; i < 10; i++) {
				assertEquals(3, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * if( listA.*.testInteger < 2000 ) 
		    map listA.*.testInteger to listB.*.testInteger
		else if(listA.*.testFloat ==1000)
		    map listA.*.testInteger to listB.*.testInteger
		else
		    map listA.*.testInteger to listC.*.testInteger
	 */
	@Test
	public void testFromSameListCondition(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromSameListConditionFmt");
			
			for (int i = 0; i < 15; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",1995+i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",995f+i);
			}
			/**
			 * if( listA.*.testInteger < 2000 ) 
				    map listA.*.testInteger to listB.*.testInteger
				else if(listA.*.testFloat ==1000)
				    map listA.*.testInteger to listB.*.testInteger
				else
				    map listA.*.testInteger to listC.*.testInteger
			 */
			format.mapContents(from, to);
			for (int i = 0; i < 6; i++) {
				assertEquals(1995+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 4; i++) {
				assertEquals(2001+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			/**
			 * if( listA.*.testInteger < 2000 || listA.*.testFloat == 1000 ) 
				    map listA.*.testInteger to listB.*.testInteger
				else
				    map listA.*.testInteger to listC.*.testInteger
			 */
			format = (DataMapperFormat)FormatElement.readObject("fromSameListConditionVarietyFmt");
			format.mapContents(from, to);
			for (int i = 0; i < 6; i++) {
				assertEquals(1995+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			for (int i = 0; i < 4; i++) {
				assertEquals(2001+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * if( listA.*.testInteger < 5 ) 
		    map listA.* to listB.*
		else if(testInteger <10)
		    map listA.* to listC.*
		else
		    map listA.* to listD.*
	 */
	@Test
	public void testFromSameMapping(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("fromSameMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			from.setValueAt("testInteger",5);
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(5+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			from.setValueAt("testInteger",15);
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(5+i, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testAppendIgnoreSameGroupMapping(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testAppendIgnoreSameGroupMappingFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testDouble",(double)i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",2);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",3);
			}
			format.mapContents(from, to);
			for (int i = 0; i <5; i++) {
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals((double)i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testDouble"));
				assertEquals((float)i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 5; i <10; i++) {
				assertEquals(3, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(null, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testDouble"));
				assertEquals(null, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 0; i <5; i++) {
				assertEquals(2, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(null, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testDouble"));
				assertEquals(null, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 5; i <10; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals((double)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testDouble"));
				assertEquals((float)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	/**
	 * 			<mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &lt; 5">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="OneIColl.*.conditionInnerKColl.testInteger"/>
					<mapIf expression="OneIColl$*$conditionInnerKColl$testInteger &lt; 3">
						<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger"/>
					</mapIf>
					<mapElse>
						<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="VIPIColl.*.conditionInnerKColl.testInteger"/>
					</mapElse>
				</mapIf>
				<mapElse>
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="HaveNotSizeIColl.*.conditionInnerKColl.testInteger"/>
				</mapElse>
	 */
	@Test
	public void testInnerConditionFromSameCondition(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("innerConditionFromSameConditionFmt");
			
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",4);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",9);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",2);
			from.setValueAt("OneIColl.3.conditionInnerKColl.testInteger",3);
			from.setValueAt("OneIColl.4.conditionInnerKColl.testInteger",5);
			from.setValueAt("OneIColl.5.conditionInnerKColl.testInteger",3);
			from.setValueAt("OneIColl.6.conditionInnerKColl.testInteger",1);
			from.setValueAt("OneIColl.7.conditionInnerKColl.testInteger",2);
			from.setValueAt("OneIColl.8.conditionInnerKColl.testInteger",5);
			from.setValueAt("OneIColl.9.conditionInnerKColl.testInteger",6);
			from.setValueAt("OneIColl.10.conditionInnerKColl.testInteger",8);
			
			format.mapContents(from, to);
			assertEquals(4, to.getValueAt("OneIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(2, to.getValueAt("OneIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(3, to.getValueAt("OneIColl.2.conditionInnerKColl.testInteger"));
			assertEquals(3, to.getValueAt("OneIColl.3.conditionInnerKColl.testInteger"));
			assertEquals(1, to.getValueAt("OneIColl.4.conditionInnerKColl.testInteger"));
			assertEquals(2, to.getValueAt("OneIColl.5.conditionInnerKColl.testInteger"));
			
			assertEquals(2, to.getValueAt("NormalIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(1, to.getValueAt("NormalIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(2, to.getValueAt("NormalIColl.2.conditionInnerKColl.testInteger"));
			
			assertEquals(4, to.getValueAt("VIPIColl.0.conditionInnerKColl.testInteger"));
			
			assertEquals(9, to.getValueAt("HaveNotSizeIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(5, to.getValueAt("HaveNotSizeIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(5, to.getValueAt("HaveNotSizeIColl.2.conditionInnerKColl.testInteger"));
			assertEquals(6, to.getValueAt("HaveNotSizeIColl.3.conditionInnerKColl.testInteger"));
			assertEquals(8, to.getValueAt("HaveNotSizeIColl.4.conditionInnerKColl.testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testInnerConditionFromSameConditionFmtDefectFromLST(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("innerConditionFromSameConditionFmtDefectFromLST");
			
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",4);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",9);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",2);
			from.setValueAt("OneIColl.3.conditionInnerKColl.testInteger",3);
			from.setValueAt("OneIColl.4.conditionInnerKColl.testInteger",5);
			from.setValueAt("OneIColl.5.conditionInnerKColl.testInteger",3);
			from.setValueAt("OneIColl.6.conditionInnerKColl.testInteger",1);
			from.setValueAt("OneIColl.7.conditionInnerKColl.testInteger",2);
			from.setValueAt("OneIColl.8.conditionInnerKColl.testInteger",5);
			from.setValueAt("OneIColl.9.conditionInnerKColl.testInteger",6);
			from.setValueAt("OneIColl.10.conditionInnerKColl.testInteger",8);
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString",""+i);
				if (i%2==0) {
					
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",3.0f);
				}else {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",2.0f);
					
				}
			}
			
			
			format.mapContents(from, to);
			assertEquals(4, to.getValueAt("OneIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(2, to.getValueAt("OneIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(3, to.getValueAt("OneIColl.2.conditionInnerKColl.testInteger"));
			assertEquals(3, to.getValueAt("OneIColl.3.conditionInnerKColl.testInteger"));
			assertEquals(1, to.getValueAt("OneIColl.4.conditionInnerKColl.testInteger"));
			assertEquals(2, to.getValueAt("OneIColl.5.conditionInnerKColl.testInteger"));
			
			assertEquals("0", to.getValueAt("OneIColl.0.conditionInnerKColl.testString"));
			assertEquals("2", to.getValueAt("OneIColl.1.conditionInnerKColl.testString"));
			assertEquals("3", to.getValueAt("OneIColl.2.conditionInnerKColl.testString"));
			assertEquals("5", to.getValueAt("OneIColl.3.conditionInnerKColl.testString"));
			assertEquals("6", to.getValueAt("OneIColl.4.conditionInnerKColl.testString"));
			assertEquals("7", to.getValueAt("OneIColl.5.conditionInnerKColl.testString"));
			
			assertEquals(3.3f, to.getValueAt("OneIColl.0.conditionInnerKColl.testFloat"));
			assertEquals(3.3f, to.getValueAt("OneIColl.1.conditionInnerKColl.testFloat"));
			assertEquals(2.2f, to.getValueAt("OneIColl.2.conditionInnerKColl.testFloat"));
			assertEquals(2.2f, to.getValueAt("OneIColl.3.conditionInnerKColl.testFloat"));
			assertEquals(3.3f, to.getValueAt("OneIColl.4.conditionInnerKColl.testFloat"));
			assertEquals(2.2f, to.getValueAt("OneIColl.5.conditionInnerKColl.testFloat"));
					
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testUseCaseCM1(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testUseCaseCM1Fmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString","aa"+i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","bb"+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i <10; i++) {
				assertEquals("aa"+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testDoubleIf(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testDoubleIfFmt");
			
			for (int i = 0; i < 3; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString","aa"+i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","bb"+i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testFloat",(float)i+i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testString", "abc");
			}
			format.mapContents(from, to);
			for (int i = 0; i <3; i++) {
				assertEquals("aa"+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
				assertEquals((float)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 3; i <6; i++) {
				assertEquals("bb"+(i-3), to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
				assertEquals((float)(i-3)+(i-3), to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testDoubleIfAppendIgnore(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testDoubleIfAppendIgnoreFmt");
			
			for (int i = 0; i < 3; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString","aa"+i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","bb"+i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testFloat",(float)i+i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testString", "abc");
			}
			format.mapContents(from, to);
			for (int i = 0; i <3; i++) {
				assertEquals("aa"+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
				assertEquals((float)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 3; i <6; i++) {
				assertEquals("bb"+(i-3), to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
				assertEquals((float)(i-3)+(i-3), to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testIfElseIfAppendIgnore(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testIfElseIfAppendIgnoreFmt");
			
			for (int i = 0; i < 10; i++) {
				if (i%2==0) {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString","aa"+i);
					from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","");
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				}else {
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testString","");
					from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","bb"+i);
					from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				}
			}
			format.mapContents(from, to);
			for (int i = 0; i <10; i++) {
				if (i%2==0) {
					assertEquals("aa"+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
					assertEquals((float)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
				}else{
					assertEquals("bb"+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testString"));
					assertEquals(null, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testConditonListSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testConditonListSizeFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",2);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				from.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i <5; i++) {
				assertEquals(i+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i <10; i++) {
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			from = ContextFactory.createContext("WildcardConditionCtx");
			to =  ContextFactory.createContext("WildcardConditionCtx");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",i+i);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",2);
			}
			format.mapContents(from, to);
			for (int i = 0; i <5; i++) {
				assertEquals(i+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i <10; i++) {
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testGotoWildcard(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testGotoWildcardFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			format.mapContents(from, to);
			for (int i = 0; i <5; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i <2; i++) {
				assertEquals(5+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testList(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testListFmt");
			
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testFloat",(float)i);
				from.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",i+i);
				from.setValueAt("VIPIColl."+i+".conditionInnerKColl.testFloat",(float)i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i <5; i++) {
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals((float)i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
			}
			for (int i = 5; i <10; i++) {
				assertEquals(i+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals((float)i+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testFloat"));
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
