package com.ibm.btt.test.fvt.scenario6;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;
import com.ibm.btt.test.fvt.common.TestingVerificationLogImpl;

public class NegExpressionToWildcardMapping extends CommonTestCase {
	protected boolean isLogVerificationEnabled() {
		return true;
	}

	protected void setDefaultTraceLevel() {
		setTraceLevel(TestingVerificationLogImpl.ERROR);
	}

	/**
	 * source element type miss match target element type
	 */
	@Test
	public void testSourceTypeMissMatchTargetTypeMapping() {
		try {
			Context source = getContextByName("FuncExprToWildcardCtxt");
			Context target = getContextByName("FuncExprToWildcardCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("USPT_SourceTypeMissMatchTargetTypeFmt");
			// initialize target, since it is needed to iterate the target and
			// meanwhile get values from source context
			IndexedCollection TDRIC = (IndexedCollection) source.getElementAt("parentKC.TDRIC");
			IndexedCollection TDRICT = (IndexedCollection) target.getElementAt("parentKC.TDRIC");
			TDRIC.removeAll();
			int N = 3;
			for (int i = 0; i < N; i++) {
				KeyedCollection kcoll = (KeyedCollection) TDRIC.createElement(true);
				kcoll.setValueAt("index", i);
				kcoll.setValueAt("TypeDataDefRec.str1", "IO" + i);
				kcoll.setValueAt("TypeDataDefRec.str2", "OMG" + i);
				kcoll.setValueAt("TypeDataDefRec.dateSlash", null);
				kcoll.setValueAt("TypeDataDefRec.mixedRec.dateMinus", parseStringToDate("2012-12-31"));
				BigDecimal bdVal = new BigDecimal("8192" + i + ".10485769125").setScale(9, BigDecimal.ROUND_HALF_UP);
				kcoll.setValueAt("TypeDataDefRec.mixedRec.numberData.numberDP9", bdVal);
				kcoll.setValueAt("TypeDataDefRec.mixedRec.numberData.deepNumberData.numberDP3", bdVal.setScale(3, 0));
				kcoll.setValueAt("TypeDataDefRec.mixedRec.numberData.deepNumberData.testBigInteger",
						new BigInteger(String.valueOf(1000 * i)));
				kcoll.setValueAt("TypeDataDefRec.mixedRec.numberData.deepNumberData.testString", "NIO" + i);
				TDRIC.addElement(kcoll);
				TDRICT.addElement(TDRICT.createElement(true));
			}

			fmt.mapContents(source, target);

			// check the logs, THERE WILL NOT BE EXCEPTIONS THROWN OUT ON CUSTOMER'S
			// DEMAND
			String[] logContents = getLogContentsInLines();
			String line0 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'functs_BTTCollectionFunctions.tableSize(parentKC.TDRIC)' with element type 'DataField' to 'TypeDataDefRec' with element type 'KeyedCollection'.";
			String lin11 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"functs_BTTCollectionFunctions.tableSize(parentKC$TDRIC)\" to=\"parentKC.TDRIC.*.TypeDataDefRec\" byReference=\"true\" />] from context [FuncExprToWildcardCtxt] to [FuncExprToWildcardCtxt]:From data element [functs_BTTCollectionFunctions.tableSize(parentKC.TDRIC)](DataField) is not match with the To data element [TypeDataDefRec](KeyedCollection).";
			String line2 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from ''ILOVEBTT'' with element type 'DataField' to 'TypeDataDefRec' with element type 'KeyedCollection'.";
			String line3 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"'ILOVEBTT'\" to=\"parentKC.TDRIC.*.TypeDataDefRec\" byReference=\"true\" />] from context [FuncExprToWildcardCtxt] to [FuncExprToWildcardCtxt]:From data element ['ILOVEBTT'](DataField) is not match with the To data element [TypeDataDefRec](KeyedCollection).";
			String line4 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'Math.PI * 8192.0' with element type 'DataField' to 'TypeDataDefRec' with element type 'KeyedCollection'.";
			String line5 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"Math.PI * 8192.0\" to=\"parentKC.TDRIC.*.TypeDataDefRec\" byReference=\"true\" />] from context [FuncExprToWildcardCtxt] to [FuncExprToWildcardCtxt]:From data element [Math.PI * 8192.0](DataField) is not match with the To data element [TypeDataDefRec](KeyedCollection).";
			String line6 = "#FUNC [ERROR]BTT-E013: incompatible data element mapping: cannot map from 'parentKC.K.INK.TK.TypeDataDefRec.str1 + parentKC.K.INK.TK.TypeDataDefRec.str2' with element type 'DataField' to 'TypeDataDefRec' with element type 'KeyedCollection'.";
			String line7 = "com.ibm.btt.base.DataMapperExpressionConverterFormat [ERROR]Error when process mapping: [<map fromExpression=\"parentKC$K$INK$TK$TypeDataDefRec$str1 + parentKC$K$INK$TK$TypeDataDefRec$str2\" to=\"parentKC.TDRIC.*.TypeDataDefRec\" byReference=\"true\" />] from context [FuncExprToWildcardCtxt] to [FuncExprToWildcardCtxt]:From data element [parentKC.K.INK.TK.TypeDataDefRec.str1 + parentKC.K.INK.TK.TypeDataDefRec.str2](DataField) is not match with the To data element [TypeDataDefRec](KeyedCollection).";
			Assert.assertEquals(line0, logContents[0]);
			Assert.assertEquals(lin11, logContents[1]);
			Assert.assertEquals(line2, logContents[2]);
			Assert.assertEquals(line3, logContents[3]);
			Assert.assertEquals(line4, logContents[4]);
			Assert.assertEquals(line5, logContents[5]);
			Assert.assertEquals(line6, logContents[6]);
			Assert.assertEquals(line7, logContents[7]);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
