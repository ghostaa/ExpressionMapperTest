package com.ibm.btt.mapper.test.impl;

import java.math.BigInteger;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.mapper.test.MapperTestCase;

public class Type extends MapperTestCase {

	private Context composeTypedContext() throws Exception {
		Context context = ContextFactory.createContext("testTypedCtx");
		context.setValueAt("testKCollType.testString", "TestString");
		context.setValueAt("testKCollType.testBigInteger", "1234567");
		context.setValueAt("testKCollType.testField", "TestField");
		IndexedCollection iColl = (IndexedCollection)context.getElementAt("testICollType");
		iColl.removeAll();
		KeyedCollection kColl = (KeyedCollection)iColl.createElement(true);
		kColl.setValueAt("testString", "TestString1");
		kColl.setValueAt("testBigInteger", "1234567");
		kColl.setValueAt("testField", "TestField1");
		iColl.addElement(kColl);
		kColl = (KeyedCollection)iColl.createElement(true);
		kColl.setValueAt("testString", "TestString2");
		kColl.setValueAt("testBigInteger", "7654321");
		kColl.setValueAt("testField", "TestField2");
		iColl.addElement(kColl);
		return context;
	}
	
	private Context composeFieldDataContext() throws Exception {
		Context from = ContextFactory.createContext("fieldDataCtx");
		from.setValueAt("testField", "testField");
		from.setValueAt("testBigInteger", "123456578");
		from.setValueAt("testString", "Monkey");
		return from;
	}
	
	public void testTypeRootFmt() throws Exception {
		Context to = composeTypedContext();
		Context from = composeFieldDataContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeRootFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("Monkey", to.getValueAt("testICollType.1.testString"));
		Assert.assertEquals(new BigInteger("123456578"), to.getValueAt("testICollType.1.testBigInteger"));
		Assert.assertEquals("testField", to.getValueAt("testICollType.1.testField"));
	}
	
	public void testTypeRootFmtReverse() throws Exception {
		Context from = composeTypedContext();
		Context to = composeFieldDataContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeRootFmtReverse");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestString", to.getValueAt("testString"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("testBigInteger"));
		Assert.assertEquals("TestField", to.getValueAt("testField"));
	}
	
	public void testTypeICollFmt() throws Exception {
		Context to = ContextFactory.createContext("testTypedCtx");
		Context from = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeICollFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestString1", to.getValueAt("testICollType.0.testString"));
		Assert.assertEquals("TestString2", to.getValueAt("testICollType.1.testString"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("testICollType.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("testICollType.1.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("testICollType.0.testField"));
	}
	
	public void testTypeICollFmtReverse() throws Exception {
		Context from = composeTypedContext();
		Context to = ContextFactory.createContext("wildcardMainCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeICollFmtReverse");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestString1", to.getValueAt("oneLevelKCollIColl.0.testString"));
		Assert.assertEquals("TestString2", to.getValueAt("oneLevelKCollIColl.1.testString"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
	}
	
	public void testTypeICollFieldFmt() throws Exception {
		Context to = ContextFactory.createContext("testTypedCtx");
		Context from = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeICollFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("testICollType.0.testString"));
		Assert.assertEquals("TestField2", to.getValueAt("testICollType.1.testString"));
		Assert.assertEquals("2001/02/05", to.getValueAt("testICollType.0.testField"));
		Assert.assertEquals("2000/12/31", to.getValueAt("testICollType.1.testField"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("testICollType.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("testICollType.1.testBigInteger"));
	}
	
	public void testTypeICollFieldFmtReverse() throws Exception {
		Context from = composeTypedContext();
		Context to = ContextFactory.createContext("wildcardMainCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("typeICollFieldFmtReverse");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestString1", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestString2", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
	}
}
