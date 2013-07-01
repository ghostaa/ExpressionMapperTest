package com.ibm.btt.test.fvt.scenario7;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegComputeWildcardWithFuncMapping extends CommonTestCase {

	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * Mapping field result to collection data
	 */
	@Test
	public void testFromFieldResultToCollectionMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_MapFieldResultToCollectionFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			for (int i = 0; i < 16; i++)
				source.setValueAt("S7PL1I." + i + ".age", (byte) (26 + i));

			// mapping the source values to target
			fmt.mapContents(source, target);

			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'functs_BTTS7PersonFunctions.averageAge(S7PL1I.*.age)' with element type 'DataField' to 'groupedData' with element type 'KeyedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"functs_BTTS7PersonFunctions.averageAge(S7PL1I$*$age)\" to=\"groupedData\" />] from context [WildcardToFieldCtxt] to [WildcardToFieldCtxt]:From data element [functs_BTTS7PersonFunctions.averageAge(S7PL1I.*.age)](DataField) is not match with the To data element [groupedData](KeyedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
