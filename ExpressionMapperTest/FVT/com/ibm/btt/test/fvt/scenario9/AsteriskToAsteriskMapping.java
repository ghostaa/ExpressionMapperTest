package com.ibm.btt.test.fvt.scenario9;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class AsteriskToAsteriskMapping extends CommonTestCase {

	/**
	 * From * to * mapping, while source dataNames equal to target dataNames
	 */
	@Test
	public void testAsteriskFmtWithDataNamesEqualMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AsteriskToAsteriskMapingFmt");

			source.setValueAt("brand", "IBM-TPD-X220");
			source.setValueAt("cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("comDiscount", 0.88D);
			source.setValueAt("comQuantity", 81921048576L);
			source.setValueAt("comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			Assert.assertEquals("IBM-TPD-X220", target.getValueAt("brand"));
			Assert.assertEquals(Integer.MAX_VALUE, target.getValueAt("cpuFreq"));
			Assert.assertEquals(new BigInteger(String.valueOf(1048576)), target.getValueAt("memSize"));
			Assert.assertEquals(new BigDecimal("1048576.819240962"), target.getValueAt("comPrice"));
			Assert.assertEquals(0.88D, target.getValueAt("comDiscount"));
			Assert.assertEquals(81921048576L, target.getValueAt("comQuantity"));
			Assert.assertEquals("WHATAWORLD", target.getValueAt("comComments"));

			Assert.assertNull(target.getValueAt("ComputerKColl.brand"));
			Assert.assertNull(target.getValueAt("ComputerKColl.cpuFreq"));
			Assert.assertNull(target.getValueAt("ComputerKColl.memSize"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comPrice"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comDiscount"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comQuantity"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comComments"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * From * to KCOLL mapping, while source dataNames equal to target dataNames
	 */
	@Test
	public void testAsteriskToKCollFmtWithDataNamesEqualMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AsteriskToKCollMapingFmt");

			source.setValueAt("brand", "IBM-TPD-X220");
			source.setValueAt("cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("comDiscount", 0.88D);
			source.setValueAt("comQuantity", 81921048576L);
			source.setValueAt("comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			Assert.assertEquals("IBM-TPD-X220", target.getValueAt("ComputerKColl.brand"));
			Assert.assertEquals(Integer.MAX_VALUE, target.getValueAt("ComputerKColl.cpuFreq"));
			Assert.assertEquals(new BigInteger(String.valueOf(1048576)), target.getValueAt("ComputerKColl.memSize"));
			Assert.assertEquals(new BigDecimal("1048576.819240962"), target.getValueAt("ComputerKColl.comPrice"));
			Assert.assertEquals(0.88D, target.getValueAt("ComputerKColl.comDiscount"));
			Assert.assertEquals(81921048576L, target.getValueAt("ComputerKColl.comQuantity"));
			Assert.assertEquals("WHATAWORLD", target.getValueAt("ComputerKColl.comComments"));

			Assert.assertNull(target.getValueAt("brand"));
			Assert.assertNull(target.getValueAt("cpuFreq"));
			Assert.assertNull(target.getValueAt("memSize"));
			Assert.assertNull(target.getValueAt("comPrice"));
			Assert.assertNull(target.getValueAt("comDiscount"));
			Assert.assertNull(target.getValueAt("comQuantity"));
			Assert.assertNull(target.getValueAt("comComments"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * From KCOLL to * mapping, while source dataNames equal to target dataNames
	 */
	@Test
	public void testKCollToAsteriskFmtWithDataNamesEqualMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("KCollToAsteriskMapingFmt");

			source.setValueAt("ComputerKColl.brand", "IBM-TPD-X220");
			source.setValueAt("ComputerKColl.cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("ComputerKColl.memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("ComputerKColl.comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("ComputerKColl.comDiscount", 0.88D);
			source.setValueAt("ComputerKColl.comQuantity", 81921048576L);
			source.setValueAt("ComputerKColl.comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			Assert.assertNull(target.getValueAt("ComputerKColl.brand"));
			Assert.assertNull(target.getValueAt("ComputerKColl.cpuFreq"));
			Assert.assertNull(target.getValueAt("ComputerKColl.memSize"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comPrice"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comDiscount"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comQuantity"));
			Assert.assertNull(target.getValueAt("ComputerKColl.comComments"));

			Assert.assertEquals("IBM-TPD-X220", target.getValueAt("brand"));
			Assert.assertEquals(Integer.MAX_VALUE, target.getValueAt("cpuFreq"));
			Assert.assertEquals(new BigInteger(String.valueOf(1048576)), target.getValueAt("memSize"));
			Assert.assertEquals(new BigDecimal("1048576.819240962"), target.getValueAt("comPrice"));
			Assert.assertEquals(0.88D, target.getValueAt("comDiscount"));
			Assert.assertEquals(81921048576L, target.getValueAt("comQuantity"));
			Assert.assertEquals("WHATAWORLD", target.getValueAt("comComments"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

}
