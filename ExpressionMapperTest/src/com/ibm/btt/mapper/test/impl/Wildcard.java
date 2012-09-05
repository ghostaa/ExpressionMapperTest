package com.ibm.btt.mapper.test.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class Wildcard extends MapperTestCase {

	public void testOneLevelFieldIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelFieldIColl.1"));
	}
	
	public void testOneLevelFieldRootIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelFieldRootFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelFieldIColl.1"));
	}
	
	public void testFieldDataRootMapping() throws Exception {
		Context from = ContextFactory.createContext("fieldDataCtx");
		Context to = ContextFactory.createContext("embeddedKCollCtx");
		from.setValueAt("testField", "234565789");
		from.setValueAt("testBigInteger", "123456578");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldDataRootFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("234565789", to.getValueAt("fieldData.testField"));
		Assert.assertEquals(new BigInteger("123456578"), to.getValueAt("fieldData.testBigInteger"));
	}
	
	public void testFieldDataRootMappingReverse() throws Exception {
		Context to = ContextFactory.createContext("fieldDataCtx");
		Context from = ContextFactory.createContext("embeddedKCollCtx");
		from.setValueAt("fieldData.testField", "234565789");
		from.setValueAt("fieldData.testBigInteger", "123456578");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldDataRootFmtReverse");
		fmt.mapContents(from, to);
		Assert.assertEquals("234565789", to.getValueAt("testField"));
		Assert.assertEquals(new BigInteger("123456578"), to.getValueAt("testBigInteger"));
	}
	
	public void testOneLevelKCollIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelKCollFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		DateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals("20010205", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.0.testKColl2.testDate")));
		Assert.assertEquals("20001231", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.1.testKColl2.testDate")));
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testOneLevelKCollICollWildcard() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelKCollFmtWildcard");
		fmt.mapContents(from, to);
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		DateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals("20010205", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.0.testKColl2.testDate")));
		Assert.assertEquals("20001231", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.1.testKColl2.testDate")));
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testTwoLevelIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("twoLevelICollFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));

		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testBigInteger"));
	}
	
	public void testTwoLevelICollWildcard() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("twoLevelICollFmtWildcard");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));

		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testBigInteger"));
	}
	
	public void testTwoLevelICollWildcardNested() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("twoLevelICollFmtWildcardNested");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));

		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testBigInteger"));
	}
	
	public void testFiveLevelIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fiveLevelICollFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.1.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.1.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.1.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.1.testKColl2.testField"));
	}
	
	public void testFiveLevelICollWildcard() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fiveLevelICollFieldFmtWildcard");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.1.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.1.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.1.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.1.testKColl2.testField"));
	}
	
	public void testFiveLevelICollWildcardNested() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fiveLevelICollFieldFmtWildcardNested");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.1.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.1.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.1.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.1.testKColl2.testField"));
	}
}
