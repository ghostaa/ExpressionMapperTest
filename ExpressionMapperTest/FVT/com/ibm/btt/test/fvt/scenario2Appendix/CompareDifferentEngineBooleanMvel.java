package com.ibm.btt.test.fvt.scenario2Appendix;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class CompareDifferentEngineBooleanMvel   extends CommonTestCase{
	
	
	//Boolean and 'abc'
	@Test
	public void testRearBooleanAndABCSimpleTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("rearBooleanAndABCSimpleTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals("nullabc", target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(false, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAndABCComparisonOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAndABCComparisonOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			Assert.assertEquals(null, target.getValueAt("testString5"));
			Assert.assertEquals(null, target.getValueAt("testString6"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			Assert.assertEquals(null, target.getValueAt("testNumber5"));
			Assert.assertEquals(null, target.getValueAt("testNumber6"));
			
			
			Assert.assertEquals(false, target.getValueAt("testBoolean1"));
			Assert.assertEquals(true, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
			Assert.assertEquals(false, target.getValueAt("testBoolean5"));
			Assert.assertEquals(false, target.getValueAt("testBoolean6"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAndABCLogicalOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAndABCLogicalOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(null, target.getValueAt("testBoolean2"));
			Assert.assertEquals(null, target.getValueAt("testBoolean3"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	//Boolean and 123
	@Test
	public void testRearBooleanAnd123SimpleTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("rearBooleanAnd123SimpleTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals("null123", target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(false, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAnd123ComparisonOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAnd123ComparisonOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			Assert.assertEquals(null, target.getValueAt("testString5"));
			Assert.assertEquals(null, target.getValueAt("testString6"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			Assert.assertEquals(null, target.getValueAt("testNumber5"));
			Assert.assertEquals(null, target.getValueAt("testNumber6"));
			
			
			Assert.assertEquals(false, target.getValueAt("testBoolean1"));
			Assert.assertEquals(true, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
			Assert.assertEquals(false, target.getValueAt("testBoolean5"));
			Assert.assertEquals(false, target.getValueAt("testBoolean6"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAnd123LogicalOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAnd123LogicalOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(null, target.getValueAt("testBoolean2"));
			Assert.assertEquals(null, target.getValueAt("testBoolean3"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	
	
	
	//Boolean and true
	@Test
	public void testRearBooleanAndTrueSimpleTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("rearBooleanAndTrueSimpleTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals("nulltrue", target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(false, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAndTrueComparisonOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAndTrueComparisonOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			Assert.assertEquals(null, target.getValueAt("testString4"));
			Assert.assertEquals(null, target.getValueAt("testString5"));
			Assert.assertEquals(null, target.getValueAt("testString6"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			Assert.assertEquals(null, target.getValueAt("testNumber4"));
			Assert.assertEquals(null, target.getValueAt("testNumber5"));
			Assert.assertEquals(null, target.getValueAt("testNumber6"));
			
			
			Assert.assertEquals(false, target.getValueAt("testBoolean1"));
			Assert.assertEquals(true, target.getValueAt("testBoolean2"));
			Assert.assertEquals(false, target.getValueAt("testBoolean3"));
			Assert.assertEquals(false, target.getValueAt("testBoolean4"));
			Assert.assertEquals(false, target.getValueAt("testBoolean5"));
			Assert.assertEquals(false, target.getValueAt("testBoolean6"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testBooleanAndTrueLogicalOperatorsTo3types() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("BooleanAndTrueLogicalOperatorsTo3types");
			
			fmt.mapContents(source, target);
			Assert.assertEquals(null, target.getValueAt("testString1"));
			Assert.assertEquals(null, target.getValueAt("testString2"));
			Assert.assertEquals(null, target.getValueAt("testString3"));
			
			Assert.assertEquals(null, target.getValueAt("testNumber1"));
			Assert.assertEquals(null, target.getValueAt("testNumber2"));
			Assert.assertEquals(null, target.getValueAt("testNumber3"));
			
			
			Assert.assertEquals(null, target.getValueAt("testBoolean1"));
			Assert.assertEquals(null, target.getValueAt("testBoolean2"));
			Assert.assertEquals(null, target.getValueAt("testBoolean3"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	
	
	
	private Context composeSourceContext() throws Exception {
		Context from = ContextFactory.createContext("funcFieldDataCtx");
		from.setValueAt("testField", "12345657890");
		from.setValueAt("testString", "10");
		from.setValueAt("testByte", new Byte("5"));
		from.setValueAt("testShort", new Short("123"));
		from.setValueAt("testInteger", 1234560);
		from.setValueAt("testLong", new Long("123123"));
		from.setValueAt("testFloat", new Float("5"));
		from.setValueAt("testDouble", new Double("4.5"));
		from.setValueAt("testNumber", "5");
		from.setValueAt("testBigInteger", new BigInteger("12345670"));
		from.setValueAt("testBigDecimal", new BigDecimal("0.123456789"));
		from.setValueAt("testBoolean", true);

		return from;
	}

	private Context divisionSourceContext() throws Exception {
		Context from = ContextFactory.createContext("funcFieldDataCtx");
		from.setValueAt("testString", "12");
		from.setValueAt("testInteger", 3);
		from.setValueAt("testIntegerZero", 0);

		return from;
	}
	
}
