package com.ibm.btt.test.fvt.scenario2;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class GlobalFuncTest extends CommonTestCase {

	@Test
	public void testGlobalReplaceStringFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			from.setValueAt("funcTestGlobalStringKColl.testString", "aaababbccc");
			from.setValueAt("funcTestGlobalStringKColl.testStringOld", "a");
			from.setValueAt("funcTestGlobalStringKColl.testStringNew", "m");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_replaceStringFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("mmmbmbbccc", to.getValueAt("funcTestGlobalStringKColl.testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testGlobalNaturalDaysBetweenDateFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			Date dateNew = new GregorianCalendar(2012, 2, 8).getTime();
			Date dateOld = new GregorianCalendar(2012, 2, 1).getTime();
			from.setValueAt("funcTestGlobalDateKColl.testDateNew", dateNew);
			from.setValueAt("funcTestGlobalDateKColl.testDateOld", dateOld);
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_naturalDaysBetweenDateFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(7, to.getValueAt("funcTestGlobalIntegerKColl.testInteger1"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testGlobalmultiplyDoubleFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			from.setValueAt("funcTestGlobalDoubleKColl.testDouble1", new Double(4.0));
			from.setValueAt("funcTestGlobalDoubleKColl.testDouble2", new Double(2.5));
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_multiplyDoubleFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new Double("10.0"), to.getValueAt("funcTestGlobalDoubleKColl.testDouble1"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testGlobalTableSizeICollFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			from.setValueAt("funcTestGlobalIntegerIColl.0.testInteger1", 11);
			from.setValueAt("funcTestGlobalIntegerIColl.1.testInteger1", 11);
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_tableSizeICollFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(2, to.getValueAt("funcTestGlobalIntegerKColl.testInteger1"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testGlobalCustomerKCollToStringFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_customer_kCollToStringFmt");
			fmt.mapContents(from, to);
			String objectString = "testField1:< field id=\"testField1\" description=\"\" />;testField2:< field id=\"testField2\" description=\"\" />";
			Assert.assertEquals(objectString, to.getValueAt("funcTestGlobalStringKColl.testString"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testDataFieldToKCollFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			from.setValueAt("testField", "myTest");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_datafieldToKCollFmt");
			fmt.mapContents(from, to);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testDataFieldToICollFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			from.setValueAt("testField", "myTest");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_datafieldToICollFmt");
			fmt.mapContents(from, to);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testCustomerFunctionToKCollFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_customerfunction_toKCollFmt");
			fmt.mapContents(from, to);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testCustomerFunctionToICollFunction() {
		try {
			Context from = ContextFactory.createContext("testGlobalKColl");
			Context to = ContextFactory.createContext("testGlobalKColl");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_customerfunction_toICollFmt");
			fmt.mapContents(from, to);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}
}
