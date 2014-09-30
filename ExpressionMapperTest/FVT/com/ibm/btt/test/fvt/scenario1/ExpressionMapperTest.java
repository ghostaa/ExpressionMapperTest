/**
 *  Scenario1
 */
package com.ibm.btt.test.fvt.scenario1;

import org.junit.Test;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataElement;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class ExpressionMapperTest extends CommonTestCase {
	
	protected boolean isLogVerificationEnabled() {
		return true;
	}
	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.INFO);
	}
	/**
	 * 
	 * TODO Scenario1 DataField to DataField -- under KColl
	 * ExpressionMapperTest.java void
	 */
	@Test
	public void testKCollDataToKCollData() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			from.setValueAt(
					"OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testField",
					"testKCollDataToKCollData");
			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testKCollDataToKCollDataFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(
					"testKCollDataToKCollData",
					to.getValueAt("OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testField"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollDataToICollData() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			from.setValueAt("OneLevelIColl.0.0", "testICollDataToICollData");
			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollDataToICollDataFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("testICollDataToICollData",
					to.getValueAt("OneLevelIColl.0.0"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollDataToData() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			from.setValueAt("TwoLevelIColl.0", "testICollDataToData");
			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollDataToDataFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("testICollDataToData",
					to.getValueAt("TwoLevelIColl.0"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testFieldDataToIColl() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			from.setValueAt("testField", "testFieldDataToIColl");
			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testFieldDataToICollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("testFieldDataToIColl",
					to.getValueAt("TwoLevelIColl.0"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testKCollToKColl() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollCtx");
			from.setValueAt(
					"OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testField",
					"testKCollToKColl");
			from.setValueAt(
					"OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testInteger",
					123);

			Context to = ContextFactory.createContext("testMultiLevelKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testKCollToKCollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(
					"testKCollToKColl",
					to.getValueAt("OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testField"));
			Assert.assertEquals(
					123,
					to.getValueAt("OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testInteger"));
			Assert.assertEquals(
					null,
					to.getValueAt("OneLevelKColl.TwoLevelKColl.ThirdLevelKColl.FourthLevelKColl.testBigInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testKCollAToKCollB() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollACtx");
			from.setValueAt("OneLevelKCollA.testInteger1", 111);
			from.setValueAt("OneLevelKCollA.testInteger2", 222);

			Context to = ContextFactory
					.createContext("testMultiLevelKCollBCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testKCollAToKCollBFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(111,
					to.getValueAt("OneLevelKCollB.testInteger1"));
			Assert.assertEquals(null,
					to.tryGetValueAt("OneLevelKCollB.testInteger2"));
			Assert.assertEquals(3, to.getValueAt("OneLevelKCollB.testInteger3"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testSameElementKCollAAToKCollBB() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelKCollACtx");
			from.setValueAt("OneLevelKCollAA.testInteger1", 111);
			from.setValueAt("OneLevelKCollAA.testInteger2", 222);

			Context to = ContextFactory
					.createContext("testMultiLevelKCollBCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testSameElementKCollAAToKCollBBFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(111,
					to.getValueAt("OneLevelKCollBB.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("OneLevelKCollBB.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendDefault() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			from.setValueAt("testOneLevelIColl.0.testInteger1", 111000);
			from.setValueAt("testOneLevelIColl.0.testInteger2", 222000);
			IndexedCollection iColl = (IndexedCollection) from
					.getElementAt("testOneLevelIColl");
			KeyedCollection kColl = (KeyedCollection) DataElement
					.readObject("OneLevelKCollAA");
			kColl.setValueAt("testInteger1", 111);
			kColl.setValueAt("testInteger2", 222);
			iColl.addElement(kColl);
			Context to = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendDefaultFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(111000,
					to.getValueAt("testOneLevelIColl.0.testInteger1"));
			Assert.assertEquals(222000,
					to.getValueAt("testOneLevelIColl.0.testInteger2"));
			Assert.assertEquals(111,
					to.getValueAt("testOneLevelIColl.1.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testOneLevelIColl.1.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendTrue() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			from.setValueAt("testOneLevelIColl.0.testInteger1", 111000);
			from.setValueAt("testOneLevelIColl.0.testInteger2", 222000);
			IndexedCollection iColl = (IndexedCollection) from
					.getElementAt("testOneLevelIColl");
			KeyedCollection kColl = (KeyedCollection) DataElement
					.readObject("OneLevelKCollAA");
			kColl.setValueAt("testInteger1", 111);
			kColl.setValueAt("testInteger2", 222);
			iColl.addElement(kColl);

			Context to = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendTrueFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1,
					to.getValueAt("testOneLevelIColl.0.testInteger1"));
			Assert.assertEquals(2,
					to.getValueAt("testOneLevelIColl.0.testInteger2"));
			Assert.assertEquals(111000,
					to.getValueAt("testOneLevelIColl.1.testInteger1"));
			Assert.assertEquals(222000,
					to.getValueAt("testOneLevelIColl.1.testInteger2"));
			Assert.assertEquals(111,
					to.getValueAt("testOneLevelIColl.2.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testOneLevelIColl.2.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendFalse() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			IndexedCollection iColl = (IndexedCollection) from
					.getElementAt("testOneLevelIColl");
			KeyedCollection kColl = (KeyedCollection) DataElement
					.readObject("OneLevelKCollAA");
			kColl.setValueAt("testInteger1", 111);
			kColl.setValueAt("testInteger2", 222);
			iColl.addElement(kColl);

			Context to = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendFalseFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1,
					to.getValueAt("testOneLevelIColl.0.testInteger1"));
			Assert.assertEquals(2,
					to.getValueAt("testOneLevelIColl.0.testInteger2"));
			Assert.assertEquals(111,
					to.getValueAt("testOneLevelIColl.1.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testOneLevelIColl.1.testInteger2"));
			Assert.assertEquals(null,
					to.tryGetValueAt("testOneLevelIColl.2.testInteger1"));
			Assert.assertEquals(null,
					to.tryGetValueAt("testOneLevelIColl.2.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendDefaultDiffName() {
		try {
			Context from = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			from.setValueAt("testOneLevelIColl.0.testInteger1", 111000);
			from.setValueAt("testOneLevelIColl.0.testInteger2", 222000);
			IndexedCollection iColl = (IndexedCollection) from
					.getElementAt("testOneLevelIColl");
			KeyedCollection kColl = (KeyedCollection) DataElement
					.readObject("OneLevelKCollAA");
			kColl.setValueAt("testInteger1", 111);
			kColl.setValueAt("testInteger2", 222);
			iColl.addElement(kColl);
			Context to = ContextFactory
					.createContext("testMultiLevelICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendDefaultDiffNameFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(111000,
					to.getValueAt("NewIColl.0.testInteger1"));
			Assert.assertEquals(222000,
					to.getValueAt("NewIColl.0.testInteger2"));
			Assert.assertEquals(111, to.getValueAt("NewIColl.1.testInteger1"));
			Assert.assertEquals(222, to.getValueAt("NewIColl.1.testInteger2"));
			Assert.assertEquals(null,
					to.tryGetValueAt("NewIColl.2.testInteger1"));
			Assert.assertEquals(null,
					to.tryGetValueAt("NewIColl.2.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendTrueIgnore() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			from.setValueAt("testPartOfAIColl.0.testInteger1", 111);
			from.setValueAt("testPartOfAIColl.0.testInteger2", 222);
			Context to = ContextFactory
					.createContext("testPartOfBICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendTrueIgnoreFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1,
					to.getValueAt("testPartOfBIColl.0.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testPartOfBIColl.0.testInteger3"));

			Assert.assertEquals(111,
					to.getValueAt("testPartOfBIColl.1.testInteger1"));
			Assert.assertEquals(null,
					to.getValueAt("testPartOfBIColl.1.testInteger3"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendTrueTrue() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			from.setValueAt("testPartOfAIColl.0.testInteger1", 111);
			from.setValueAt("testPartOfAIColl.0.testInteger2", 222);
			Context to = ContextFactory
					.createContext("testPartOfBICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendTrueTrueFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(1,
					to.getValueAt("testPartOfBIColl.0.testInteger1"));
			Assert.assertEquals(3,
					to.getValueAt("testPartOfBIColl.0.testInteger3"));
			Assert.assertEquals(111,
					to.getValueAt("testPartOfBIColl.1.testInteger1"));
			Assert.assertEquals(null,
					to.getValueAt("testPartOfBIColl.1.testInteger3"));
			Assert.assertEquals(null,
					to.getValueAt("testPartOfBIColl.2.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testPartOfBIColl.2.testInteger3"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendFalseIgnore() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			from.setValueAt("testPartOfAIColl.0.testInteger1", 111);
			from.setValueAt("testPartOfAIColl.0.testInteger2", 222);
			Context to = ContextFactory
					.createContext("testPartOfBICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendFalseIgnoreFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(111,
					to.getValueAt("testPartOfBIColl.0.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testPartOfBIColl.0.testInteger3"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAppendFalseFalse() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			from.setValueAt("testPartOfAIColl.0.testInteger1", 111);
			from.setValueAt("testPartOfAIColl.0.testInteger2", 222);
			Context to = ContextFactory
					.createContext("testPartOfBICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAppendFalseFalseFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(1,
					to.getValueAt("testPartOfBIColl.0.testInteger1"));
			Assert.assertEquals(222,
					to.getValueAt("testPartOfBIColl.0.testInteger3"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testICollToICollAlias() {
		try {
			Context from = ContextFactory
					.createContext("testThreeICollsInColl1Ctx");
			from.setValueAt("IColl1.0.testInteger1", 101);
			from.setValueAt("IColl1.0.testInteger2", 102);
			from.setValueAt("IColl2.0.testInteger1", 201);
			from.setValueAt("IColl2.0.testInteger2", 202);
			from.setValueAt("IColl3.0.testInteger1", 301);
			from.setValueAt("IColl3.0.testInteger2", 302);

			Context to = ContextFactory
					.createContext("testThreeICollsInColl2Ctx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testICollToICollAliasFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(101, to.getValueAt("IColl4.1.testInteger1"));
			Assert.assertEquals(102, to.getValueAt("IColl4.1.testInteger2"));
			Assert.assertEquals(201, to.getValueAt("IColl5.1.testInteger1"));
			Assert.assertEquals(202, to.getValueAt("IColl5.1.testInteger2"));
			Assert.assertEquals(301, to.getValueAt("IColl6.1.testInteger1"));
			Assert.assertEquals(302, to.getValueAt("IColl6.1.testInteger2"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	@Test
	public void testPartOfICollAppendTrue() {
		try {
			Context from = ContextFactory
					.createContext("testPartOfAICollKCollCtx");
			from.setValueAt("testPartOfAIColl.0.testInteger1", 101);
			from.setValueAt("testPartOfAIColl.0.testInteger2", 102);
			Context to = ContextFactory
					.createContext("testPartOfBICollKCollCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement
					.readObject("testPartOfICollAppendTrueFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(1,
					to.getValueAt("testPartOfBIColl.0.testInteger1"));
			Assert.assertEquals(3,
					to.getValueAt("testPartOfBIColl.0.testInteger3"));
			Assert.assertEquals(101,
					to.getValueAt("testPartOfBIColl.1.testInteger1"));
			Assert.assertEquals(null,
					to.tryGetValueAt("testPartOfBIColl.1.testInteger2"));
			Assert.assertEquals(null,
					to.getValueAt("testPartOfBIColl.1.testInteger3"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: "
					+ e);
		}
	}

	

	
}
