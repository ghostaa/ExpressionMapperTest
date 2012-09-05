package com.ibm.btt.mapper.test.impl;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class Expression extends MapperTestCase {

	public void testFieldDataMappingOper() throws Exception {
		Context from = composeSourceContext();
		Context to = ContextFactory.createContext("fieldDataCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_fieldDataFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("1234579.0", to.getValueAt("testString"));
		Assert.assertEquals("testField1_concat", to.getValueAt("testField"));
	}
	
	public void testFieldDataMappingTwoParamsOper() throws Exception {
		Context from = composeSourceContext();
		Context to = ContextFactory.createContext("fieldDataCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("oper_fieldDataFmtTwoParams");
		fmt.mapContents(from, to);
		Assert.assertEquals("testField1_1234567", to.getValueAt("testField"));
	}
	
	public void testFieldDataMappingFunc() throws Exception {
		Context from = composeSourceContext();
		Context to = ContextFactory.createContext("fieldDataCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("func_fieldDataFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("1234579.0", to.getValueAt("testString"));
		Assert.assertEquals("testField1_concat", to.getValueAt("testField"));
	}
	
	public void testFieldDataMappingTwoParamsFunc() throws Exception {
		Context from = composeSourceContext();
		Context to = ContextFactory.createContext("fieldDataCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("func_fieldDataFmtTwoParams");
		fmt.mapContents(from, to);
		Assert.assertEquals("testField1_1234567", to.getValueAt("testField"));
	}
	
	private Context composeSourceContext() throws Exception {
		Context from = ContextFactory.createContext("fieldDataCtx");
		from.setValueAt("testField", "234565789");
		from.setValueAt("testBigInteger", "1234567");
		from.setValueAt("testString", "testField1");
		return from;
	}
}
