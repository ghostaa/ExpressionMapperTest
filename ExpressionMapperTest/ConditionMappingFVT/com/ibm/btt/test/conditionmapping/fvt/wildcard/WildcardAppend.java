package com.ibm.btt.test.conditionmapping.fvt.wildcard;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class WildcardAppend extends CommonTestCase {
	/**
	 * <mapperConverterExpression>
			<map fromExpression="HaveNotSizeIColl" to="HaveNotSizeIColl" append="true"/>
		</mapperConverterExpression>
	 */
	@Test
	public void testAppendTrueNotSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendTrueNotSizeFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i-5, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * <mapperConverterExpression>
			<map fromExpression="HaveNotSizeIColl" to="HaveNotSizeIColl" append="false"/>
		</mapperConverterExpression>
	 */
	@Test
	public void testAppendFalseNotSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendFalseNotSizeFmt");
			for (int i = 0; i < 2; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 2; i++) {
				assertEquals(i, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testAppendIgnoreNotSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendIgnoreNotSizeFmt");
			for (int i = 0; i < 2; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 2; i++) {
				assertEquals(i, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 2; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	@Test
	public void testAppendTrueLessThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendTrueSizeFmt");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 25; i++) {
				System.out.println(to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 15; i < 25; i++) {
				assertEquals(i-15, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testAppendTrueMoreThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendTrueSizeFmt");
			for (int i = 0; i < 20; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 35; i++) {
				System.out.println(to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 15; i < 35; i++) {
				assertEquals(i-15, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testAppendFalseLessThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendFalseSizeFmt");
			for (int i = 0; i < 2; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			
			for (int i = 0; i < 2; i++) {
				assertEquals(i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	@Test
	public void testAppendFalseMoreThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendFalseSizeFmt");
			for (int i = 0; i < 20; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 20; i++) {
				System.out.println(to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 20; i++) {
				assertEquals(i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testAppendIgnoreLessThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendIgnoreSizeFmt");
			for (int i = 0; i < 2; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 2; i++) {
				assertEquals(i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 2; i < 5; i++) {
				assertEquals(i+i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	@Test
	public void testAppendIgnoreMoreThanSize(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("wildcardAppendIgnoreSizeFmt");
			for (int i = 0; i < 20; i++) {
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 5; i++) {
				to.setValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 20; i++) {
				assertEquals(i, to.getValueAt("HaveSizeIColl."+i+".conditionInnerKColl.testInteger"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	/**
	 * wildcard default use append ignore
	 */
	@Test
	public void testDefault(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("appendDefaultFmt");
			for (int i = 0; i < 5; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i);
			}
			for (int i = 0; i < 10; i++) {
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i+i);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger",i+i);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger",i+i);
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
				System.out.println(to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				System.out.println(to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				System.out.println(to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
				System.out.println(to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				System.out.println(to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 5; i++) {
				assertEquals(i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 5; i < 10; i++) {
				assertEquals(i+i, to.getValueAt("OneIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(i+i, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				assertEquals(i+i, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	
	@Test
	public void testMultiAppend(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testMultiAppendFalseFmt");
			
			for (int i = 0; i < 20; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i%2);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(0, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			for (int i = 0; i < 10; i++) {
				assertEquals(1, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
			}
			/**
			 * 
			 */
			from = ContextFactory.createContext("WildcardConditionCtx");
			for (int i = 0; i < 10; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i%2);
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 5);
				to.setValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger", 6);
				
			}
			format = (DataMapperFormat)FormatElement.readObject("testMultiAppendIgnoreFmt");
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				if (i%2==1) {
					assertEquals(1, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				}else
					assertEquals(5, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
				
			}
			for (int i = 0; i < 10; i++) {
				if (i%2==0) {
					assertEquals(0, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				}else {
					assertEquals(6, to.getValueAt("VIPIColl."+i+".conditionInnerKColl.testInteger"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * <mapIf expression="(HaveNotSizeIColl$*$conditionInnerKColl$testInteger &gt; 10) ">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
				</mapIf>
				<mapElse>
					<map fromExpression="HaveNotSizeIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="ignore"/>
				</mapElse>
	 */
	@Test
	public void testSameBlockAppend(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testSameBlockAppendFmt");
			for (int i = 0; i < 3; i++) {
				to.setValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger", 1);
			}
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",30);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",11);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",40);

			from.setValueAt("HaveNotSizeIColl.0.conditionInnerKColl.testInteger",20);
			from.setValueAt("HaveNotSizeIColl.1.conditionInnerKColl.testInteger",8);
			from.setValueAt("HaveNotSizeIColl.2.conditionInnerKColl.testInteger",15);
			format.mapContents(from, to);
			
			assertEquals(30,to.getValueAt("NormalIColl.0.conditionInnerKColl.testInteger"));
			assertEquals(8,to.getValueAt("NormalIColl.1.conditionInnerKColl.testInteger"));
			assertEquals(40,to.getValueAt("NormalIColl.2.conditionInnerKColl.testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	
	/**
	 * <mapIf expression="HaveNotSizeIColl$*$conditionInnerKColl$testInteger &gt; 10 ">
					<map fromExpression="OneIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="true"/>
				</mapIf>
				<mapElse>
					<map fromExpression="HaveNotSizeIColl$*$conditionInnerKColl$testInteger" to="NormalIColl.*.conditionInnerKColl.testInteger" append="false"/>
				</mapElse>
	 */
	@Test
	public void testSameBlockAppendExecutionOrder(){
		try {
			
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testSameBlockAppendExecutionOrderFmt");
			from.setValueAt("HaveNotSizeIColl.0.conditionInnerKColl.testInteger",20);
			from.setValueAt("HaveNotSizeIColl.1.conditionInnerKColl.testInteger",7);
			from.setValueAt("HaveNotSizeIColl.2.conditionInnerKColl.testInteger",15);
			
			from.setValueAt("OneIColl.0.conditionInnerKColl.testInteger",30);
			from.setValueAt("OneIColl.1.conditionInnerKColl.testInteger",8);
			from.setValueAt("OneIColl.2.conditionInnerKColl.testInteger",40);

			for (int j = 0; j < 10; j++) {
				to.setValueAt("NormalIColl."+j+".conditionInnerKColl.testInteger",j);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals(30,to.getValueAt("NormalIColl.10.conditionInnerKColl.testInteger"));
			assertEquals(7,to.getValueAt("NormalIColl.11.conditionInnerKColl.testInteger"));
			assertEquals(40,to.getValueAt("NormalIColl.12.conditionInnerKColl.testInteger"));
			
			
			for (int j = 0; j < 13; j++) {
				to.setValueAt("NormalIColl."+j+".conditionInnerKColl.testInteger",j+j);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(i+i,to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals(30,to.getValueAt("NormalIColl.13.conditionInnerKColl.testInteger"));
			assertEquals(7,to.getValueAt("NormalIColl.14.conditionInnerKColl.testInteger"));
			assertEquals(40,to.getValueAt("NormalIColl.15.conditionInnerKColl.testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testAppendEntrance(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testAppendEntranceFmt");
			
			for (int i = 0; i < 20; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i%2);
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",999);
				
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(i%2, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}
	@Test
	public void testInnerAppend(){
		try {
			Context from = ContextFactory.createContext("WildcardConditionCtx");
			Context to =  ContextFactory.createContext("WildcardConditionCtx");
			DataMapperFormat format = (DataMapperFormat)FormatElement.readObject("testInnerAppendFmt");
			
			for (int i = 0; i < 20; i++) {
				from.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",i%2);
				to.setValueAt("OneIColl."+i+".conditionInnerKColl.testInteger",999);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testInteger",i);
				from.setValueAt("HaveNotSizeIColl."+i+".conditionInnerKColl.testString","jack"+i);
			}
			format.mapContents(from, to);
			for (int i = 0; i < 10; i++) {
				assertEquals(i%2, to.getValueAt("NormalIColl."+i+".conditionInnerKColl.testInteger"));
			}
			assertEquals("jack2", to.getValueAt("NormalIColl.2.conditionInnerKColl.testString"));
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
