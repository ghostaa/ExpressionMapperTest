package com.ibm.btt.mapper.test.impl;

import java.math.BigInteger;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class WildcardOperator extends MapperTestCase {

	public void testOneLevelFieldICollOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_oneLevelFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1_concat", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("oneLevelFieldIColl.1"));
	}
	
	public void testOneLevelKCollICollOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_oneLevelKCollFmt");
		fmt.mapContents(from, to);
		// Number will auto-convert to String in mapping logic. This is for PMR JR40268
		Assert.assertEquals("1234579.0", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("7654333.0", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testOneLevelKCollICollNestedOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_oneLevelKCollFmtNested");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestString1TestField11234567", to.getValueAt("oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestString2TestField27654321", to.getValueAt("oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testTwoLevelICollOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_twoLevelICollFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));

		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testBigInteger"));
		Assert.assertEquals(new BigInteger((1234567+12) + ""), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testBigInteger"));
		Assert.assertEquals(new BigInteger((7654321+12) + ""), to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testBigInteger"));
	}
	
	public void testTwoLevelICollTwoParamsOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_twoLevelIColl_twoParamsFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("1234567TestField1", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("7654321TestField2", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("1234567TestField1", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("7654321TestField2", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));
	}
	
	public void testFiveLevelICollOper() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardTargetContext(from);
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_fiveLevelICollFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1_concat", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.0.testIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("fiveLevelIColl.1.0.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("fiveLevelIColl.0.1.testIColl.0.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("fiveLevelIColl.0.0.testIColl.1.testIColl.0.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField2_concat", to.getValueAt("fiveLevelIColl.0.0.testIColl.0.testIColl.1.testIColl.1.testKColl2.testField"));
	}
	
}
