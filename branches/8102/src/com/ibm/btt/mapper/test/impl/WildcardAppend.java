package com.ibm.btt.mapper.test.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.mapper.test.MapperTestCase;

public class WildcardAppend extends MapperTestCase {

	public void testOneLevelFieldIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oneLevelFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelFieldIColl.2"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelFieldIColl.3"));
	}
	
	public void testOneLevelFieldICollFunc() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_func_oneLevelFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelFieldIColl.1"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("oneLevelFieldIColl.2"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("oneLevelFieldIColl.3"));
	}
	
	public void testOneLevelKCollIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oneLevelKCollFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals(2, ((IndexedCollection)to.getElementAt("oneLevelKCollIColl")).size());
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		DateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals("20010205", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.0.testKColl2.testDate")));
		Assert.assertEquals("20001231", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.1.testKColl2.testDate")));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testOneLevelKCollICollReverse() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		Assert.assertEquals(2, ((IndexedCollection)to.getElementAt("oneLevelKCollIColl")).size());
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oneLevelKCollFmtReverse");
		fmt.mapContents(from, to);
		Assert.assertEquals(4, ((IndexedCollection)to.getElementAt("oneLevelKCollIColl")).size());
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.2.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.3.testBigInteger"));
		DateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals("20010205", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.0.testKColl2.testDate")));
		Assert.assertEquals("20001231", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.1.testKColl2.testDate")));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.2.testKColl2.testDate"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.3.testKColl2.testDate"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.2.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.3.testKColl2.testField"));
	}
	
	public void testOneLevelKCollICollIgnore() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		Assert.assertEquals("TestField1", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oneLevelKCollFmtIgnore");
		fmt.mapContents(from, to);
		Assert.assertEquals(2, ((IndexedCollection)to.getElementAt("oneLevelKCollIColl")).size());
		Assert.assertEquals(new BigInteger("1234567"), to.getValueAt("oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger("7654321"), to.getValueAt("oneLevelKCollIColl.1.testBigInteger"));
		DateFormat dateFmt = new SimpleDateFormat("yyyyMMdd");
		Assert.assertEquals("20010205", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.0.testKColl2.testDate")));
		Assert.assertEquals("20001231", dateFmt.format((Date)to.getValueAt("oneLevelKCollIColl.1.testKColl2.testDate")));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testTwoLevelICollOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		
		Assert.assertEquals("TestString1", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testString"));
		Assert.assertEquals("TestField1", to.getValueAt("twoLevelIColl.0.testField"));
		
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oper_twoLevelICollFmt");
		fmt.mapContents(from, to);
		
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testString"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.0.testField"));
		
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));

		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testBigInteger"));
	}
	
	public void testTwoLevelICollOperMax() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("append_oper_twoLevelICollFmt_Max");
		fmt.mapContents(from, to);
		
		Assert.assertEquals(6, ((IndexedCollection)to.getElementAt("twoLevelIColl")).size());
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.2.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.2.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.3.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.3.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.4.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.4.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.5.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.5.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.2.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.2.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.3.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.3.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.4.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.4.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.5.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(null, to.getValueAt("twoLevelIColl.5.oneLevelKCollIColl.1.testBigInteger"));
	}
}
