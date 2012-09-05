package com.ibm.btt.mapper.test.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.mapper.test.MapperTestCase;

public class WildcardFunctionFieldTarget extends MapperTestCase {

	public void testSumWildcardFieldTarget() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = ContextFactory.createContext("singleFieldCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldTarget_oneLevelFieldFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("8888888", to.tryGetValueAt("testField"));
	}
	
	public void testCountWildcardFieldTarget() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = ContextFactory.createContext("singleFieldCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldTarget_oneLevelFieldCountFmt");
		fmt.mapContents(from, to);
		Assert.assertEquals("1", to.tryGetValueAt("testField"));
	}
	
	public void testDateWildcardFieldTarget() throws Exception {
		Context from = composeWildcardMainContext();
		Context to = ContextFactory.createContext("singleFieldCtx");
		DataMapperFormat fmt = (DataMapperFormat)FormatElement.readObject("fieldTarget_oneLevelFieldDateFmt");
		fmt.mapContents(from, to);
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy/MM/dd");
		Assert.assertEquals("2001/02/05", dateFmt.format((Date)to.tryGetValueAt("testField")));
	}
}
