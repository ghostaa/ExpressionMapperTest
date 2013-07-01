package com.ibm.btt.test.fvt.scenario9;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegAsteriskToAsteriskMapping extends CommonTestCase {

	@Override
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	@Override
	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * From KCOLL.* to * mapping, while source dataNames equal to target dataNames
	 */
	@Test
	public void testKCollToAsteriskFmtWithDataNamesEqualMapping2() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_KCollAsteriskToAsteriskMapingFmt");

			source.setValueAt("ComputerKColl.brand", "IBM-TPD-X220");
			source.setValueAt("ComputerKColl.cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("ComputerKColl.memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("ComputerKColl.comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("ComputerKColl.comDiscount", 0.88D);
			source.setValueAt("ComputerKColl.comQuantity", 81921048576L);
			source.setValueAt("ComputerKColl.comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			String line0 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Can not map a wildcard source to non-wildcard target.";
			String line1 = "<map from=\"ComputerKColl.*\" to=\"*\" />";
			String line2 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map from=\"ComputerKColl.*\" to=\"*\" />] from context [ComputerCtxt] to [ComputerCtxt]:Can not map a wildcard source to non-wildcard target.";
			String line3 = "<map from=\"ComputerKColl.*\" to=\"*\" />";

			String[] lines = getLogContentsInLines();

			Assert.assertEquals(line0, lines[0]);
			Assert.assertEquals(line1, lines[1]);
			Assert.assertEquals(line2, lines[2]);
			Assert.assertEquals(line3, lines[3]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Data element not found either in source or target
	 */
	@Test
	public void testDataElementNotFoundMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("NEG_DataElementNotExistFmt");

			source.setValueAt("ComputerKColl.brand", "IBM-TPD-X220");
			source.setValueAt("ComputerKColl.cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("ComputerKColl.memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("ComputerKColl.comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("ComputerKColl.comDiscount", 0.88D);
			source.setValueAt("ComputerKColl.comQuantity", 81921048576L);
			source.setValueAt("ComputerKColl.comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			String line0 = "#FUNC [ERROR]BTT-E006: data element 'ComputerKColl.brand1' not found in context 'ComputerCtxt' while performing data mapping.";
			String line1 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map from=\"brand\" to=\"ComputerKColl.brand1\" />] from context [ComputerCtxt] to [ComputerCtxt]:An element \"ComputerKColl.brand1\" has not been found in target context \"ComputerCtxt\".";
			String line2 = "#FUNC [ERROR]BTT-E006: data element 'ComputerKColl1brand' not found in context 'ComputerCtxt' while performing data mapping.";
			String line3 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map from=\"ComputerKColl1brand\" to=\"brand\" />] from context [ComputerCtxt] to [ComputerCtxt]:An element \"ComputerKColl1brand\" has not been found in source context \"ComputerCtxt\".";

			String[] lines = getLogContentsInLines();

			Assert.assertEquals(line0, lines[0]);
			Assert.assertEquals(line1, lines[1]);
			Assert.assertEquals(line2, lines[2]);
			Assert.assertEquals(line3, lines[3]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Referenced format is invalid
	 */
	@Test
	public void testReferencedFmtInvalidMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_ReferencedFmtIsInvalidFmt");

			source.setValueAt("ComputerKColl.brand", "IBM-TPD-X220");
			source.setValueAt("ComputerKColl.cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("ComputerKColl.memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("ComputerKColl.comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("ComputerKColl.comDiscount", 0.88D);
			source.setValueAt("ComputerKColl.comQuantity", 81921048576L);
			source.setValueAt("ComputerKColl.comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			String line0 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Non-DataMapperFmt can not be nested in DataMapperFmt:";
			String line1 = "<fmtDef id=\"StringFmt\" >";

			String[] lines = getLogContentsInLines();

			Assert.assertEquals(line0, lines[0].trim());
			Assert.assertEquals(line1, lines[1].trim());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Mapper format is invalid
	 */
	@Test
	public void testMapperFmtInvalidMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_MapperFmtIsInvalidFmt");

			source.setValueAt("ComputerKColl.brand", "IBM-TPD-X220");
			source.setValueAt("ComputerKColl.cpuFreq", Integer.MAX_VALUE);
			source.setValueAt("ComputerKColl.memSize", new BigInteger(String.valueOf(1048576)));
			source.setValueAt("ComputerKColl.comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("ComputerKColl.comDiscount", 0.88D);
			source.setValueAt("ComputerKColl.comQuantity", 81921048576L);
			source.setValueAt("ComputerKColl.comComments", "WHATAWORLD");

			fmt.mapContents(source, target);

			String line0 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map from1=\"brand\" to2=\"brand\" byReference3=\"true\" />] from context [ComputerCtxt] to [ComputerCtxt]:Mapper is not valid, should at least contain \"to\" and \"fromExpression\" or \"from\" attributes.";
			String line1 = "<map from1=\"brand\" to2=\"brand\" byReference3=\"true\" />";

			String[] lines = getLogContentsInLines();

			Assert.assertEquals(line0, lines[0].trim());
			Assert.assertEquals(line1, lines[1].trim());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Value is NOT cloneable while byReference is false.
	 */
	@Test
	public void testValueNotClonableMapping() {
		try {
			Context source = getContextByName("ComputerCtxt");
			Context target = getContextByName("ComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_ValueNotSupportCloneFmt");

			source.setValueAt("brand", "IBM-TPD-X220");
			source.setValueAt("comPrice", new BigDecimal("1048576.819240962"));
			source.setValueAt("comComments", new ABC());

			fmt.mapContents(source, target);

			String line0 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map from=\"comComments\" to=\"comComments\" byReference=\"false\" />] from context [ComputerCtxt] to [ComputerCtxt]:Cannot clone untyped data comComments when using byReference=false.";

			String[] lines = getLogContentsInLines();

			Assert.assertEquals(line0, lines[0].trim());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	class ABC {
		String a = "a";

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

	}
}
