package com.ibm.btt.test.fvt.scenario5;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegComputingWildcardWithOpersMapping extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * source and target types are not match
	 */
	@Test
	public void testOperatorsResultTypeAndTargetTypeNotMatchFmt() {
		try {
			Context source = getContextByName("OperatorsUsedCtxt");
			Context target = getContextByName("OperatorsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_OperatorsResultTypeAndTargetTypeNotMatchFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E010: incompatible type mapping: cannot map from 'L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str1Field+L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str2Field+L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str3Field' of type 'untyped(DataField[java.lang.String])' to 'BigIntegerField' of type 'BigInteger'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$Str1Field + L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$Str2Field + L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$Str3Field\" to=\"BigIntegerField\" />] from context [OperatorsUsedCtxt] to [OperatorsUsedCtxt]:Cannot convert data L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str1Field+L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str2Field+L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.Str3Field when using converType = default.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * wildcards between source and target are not match
	 */
	@Test
	public void testWildCardNumberNotMatchMapping() {
		try {
			Context source = getContextByName("OperatorsUsedCtxt");
			Context target = getContextByName("OperatorsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_OperatorsWildCardNotMatchFmt1");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Invalid mapping configuration, wildcard number in source [L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$ByteField+(L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$IntegerField-L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$LongField)*L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$DoubleField/BigIntegerField] and target [MI.*.IMI.*.PlainField] are not consistent.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$ByteField + (L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$IntegerField - L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$LongField) * L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$DoubleField / BigIntegerField\" to=\"MI.*.IMI.*.PlainField\" />] from context [OperatorsUsedCtxt] to [OperatorsUsedCtxt]:Invalid mapping configuration, wildcard number in source [L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$ByteField+(L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$IntegerField-L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$LongField)*L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$DoubleField/BigIntegerField] and target [MI.*.IMI.*.PlainField] are not consistent.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * wildcards between expression factors are not match
	 */
	@Test
	public void testWildCardNumberNotMatchBetweenExprFactorMapping() {
		try {
			Context source = getContextByName("OperatorsUsedCtxt");
			Context target = getContextByName("OperatorsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_OperatorsWildCardNotMatchFmt2");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Wildcard expression is invalid, the paths [L1I.*.L2I.*.L3I.*.L4I.*.L5I.*] and [MI.*.IMI.*] are not for the same IndexedCollection, expression: L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.ByteField+(L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.IntegerField-MI.*.IMI.*.PlainField)*L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.DoubleField/BigIntegerField.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$ByteField + (L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$IntegerField - MI$*$IMI$*$PlainField) * L1I$*$L2I$*$L3I$*$L4I$*$L5I$*$DoubleField / BigIntegerField\" to=\"MI.*.IMI.*.PlainField\" />] from context [OperatorsUsedCtxt] to [OperatorsUsedCtxt]:Wildcard expression is invalid, the paths [L1I.*.L2I.*.L3I.*.L4I.*.L5I.*] and [MI.*.IMI.*] are not for the same IndexedCollection, expression: L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.ByteField+(L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.IntegerField-MI.*.IMI.*.PlainField)*L1I.*.L2I.*.L3I.*.L4I.*.L5I.*.DoubleField/BigIntegerField.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * factors are not from same icoll
	 */
	@Test
	public void testFactorsFromDifferentICollInExprMapping() {
		try {
			Context source = getContextByName("OperatorsUsedCtxt");
			Context target = getContextByName("OperatorsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_OperatorsFactorsNotFromSameICollFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Wildcard expression is invalid, the paths [MI.*.IMI.*] and [MI.*.IMI2.*] are not for the same IndexedCollection, expression: MI.*.IMI.*.PlainField+MI.*.IMI2.*.PlainField.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"MI$*$IMI$*$PlainField + MI$*$IMI2$*$PlainField\" to=\"MI.*.IMI.*.PlainField\" />] from context [OperatorsUsedCtxt] to [OperatorsUsedCtxt]:Wildcard expression is invalid, the paths [MI.*.IMI.*] and [MI.*.IMI2.*] are not for the same IndexedCollection, expression: MI.*.IMI.*.PlainField+MI.*.IMI2.*.PlainField.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	private void initializeSourceContext(Context source) throws Exception {
		// initialize the source context
		int CONSTANTII = 5;
		// initialize the source context
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 4; k++)
					for (int ii = 0; ii < CONSTANTII; ii++)
						for (int jj = 0; jj < 3; jj++) {
							// string
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str1Field", "I DO "
									+ i + j + k + ii + jj + "BTT" + i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str2Field", "LOVE "
									+ i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str3Field", "BTT");

							// numbers
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".ByteField", (byte) ((i
									+ j + k)
									* (ii + jj) % Byte.MAX_VALUE));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".IntegerField",
									(i + j - k) * (ii + jj));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".LongField",
									(long) 8192 * (i + j + k + ii + jj));
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".DoubleField",
									1.024D * (i + j + k + ii + jj) * 1024);

							// boolean
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".BooleanField", (i + j
									+ k + ii + jj) % 2 == 0);
						}

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++) {
				source.setValueAt("MI." + i + ".IMI." + j, "I1" + i + j);
				source.setValueAt("MI." + i + ".IMI2." + j, "I2" + i + j);
			}
	}
}
