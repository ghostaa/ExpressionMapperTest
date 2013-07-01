package com.ibm.btt.test.fvt.scenario2Appendix;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ExpressionWithOctHexNumberTest extends CommonTestCase {

	@Test
	public void testMapperWithOctNumber() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OctalNumberFmt");

			// map the source to target
			fmt.mapContents(source, target);

			Assert.assertEquals(0077, target.getValueAt("testInteger"));
			Assert.assertEquals(0077L, target.getValueAt("testLong"));
			Assert.assertEquals(new BigInteger("63"), target.getValueAt("testBigInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	@Test
	public void testMapperWithHexNumber() {
		try {
			Context source = getContextByName("funcFieldDataCtx");
			Context target = getContextByName("funcFieldDataCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("HexNumberFmt");

			// map the source to target
			fmt.mapContents(source, target);

			Assert.assertEquals(0x7E, target.getValueAt("testInteger"));
			Assert.assertEquals(0x7EL, target.getValueAt("testLong"));
			Assert.assertEquals(new BigInteger("126"), target.getValueAt("testBigInteger"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case ["
					+ getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}	
}
