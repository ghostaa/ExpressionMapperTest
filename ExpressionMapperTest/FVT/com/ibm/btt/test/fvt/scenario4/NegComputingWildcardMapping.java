package com.ibm.btt.test.fvt.scenario4;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegComputingWildcardMapping extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * WildCardNumberNotMatch
	 */
	@Test
	public void testWildCardNumberNotMatchMapping() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_WildCardNumberNotMatchFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'Packages.com.ibm.btt.utils.GlobalFunctions.getRowByIndex(L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$L5I, 1)'. Check the expression for errors or invalid parameters to global functions.";
			String lin11 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Invalid mapping configuration, wildcard number in source [functs_BTTCollectionFunctions.getRowByIndex(L1I$*$L2I$*$L3I$*$L4I$*$L5I, 1)] and target [L1IC.*.L2IC.*.L3IC.*] are not consistent.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * WildCardNumberNotMatch between source expression factors
	 */
	@Test
	public void testWildCardNumberNotMatchBetweenExprFactorMapping() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_WildCardNumberNotMatchInExprFactorFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'Packages.com.ibm.btt.utils.GlobalFunctions.replace(L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$L5I$_$$_$Str1Field, L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$Str2Field, L1I$_$$_$L2I$_$$_$L3I$_$$_$Str3Field)'. Check the expression for errors or invalid parameters to global functions.";
			String lin11 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Wildcard number in source expression is not the same.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * @see NegComputingWildcardMapping<br>
	 *      method <code>testWildCardNumberNotMatchBetweenExprFactorMapping</code>
	 */
	@Test
	public void testFactorsFromDifferentICollInExprMapping() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_WildCardNumberNotMatchInExprFactorFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'Packages.com.ibm.btt.utils.GlobalFunctions.replace(L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$L5I$_$$_$Str1Field, L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$Str2Field, L1I$_$$_$L2I$_$$_$L3I$_$$_$Str3Field)'. Check the expression for errors or invalid parameters to global functions.";
			String lin11 = "#FUNC [ERROR]BTT-E014: invalid wildcard mapping: Wildcard number in source expression is not the same.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Leaf data element type does not match while the expression is asterisk to
	 * asterisk.
	 */
	@Test
	public void testLeafDataEleMissMatchMapping() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_LeafDataEleMissMatchFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'Packages.com.ibm.btt.utils.GlobalFunctions.getRowByIndex(L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$L5I, 1)'. Check the expression for errors or invalid parameters to global functions.";
			String lin11 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'S4FuncTypedDataRec' with element type 'KeyedCollection' to 'FIELDDATA' with element type 'DataField'.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * function returned data miss match with target data element's type
	 */
	@Test
	public void testFuncRetDataMisMatchTargetDataEleMapping() {
		try {
			Context source = getContextByName("FuncsUsedCtxt");
			Context target = getContextByName("FuncsUsedCtxt");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_FuncRetDataMisMatchTargetDataEleFmt");

			// initialize the source context
			initializeSourceContext(source);

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E011: failed to execute mapping expression 'Packages.com.ibm.btt.utils.GlobalFunctions.parseDate(L1I$_$$_$L2I$_$$_$L3I$_$$_$L4I$_$$_$L5I$_$$_$DateStrField, 'yyyyMMdd')'. Check the expression for errors or invalid parameters to global functions.";
			String lin11 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'functs_BTTDateFunctions.parseDate(L1I.0.L2I.0.L3I.0.L4I.0.L5I.0.DateStrField, 'yyyyMMdd')' with element type 'DataField' to 'S4FuncTypedDataRec' with element type 'KeyedCollection'.";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	private void initializeSourceContext(Context source) throws Exception {
		int CONSTANTII = 5;
		// initialize the source context
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 4; k++)
					for (int ii = 0; ii < CONSTANTII; ii++)
						for (int jj = 0; jj < 3; jj++) {
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str1Field", "ILIKE"
									+ i + j + k + ii + jj + "BTT" + i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str2Field", "LIKE" + i
									+ j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".Str3Field", "LOVE");
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".DateStrField", "201"
									+ i + "0" + (j + 1) + "0" + (k + 1));
							long l = 1048576 * 8 + (i + j + k + ii + jj);
							source.setValueAt("L1I." + i + ".L2I." + j + ".L3I." + k + ".L4I." + ii + ".L5I." + jj + ".MinusLongField", -1
									* l);
						}
	}
}
