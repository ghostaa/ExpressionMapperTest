package com.ibm.btt.test.fvt.scenario8;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class AppendValueTest extends CommonTestCase {

	/**
	 * Append = I(ignore)<br>
	 * AND <br>
	 * Source elements more than target elements in size
	 */
	@Test
	public void testAppendIgnoreWithSourceElesMoreThanTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendIgnoreWithSourceElesNEQToTargetElesFmt");

			int M = 32, N = 16;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			Assert.assertEquals(M, ((IndexedCollection) target.getElementAt("destComputerList1")).size());
			for (int i = 0; i < M; i++) {
				Assert.assertEquals("IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i),
						target.getValueAt("destComputerList1." + i + ".destBrand"));
				Assert.assertEquals(Integer.MAX_VALUE - i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
				Assert.assertEquals(new BigInteger(String.valueOf(1048576 * (512 * (1 + (i % 5))))),
						target.getValueAt("destComputerList1." + i + ".destMemSize"));
				Assert.assertEquals(new BigDecimal(1024 * (10 + i) / (i + 1)).setScale(9),
						target.getValueAt("destComputerList1." + i + ".destPrice"));
				Assert.assertEquals(0.5D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
				Assert.assertEquals((long) 1 + (i % 8), target.getValueAt("destComputerList1." + i + ".destQuantity"));
				Assert.assertEquals("" + i, target.getValueAt("destComputerList1." + i + ".destComments"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Append = I(ignore)<br>
	 * AND <br>
	 * Source elements LESS than target elements in size
	 */
	@Test
	public void testAppendIgnoreWithSourceElesLessThanTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendIgnoreWithSourceElesNEQToTargetElesFmt");

			int M = 16, N = 32;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			Assert.assertEquals(N, ((IndexedCollection) target.getElementAt("destComputerList1")).size());
			for (int i = 0; i < N; i++) {
				if (i < M) {
					Assert.assertEquals("IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destBrand"));
					Assert.assertEquals(Integer.MAX_VALUE - i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 * (512 * (1 + (i % 5))))),
							target.getValueAt("destComputerList1." + i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 * (10 + i) / (i + 1)).setScale(9),
							target.getValueAt("destComputerList1." + i + ".destPrice"));
					Assert.assertEquals(0.5D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
					Assert.assertEquals((long) 1 + (i % 8), target.getValueAt("destComputerList1." + i + ".destQuantity"));
					Assert.assertEquals("" + i, target.getValueAt("destComputerList1." + i + ".destComments"));
				} else {
					Assert.assertEquals("BTT-TPD-X" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destBrand"));
					Assert.assertEquals(i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 + i)),
							target.getValueAt("destComputerList1." + i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 + i), target.getValueAt("destComputerList1." + i + ".destPrice"));
					Assert.assertEquals(0.7D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
					Assert.assertEquals((long) 1 + i, target.getValueAt("destComputerList1." + i + ".destQuantity"));
					Assert.assertEquals("000" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destComments"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Append = F(false)<br>
	 * AND <br>
	 * Source elements NOT EQUALS TO target elements in size
	 */
	@Test
	public void testAppendFalseWithSourceElesNotEqualToTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendFalseWithSourceElesNEQToTargetElesFmt");

			int M = 8, N = 16;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			Assert.assertEquals(M, ((IndexedCollection) target.getElementAt("destComputerList1")).size());
			for (int i = 0; i < M; i++) {
				Assert.assertEquals("IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i),
						target.getValueAt("destComputerList1." + i + ".destBrand"));
				Assert.assertEquals(Integer.MAX_VALUE - i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
				Assert.assertEquals(new BigInteger(String.valueOf(1048576 * (512 * (1 + (i % 5))))),
						target.getValueAt("destComputerList1." + i + ".destMemSize"));
				Assert.assertEquals(new BigDecimal(1024 * (10 + i) / (i + 1)).setScale(9),
						target.getValueAt("destComputerList1." + i + ".destPrice"));
				Assert.assertEquals(0.5D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
				Assert.assertEquals((long) 1 + (i % 8), target.getValueAt("destComputerList1." + i + ".destQuantity"));
				Assert.assertEquals("" + i, target.getValueAt("destComputerList1." + i + ".destComments"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Append = T(TRUE)<br>
	 * AND <br>
	 * Source elements NOT EQUALS TO target elements in size
	 */
	@Test
	public void testAppendTrueWithSourceElesNotEqualToTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendTrueWithSourceElesNEQToTargetElesFmt");

			int M = 4, N = 8;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			Assert.assertEquals(M + N, ((IndexedCollection) target.getElementAt("destComputerList1")).size());

			// checking the append IGNORE mapping items
			for (int i = 0; i < N; i++) {
				if (i >= M) {
					Assert.assertEquals("BTT-TPD-X" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destBrand"));
					Assert.assertEquals(i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 + i)),
							target.getValueAt("destComputerList1." + i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 + i), target.getValueAt("destComputerList1." + i + ".destPrice"));
					Assert.assertEquals(0.7D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
					Assert.assertEquals((long) 1 + i, target.getValueAt("destComputerList1." + i + ".destQuantity"));
					Assert.assertEquals("000" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destComments"));
				} else {
					Assert.assertEquals("BTT-TPD-X" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destBrand"));
					Assert.assertEquals(Integer.MAX_VALUE - i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 * (512 * (1 + (i % 5))))),
							target.getValueAt("destComputerList1." + i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 * (10 + i) / (i + 1)).setScale(9),
							target.getValueAt("destComputerList1." + i + ".destPrice"));
					Assert.assertEquals(0.5D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
					Assert.assertEquals((long) 1 + (i % 8), target.getValueAt("destComputerList1." + i + ".destQuantity"));
					Assert.assertEquals("" + i, target.getValueAt("destComputerList1." + i + ".destComments"));
				}
			}

			// checking the append TRUE mapping items
			for (int i = N; i < M + N; i++) {
				Assert.assertEquals("IBM-TPD-X9" + ((i - N) < 10 ? "0" + (i - N) : "" + (i - N)),
						target.getValueAt("destComputerList1." + i + ".destBrand"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destMemSize"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destPrice"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destDiscount"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destQuantity"));
				Assert.assertNull("Append valued IGNORE.", target.getValueAt("destComputerList1." + i + ".destComments"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Append = FALSE(all)<br>
	 * AND <br>
	 * Source elements LESS than target elements in size
	 */
	@Test
	public void testAppendAllFalseWithSourceElesLessThanTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendAllFalseWithSourceElesNEQToTargetElesFmt");

			int M = 8, N = 16;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);

			// checking the target context
			Assert.assertEquals(M, ((IndexedCollection) target.getElementAt("destComputerList1")).size());
			for (int i = 0; i < M; i++) {
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destBrand"));
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destMemSize"));
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destPrice"));
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destDiscount"));
				Assert.assertNull("Append FALSE, so overwrite by later mapping elements.",
						target.getValueAt("destComputerList1." + i + ".destQuantity"));
				Assert.assertEquals("" + i, target.getValueAt("destComputerList1." + i + ".destComments"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Append = TRUE(all)<br>
	 * AND <br>
	 * Source elements more than target elements in size<br><br>
	 * <b>Target elements after mapping are look like below: the blank cells are all NULL</b><br>
	 * <table border="1" >
	 * <tr align="center" >
	 * <td><b>destBrand</b></td>
	 * <td><b>destCpuFreq</b></td>
	 * <td><b>destMemSize</b></td>
	 * <td><b>destPrice</b></td>
	 * <td><b>destDiscount</b></td>
	 * <td><b>destQuantity</b></td>
	 * <td><b>destComments</b></td>
	 * </tr>
	 * <tr align="center">
	 * <td>O</td>
	 * <td>R</td>
	 * <td>G</td>
	 * <td>V</td>
	 * <td>A</td>
	 * <td>L</td>
	 * <td>S</td>
	 * </tr>
	 * <tr align="center">
	 * <td>O</td>
	 * <td>R</td>
	 * <td>G</td>
	 * <td>V</td>
	 * <td>A</td>
	 * <td>L</td>
	 * <td>S</td>
	 * </tr>
	 * <tr align="center">
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * <td>&nbsp;</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * </tr>
	 * <tr align="center">
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>&nbsp;</td>
	 * <td>*</td>
	 * </tr>
	 * </table>
	 */
	@Test
	public void testAppendAllTrueWithSourceElesMoreThanTargetEles() {
		try {
			Context source = getContextByName("SrcComputerCtxt");
			Context target = getContextByName("DestComputerCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("AppendAllTrueWithSourceElesNEQToTargetElesFmt");

			// mapping elements defined in the format
			int MAPELENUM = 7;

			int M = 32, N = 16;
			// initialize the source context
			initializeSourceContext(source, M);

			// initialize the target context
			initializeTargetContext(target, N);

			// mapping the source to target
			fmt.mapContents(source, target);
			System.out.println(target.getKeyedCollection());

			// checking the target context
			Assert.assertEquals(N + M * MAPELENUM, ((IndexedCollection) target.getElementAt("destComputerList1")).size());
			for (int i = 0; i < N + M * MAPELENUM; i++) {
				if (i < N) {
					// check the original values
					Assert.assertEquals("BTT-TPD-X" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destBrand"));
					Assert.assertEquals(i, target.getValueAt("destComputerList1." + i + ".destCpuFreq"));
					Assert.assertEquals(new BigInteger(String.valueOf(1048576 + i)),
							target.getValueAt("destComputerList1." + i + ".destMemSize"));
					Assert.assertEquals(new BigDecimal(1024 + i), target.getValueAt("destComputerList1." + i + ".destPrice"));
					Assert.assertEquals(0.7D, target.getValueAt("destComputerList1." + i + ".destDiscount"));
					Assert.assertEquals((long) 1 + i, target.getValueAt("destComputerList1." + i + ".destQuantity"));
					Assert.assertEquals("000" + (i < 10 ? "0" + i : "" + i),
							target.getValueAt("destComputerList1." + i + ".destComments"));
				} else {
					// check the values mapped from source context by column
					int indicator = (i - N) / M;
					String[] nullValuesExprs = new String[6];
					String nonNullValueExpr = "";
					switch (indicator) {
					case 0: {
						nonNullValueExpr = "destBrand";
						nullValuesExprs[0] = "destCpuFreq";
						nullValuesExprs[1] = "destMemSize";
						nullValuesExprs[2] = "destPrice";
						nullValuesExprs[3] = "destDiscount";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals("IBM-TPD-X9" + ((i - N) < 10 ? "0" + (i - N) : "" + (i - N)),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 1: {
						nonNullValueExpr = "destCpuFreq";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destMemSize";
						nullValuesExprs[2] = "destPrice";
						nullValuesExprs[3] = "destDiscount";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals(Integer.MAX_VALUE - (i - N - M),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 2: {
						nonNullValueExpr = "destMemSize";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destCpuFreq";
						nullValuesExprs[2] = "destPrice";
						nullValuesExprs[3] = "destDiscount";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals(new BigInteger(String.valueOf(1048576 * (512 * (1 + ((i - N - 2 * M) % 5))))),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 3: {
						nonNullValueExpr = "destPrice";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destCpuFreq";
						nullValuesExprs[2] = "destMemSize";
						nullValuesExprs[3] = "destDiscount";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals(
								new BigDecimal(1024 * (10 + (i - N - 3 * M)) / ((i - N - 3 * M) + 1)).setScale(9, BigDecimal.ROUND_HALF_UP),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 4: {
						nonNullValueExpr = "destDiscount";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destCpuFreq";
						nullValuesExprs[2] = "destMemSize";
						nullValuesExprs[3] = "destPrice";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals(0.5D, target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 5: {
						nonNullValueExpr = "destQuantity";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destCpuFreq";
						nullValuesExprs[2] = "destMemSize";
						nullValuesExprs[3] = "destPrice";
						nullValuesExprs[4] = "destDiscount";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals((long) 1 + ((i - N - 5 * M) % 8),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					case 6: {
						nonNullValueExpr = "destComments";
						nullValuesExprs[0] = "destBrand";
						nullValuesExprs[1] = "destCpuFreq";
						nullValuesExprs[2] = "destMemSize";
						nullValuesExprs[3] = "destPrice";
						nullValuesExprs[4] = "destDiscount";
						nullValuesExprs[5] = "destQuantity";
						Assert.assertEquals("" + (i - N - 6 * M), target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
						break;
					}
					default: {// 0
						nonNullValueExpr = "destBrand";
						nullValuesExprs[0] = "destCpuFreq";
						nullValuesExprs[1] = "destMemSize";
						nullValuesExprs[2] = "destPrice";
						nullValuesExprs[3] = "destDiscount";
						nullValuesExprs[4] = "destQuantity";
						nullValuesExprs[5] = "destComments";
						Assert.assertEquals("IBM-TPD-X9" + ((i - N) < 10 ? "0" + (i - N) : "" + (i - N)),
								target.getValueAt("destComputerList1." + i + "." + nonNullValueExpr));
					}
					}

					for (int j = 0; j < nullValuesExprs.length; j++) {
						Assert.assertNull("Value should be NULL, snice the append value is TRUE for this specific mapping item",
								target.getValueAt("destComputerList1." + i + "." + nullValuesExprs[j]));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	private void initializeSourceContext(Context source, int CONST) throws Exception {
		IndexedCollection srcComputerList1 = (IndexedCollection) source.getElementAt("srcComputerList1");
		srcComputerList1.removeAll();
		for (int i = 0; i < CONST; i++) {
			KeyedCollection computer = (KeyedCollection) srcComputerList1.createElement(true);
			computer.setValueAt("srcBrand", "IBM-TPD-X9" + (i < 10 ? "0" + i : "" + i));
			computer.setValueAt("srcCpuFreq", Integer.MAX_VALUE - i);
			computer.setValueAt("srcMemSize", new BigInteger(String.valueOf(1048576 * (512 * (1 + (i % 5))))));
			computer.setValueAt("srcPrice", new BigDecimal(1024 * (10 + i) / (i + 1)));
			computer.setValueAt("srcDiscount", 0.5D);
			computer.setValueAt("srcQuantity", (long) 1 + (i % 8));
			computer.setValueAt("srcComments", "" + i);
			srcComputerList1.addElement(computer);
		}
	}

	private void initializeTargetContext(Context target, int CONST) throws Exception {
		IndexedCollection destComputerList1 = (IndexedCollection) target.getElementAt("destComputerList1");
		destComputerList1.removeAll();
		for (int i = 0; i < CONST; i++) {
			KeyedCollection computer = (KeyedCollection) destComputerList1.createElement(true);
			computer.setValueAt("destBrand", "BTT-TPD-X" + (i < 10 ? "0" + i : "" + i));
			computer.setValueAt("destCpuFreq", i);
			computer.setValueAt("destMemSize", new BigInteger(String.valueOf(1048576 + i)));
			computer.setValueAt("destPrice", new BigDecimal(1024 + i));
			computer.setValueAt("destDiscount", 0.7D);
			computer.setValueAt("destQuantity", (long) 1 + i);
			computer.setValueAt("destComments", "000" + (i < 10 ? "0" + i : "" + i));
			destComputerList1.addElement(computer);
		}
	}
}
