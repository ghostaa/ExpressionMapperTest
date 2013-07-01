package com.ibm.btt.mapper.test.impl;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class ExpressionWildardTarget extends MapperTestCase {
	
	public void testOneLevelFieldConstantIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelFieldConstantFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("123", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("123", to.getValueAt("oneLevelFieldIColl.1"));
	}
	
	public void testOneLevelFieldExpIColl() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oneLevelFieldExpFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1_concat", to.getValueAt("oneLevelFieldIColl.0"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("oneLevelFieldIColl.1"));
	}
	
	public void testTwoLevelICollExpFmt() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = composeWildcardMainContext();
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("twoLevelICollExpFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.0.oneLevelKCollIColl.1.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.0.testKColl2.testField"));
		Assert.assertEquals("TestField1_concat", to.getValueAt("twoLevelIColl.1.oneLevelKCollIColl.1.testKColl2.testField"));
	}
}
