package com.ibm.btt.test.fvt.scenario2;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegDirectComputingMapping extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * Data element type miss match mapping
	 */
	@Test
	public void testDataEleMissMatchMapping() {
		try {
			Context source = getContextByName("testGlobalKColl");
			Context target = getContextByName("testGlobalKColl");
			source.getKeyedCollection().setDynamic(true);
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_EleTypeMissMatchFmt");

			// mapping the source to target
			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'functs_BTTNumberFunctions.multiply(1048576.8192,1024)' with element type 'DataField' to 'funcTestGlobalIntegerIColl' with element type 'IndexedCollection'.";
			String lin11 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map fromExpression=\"functs_BTTNumberFunctions.multiply(1048576.8192,1024)\" to=\"funcTestGlobalIntegerIColl\" />] from context [testGlobalKColl] to [testGlobalKColl]:From data element [functs_BTTNumberFunctions.multiply(1048576.8192,1024)](DataField) is not match with the To data element [funcTestGlobalIntegerIColl](IndexedCollection).";
			String line2 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'funcTestGlobalIntegerKColl' with element type 'KeyedCollection' to 'funcTestGlobalIntegerIColl' with element type 'IndexedCollection'.";
			String lin13 = "com.ibm.btt.base.ConditionalDataMapperFormat [ERROR]Error when process mapping: [<map fromExpression=\"functs_BTTCollectionFunctions.getRowByIndex(funcTestGlobalIntegerIColl,0)\" to=\"funcTestGlobalIntegerIColl\" />] from context [testGlobalKColl] to [testGlobalKColl]:From data element [funcTestGlobalIntegerKColl](KeyedCollection) is not match with the To data element [funcTestGlobalIntegerIColl](IndexedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
			Assert.assertEquals(line2, logContents[2]);
			Assert.assertEquals(lin13, logContents[3]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
