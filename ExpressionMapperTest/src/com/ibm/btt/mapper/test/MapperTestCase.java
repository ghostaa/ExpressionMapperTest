package com.ibm.btt.mapper.test;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataElement;
import com.ibm.btt.base.DataField;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.base.Tag;
import com.ibm.btt.config.InitManager;

public abstract class MapperTestCase extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		InitManager.reset("jar:///definitions/btt.xml");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		InitManager.cleanUp();
	}
	
	protected Context composeWildcardMainContext() throws Exception {
		Context context = ContextFactory.createContext("wildcardMainCtx");
		
		IndexedCollection oneLevelFieldIColl = (IndexedCollection)context.tryGetElementAt("oneLevelFieldIColl");
		DataField testField = (DataField)oneLevelFieldIColl.createElement(false);
		testField.setValue("TestField1");
		oneLevelFieldIColl.addElement(testField);
		testField = (DataField)oneLevelFieldIColl.createElement(false);
		testField.setValue("TestField2");
		oneLevelFieldIColl.addElement(testField);
		
		IndexedCollection oneLevelKCollIColl = (IndexedCollection)context.tryGetElementAt("oneLevelKCollIColl");
		decorateOneLevelKCollIColl(oneLevelKCollIColl);
		
		IndexedCollection twoLevelIColl = (IndexedCollection)context.tryGetElementAt("twoLevelIColl");
		KeyedCollection testKColl = (KeyedCollection)twoLevelIColl.createElement(true);
		testKColl.setValueAt("testField", "TestField1");
		decorateOneLevelKCollIColl((IndexedCollection)testKColl.tryGetElementAt("oneLevelKCollIColl"));
		twoLevelIColl.addElement(testKColl);
		testKColl = (KeyedCollection)twoLevelIColl.createElement(true);
		testKColl.setValueAt("testField", "TestField2");
		decorateOneLevelKCollIColl((IndexedCollection)testKColl.tryGetElementAt("oneLevelKCollIColl"));
		twoLevelIColl.addElement(testKColl);
		
		IndexedCollection fiveLevelIColl = (IndexedCollection)context.tryGetElementAt("fiveLevelIColl");
		decorateFiveLevelIColl(fiveLevelIColl);
		return context;
	}
	
	protected Context composeWildcardTargetContext(Context source) throws Exception {
		return ContextFactory.createContext("wildcardMainCtx");
	}
	
	private void decorateFiveLevelIColl(IndexedCollection fiveLevelIColl) throws Exception {
		IndexedCollection testIColl = (IndexedCollection)fiveLevelIColl.createElement(true);
		decorateNestedLevelIColl(testIColl);
		fiveLevelIColl.addElement(testIColl);
		testIColl = (IndexedCollection)fiveLevelIColl.createElement(true);
		decorateNestedLevelIColl(testIColl);
		fiveLevelIColl.addElement(testIColl);
	}

	private void decorateNestedLevelIColl(IndexedCollection testIColl) throws Exception {
		Tag tag = testIColl.getElementSubTag();
		boolean continueNested = false;
		for(Object subtag : tag.getSubTags()) {
			if ("iColl".equals(((Tag)subtag).getName())) {
				continueNested = true;
				break;
			}
		}
		if (continueNested) {
			KeyedCollection testKColl = (KeyedCollection)testIColl.createElement(true);
			testKColl.setValueAt("testField", "TestField1");
			decorateNestedLevelIColl((IndexedCollection)testKColl.tryGetElementAt("testIColl"));
			testIColl.addElement(testKColl);
			testKColl = (KeyedCollection)testIColl.createElement(true);
			testKColl.setValueAt("testField", "TestField2");
			decorateNestedLevelIColl((IndexedCollection)testKColl.tryGetElementAt("testIColl"));
			testIColl.addElement(testKColl);
		} else {
			decorateOneLevelKCollIColl(testIColl);
		}
	}

	private void decorateOneLevelKCollIColl(IndexedCollection oneLevelKCollIColl) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2001, 1, 5);
		KeyedCollection testKColl = composeTestKColl(calendar.getTime(), new BigInteger("1234567"), "TestString1", "TestField1");
		oneLevelKCollIColl.addElement(testKColl);
		calendar = Calendar.getInstance();
		calendar.set(2000, 11, 31);
		testKColl = composeTestKColl(calendar.getTime(), new BigInteger("7654321"), "TestString2", "TestField2");
		oneLevelKCollIColl.addElement(testKColl);
	}
	
	private KeyedCollection composeTestKColl(Date date, BigInteger bigInteger, String testString, String testField) throws Exception {
		KeyedCollection testKColl = (KeyedCollection)DataElement.readObject("testKColl");
		testKColl.setValueAt("testBigInteger", bigInteger);
		testKColl.setValueAt("testString", testString);
		testKColl.setValueAt("testKColl2.testDate", date);
		testKColl.setValueAt("testKColl2.testField", testField);
		return testKColl;
	}

}
