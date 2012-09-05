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

public class ExpressionToWildcardMapping extends CommonTestCase {

	/**
	 * global function within expression to wildcard mapping
	 */
	@Test
	public void testFunctionExprToWildcardMapping1() {
		try {
			Context source = getContextByName("FuncExprToWildcardCtxt");
			Context target = getContextByName("FuncExprToWildcardCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FuncExprToWildcardMapFmt1");
			// initialize target, since it is needed to iterate the target and
			// meanwhile get values from source context
			IndexedCollection TDRI = (IndexedCollection) target.getElementAt("parentKC.TDRI");
			IndexedCollection TDRIC = (IndexedCollection) target.getElementAt("parentKC.TDRIC");
			int N = 3;
			for (int i = 0; i < N; i++) {
				TDRI.addElement(TDRI.createElement(true));
				TDRIC.addElement(TDRIC.createElement(false));
			}

			// initialize the source context
			source.setValueAt("parentKC.K.INK.TK.TypeDataDefRec.str1", "ILOVE");
			source.setValueAt("parentKC.K.INK.TK.TypeDataDefRec.str2", "BTT");
			source.setValueAt("parentKC.K.INK.TK.TypeDataDefRec.mixedRec.dateMinus", parseStringToDate("2012-12-12"));
			BigDecimal bdVal = new BigDecimal("8192.10485769125").setScale(9, BigDecimal.ROUND_HALF_UP);
			source.setValueAt("parentKC.K.INK.TK.TypeDataDefRec.mixedRec.numberData.numberDP9", bdVal);
			source.setValueAt("parentKC.K.INK.TK.TypeDataDefRec.mixedRec.numberData.deepNumberData.testString", "SUC");

			fmt.mapContents(source, target);

			for (int i = 0; i < N; i++) {
				// mapping item 1
				Assert.assertEquals("ILOVE", target.getValueAt("parentKC.TDRI." + i + ".str1"));
				Assert.assertEquals("BTT", target.getValueAt("parentKC.TDRI." + i + ".str2"));
				Assert.assertNull("No default value", target.getValueAt("parentKC.TDRI." + i + ".dateSlash"));
				Assert.assertEquals(parseStringToDate("2012-12-12"), target.getValueAt("parentKC.TDRI." + i + ".mixedRec.dateMinus"));
				Assert.assertEquals(bdVal, target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.numberDP9"));
				Assert.assertNull("No default value",
						target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.numberDP3"));
				Assert.assertNull("No default value",
						target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.testBigInteger"));
				Assert.assertEquals("SUC", target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.testString"));
				// mapping item 2
				Assert.assertEquals("ILOVE", target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.str1"));
				Assert.assertEquals("BTT", target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.str2"));
				Assert.assertNull("No default value", target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.dateSlash"));
				Assert.assertEquals(parseStringToDate("2012-12-12"),
						target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.mixedRec.dateMinus"));
				Assert.assertEquals(bdVal, target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.mixedRec.numberData.numberDP9"));
				Assert.assertNull("No default value",
						target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.mixedRec.numberData.deepNumberData.numberDP3"));
				Assert.assertNull("No default value",
						target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.mixedRec.numberData.deepNumberData.testBigInteger"));
				Assert.assertEquals("SUC",
						target.getValueAt("parentKC.TDRIC." + i + ".TypeDataDefRec.mixedRec.numberData.deepNumberData.testString"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * global function within expression to wildcard mapping
	 */
	@Test
	public void testFunctionExprToWildcardMapping2() {
		try {
			Context source = getContextByName("FuncExprToWildcardCtxt");
			Context target = getContextByName("FuncExprToWildcardCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FuncExprToWildcardMapFmt2");
			// initialize target, since it is needed to iterate the target and
			// meanwhile get values from source context
			IndexedCollection TDRI = (IndexedCollection) target.getElementAt("parentKC.TDRI");
			IndexedCollection TDRIC = (IndexedCollection) source.getElementAt("parentKC.TDRIC");
			TDRI.removeAll();
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
				TDRI.addElement(TDRI.createElement(true));
			}

			fmt.mapContents(source, target);

			for (int i = 0; i < N; i++) {
				Assert.assertEquals("IO" + i, target.getValueAt("parentKC.TDRI." + i + ".str1"));
				Assert.assertEquals("OMG" + i, target.getValueAt("parentKC.TDRI." + i + ".str2"));
				Assert.assertNull("Default value", target.getValueAt("parentKC.TDRI." + i + ".dateSlash"));
				Assert.assertEquals(parseStringToDate("2012-12-31"), target.getValueAt("parentKC.TDRI." + i + ".mixedRec.dateMinus"));
				BigDecimal bdVal = new BigDecimal("8192" + i + ".10485769125").setScale(9, BigDecimal.ROUND_HALF_UP);
				Assert.assertEquals(bdVal, target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.numberDP9"));
				Assert.assertEquals(bdVal.setScale(3, 0),
						target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.numberDP3"));
				Assert.assertEquals(new BigInteger(String.valueOf(1000 * i)),
						target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.testBigInteger"));
				Assert.assertEquals("NIO" + i,
						target.getValueAt("parentKC.TDRI." + i + ".mixedRec.numberData.deepNumberData.testString"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

}
