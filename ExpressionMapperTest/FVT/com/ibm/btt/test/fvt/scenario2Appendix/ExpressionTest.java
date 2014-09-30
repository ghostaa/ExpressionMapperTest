package com.ibm.btt.test.fvt.scenario2Appendix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.ContextFactory;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.DataMapperFormat;
import com.ibm.btt.base.FormatElement;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ExpressionTest extends CommonTestCase {

	@Test
	public void testFuncStringAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_string_addtionFmt");
			Date date = new Date();
			from.setValueAt("testDate", date);
			fmt.mapContents(from, to);
			Assert.assertEquals(101234560, to.getValueAt("testInteger"));
			Assert.assertEquals("100.123456789", to.getValueAt("testBigDecimal").toString());
			Assert.assertEquals("10" + date, to.getValueAt("testDateField"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncStringSubtractionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_string_subtractionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("-12345660", to.getValueAt("testBigInteger").toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncStringMultiplicationOper() {
		try {

			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_string_multiplicationFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals("1.234567890", to.getValueAt("testBigDecimal").toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncStringDivisionOper() {
		try {

			Context from = divisionSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_string_divisionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(4, to.getValueAt("testInteger"));

		} catch (Exception e) {
			Assert.assertTrue(!e.getMessage().contains("divide by zero"));
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncStringDoubleEqualSignOper() {
		try {
			Context from = ContextFactory.createContext("funcFieldDataCtx");
			from.setValueAt("testBigInteger", "112233");
			from.setValueAt("testString", "112233");
			from.setValueAt("testInteger", 112233);
			from.setValueAt("testBigDecimal", "112233.00000000");

			Context to = ContextFactory.createContext("funcTargetBooleanDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_string_doubleEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testIntegerBoolean"));
			Assert.assertTrue((Boolean) to.getValueAt("testBigIntegerBoolean"));
			Assert.assertTrue((Boolean) to.getValueAt("testBigDecimalBoolean"));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testFuncBooleanDoubleEqualSignOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 1);
			from.setValueAt("testString", "true");
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_boolean_doubleEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
			//js engine case
			//Assert.assertFalse((Boolean) to.getValueAt("testField"));
			//mvel engine case
			Assert.assertTrue((Boolean) to.getValueAt("testField"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncBooleanNonEqualSignOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 0);
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_boolean_nonEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncBooleanNotOper() {
		try {

			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_boolean_notFmt");
			fmt.mapContents(from, to);
			Assert.assertFalse((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}
	//js engine,now it has changed to mvel,so ignore this case
	@Ignore
	public void testFuncBooleanAndAndOper() {
		try {

			Context from = composeSourceContext();
			from.setValueAt("testString", "true");
			from.setValueAt("testInteger", "1");

			Context to = ContextFactory.createContext("funcTargetBooleanDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_boolean_andandFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testStringBoolean"));
			Assert.assertTrue((Boolean) to.getValueAt("testIntegerBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}
	//js engine,now it has changed to mvel,so ignore this case
	@Ignore
	public void testFuncBooleanOrOrOper() {
		try {

			Context from = composeSourceContext();
			from.setValueAt("testString", "false");
			from.setValueAt("testInteger", 0);

			Context to = ContextFactory.createContext("funcTargetBooleanDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_boolean_ororFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testStringBoolean"));
			Assert.assertTrue((Boolean) to.getValueAt("testIntegerBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}

	}

	@Test
	public void testFuncNumberAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_addtionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new BigDecimal("10.000"), to.getValueAt("testNumber"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncNumberSubtractionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_subtractionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new BigDecimal("-118.000"), to.getValueAt("testNumber"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncNumberMultiplicationOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_multiplicationFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new BigDecimal("615.000"), to.getValueAt("testNumber"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncNumberDivisionOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testNumber", "25");
			from.setValueAt("testLong", "5");
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_divisionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new BigDecimal("5.000"), to.getValueAt("testNumber"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncNumberDoubleEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_doubleEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncNumberNonEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_number_noEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertFalse((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	//js engine,now it has changed to mvel,so ignore this case
	@Ignore
	public void testFuncByteAddtionOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 15);
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_byte_addtionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new Byte("20"), to.getValueAt("testByte"));
			Assert.assertEquals(new Float("10.0"), to.getValueAt("testFloat"));
			Assert.assertEquals(new Short("128"), to.getValueAt("testShort"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncByteGreaterThanOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_byte_greaterThanFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncShortAddtionOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 12);
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_short_addtionFmt");
			fmt.mapContents(from, to);
			Assert.assertEquals(new Short("135"), to.getValueAt("testShort"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncShortLessThanOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_short_lessThanFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncShortDoubleEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_short_doubleEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertFalse((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncIntegerAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_integer_addtionFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(1234565, to.getValueAt("testInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncIntegerGreaterThanEqualSignOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 5);
			from.setValueAt("testFloat", new Float("4.9"));
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_integer_greaterThanEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncIntegerNonEqualSignOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testInteger", 5);
			from.setValueAt("testDouble", new Double("5.0"));
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_integer_nonEqualSignFmt");
			fmt.mapContents(from, to);
			Assert.assertFalse((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncLongAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_long_addtionFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(new Long("123127"), to.getValueAt("testLong"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncLongLessThanEqualSignOper() {
		try {
			Context from = composeSourceContext();
			from.setValueAt("testLong", new Long("5"));
			from.setValueAt("testDouble", new Double("5.1"));
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_long_lessThanEqualSignFmt");
			fmt.mapContents(from, to);

			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncFloatAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_float_addtionFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(new Float("128.0"), to.getValueAt("testFloat"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncLongGreaterThanEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_float_greaterThanEqualSignFmt");
			fmt.mapContents(from, to);

			Assert.assertFalse((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncDoubleAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_double_addtionFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(new BigInteger("12345674"), to.getValueAt("testBigInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncDoubleLessThanOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_double_lessThanFmt");
			fmt.mapContents(from, to);

			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncBigIntegerAddtionOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_bigInteger_addtionFmt");
			fmt.mapContents(from, to);

			Assert.assertEquals(new BigInteger("12345670"), to.getValueAt("testBigInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncBigIntegerGreaterThanEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_bigInteger_greaterThanEqualSignFmt");
			fmt.mapContents(from, to);

			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testFuncBigDecimalLessThanEqualSignOper() {
		try {
			Context from = composeSourceContext();
			Context to = ContextFactory.createContext("funcTargetFieldDataCtx");
			DataMapperFormat fmt = (DataMapperFormat) FormatElement.readObject("func_oper_bigDecimal_lessThanEqualSignFmt");
			fmt.mapContents(from, to);

			Assert.assertTrue((Boolean) to.getValueAt("testBoolean"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}

	@Test
	public void testStringSpaceCenter() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceCenterFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("Hello World", target.getValueAt("testString"));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	@Test
	public void testStringSpaceLeft() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceLeftFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals(" HelloWorld", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testStringSpaceRight() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceRightFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("HelloWorld ", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testStringSpaceTab() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceTabFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("HelloWorld", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testStringSpaceTabString() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceTabStringFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("\tHelloWorld\t", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	@Test
	public void testStringSpaceEnter() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceEnterFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("HelloWorld", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testStringSpaceEnterString() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceEnterStringFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("\nHello\nWorld\n", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	
	
	
	@Test
	public void testStringSpaceNewLine() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceNewLineFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("HelloWorld", target.getValueAt("testString"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while testing, detailed exception messages are: " + e);
		}
	}
	@Test
	public void testStringSpaceNewLineString() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = (DataMapperExpressionConverterFormat) FormatElement.readObject("StringSpaceNewLineStringFmt");
			fmt.mapContents(source, target);
			Assert.assertEquals("\rHello\rWorld\r", target.getValueAt("testString"));
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
