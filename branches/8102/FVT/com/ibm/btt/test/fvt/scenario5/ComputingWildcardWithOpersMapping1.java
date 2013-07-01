package com.ibm.btt.test.fvt.scenario5;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ComputingWildcardWithOpersMapping1 extends CommonTestCase {
	/**
	 * 
	 * @Title: testOperatorOneLevelDeepMapping
	 * @Description:  ICOLL0.*.DataElement OPERATOR ICOLL0.*.DataElement void
	 *               Mar 15, 2012 1:54:44 PM
	 */
	@Test
	public void testOperatorOneLevelDeepMapping() {
		try {
			String[] stringsArray = new String[] { "string1", "string2" };
			BigInteger[] bigIntegersArray = new BigInteger[] { new BigInteger("123"), new BigInteger("456") };
			BigDecimal[] bigDecimalsArray = new BigDecimal[] { new BigDecimal("123"), new BigDecimal("456") };
			Integer[] integersArray = new Integer[] { 111, 222 };
			Context source = getContextByName("SrcOneLvlICollKCollCtx");
			Context target = getContextByName("SrcOneLvlICollKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorOneLevelICollFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcStringField", stringsArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBIField", bigIntegersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField", integersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBDField", bigDecimalsArray[i]);
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				Assert.assertEquals(stringsArray[i] + stringsArray[i], target.getValueAt("SrcOneLvlIColl." + i + ".SrcStringField"));
				Assert.assertEquals(bigIntegersArray[i].subtract(bigIntegersArray[i]),
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcBIField"));
				Assert.assertEquals(integersArray[i] * integersArray[i], target.getValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField"));
				Assert.assertEquals(new BigDecimal("1.000000000"), target.getValueAt("SrcOneLvlIColl." + i + ".SrcBDField"));
				Assert.assertEquals(stringsArray[i] + stringsArray[i] + stringsArray[i],
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcPlainField"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testOperatorMultiLevelDeepMapping
	 * @Description:  ICOLL0.*.ICOLL2.*.DataElement OPERATOR
	 *               ICOLL0.*.ICOLL2.*.DataElement void Mar 15, 2012 2:38:48 PM
	 */
	@Test
	public void testOperatorMultiLevelDeepMapping() {
		try {
			String stringOne = "string1";
			String stringTwo = "string2";
			Integer intOne = 10;
			Integer intTwo = 5;
			BigInteger bigIntegerOne = new BigInteger("60");
			BigInteger bigIntegerTwo = new BigInteger("30");
			BigDecimal bigDecimalOne = new BigDecimal("9");
			BigDecimal bigDecimalTwo = new BigDecimal("3");
			Context source = getContextByName("OperSixLevelICollCtxKCollCtx");
			Context target = getContextByName("OperSixLevelICollCtxKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorMultiLevelICollFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldOne", stringOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldTwo", stringTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldOne", intOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldOne", bigIntegerOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldTwo", bigIntegerTwo);

					for (int k = 0; k < 2; k++) {
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldOne", intOne);
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);

						for (int ii = 0; ii < 2; ii++) {
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldOne", bigDecimalOne);
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldTwo", bigDecimalTwo);
							for (int jj = 0; jj < 2; jj++) {
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.IntegerFieldOne", intOne);
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.BIFieldOne", bigIntegerOne);
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.BIFieldTwo", bigIntegerTwo);
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.BDFieldOne", bigDecimalOne);
								source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
										+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.BDFieldTwo", bigDecimalTwo);

							}
						}
					}
				}
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					Assert.assertEquals(stringOne + stringTwo,
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldResult"));
					Assert.assertEquals(intOne - intTwo,
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldResult"));
					Assert.assertEquals(bigIntegerOne.add(bigIntegerTwo).add(new BigInteger(intOne.toString())),
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldResult"));

					for (int k = 0; k < 2; k++) {
						Assert.assertEquals(
								intOne * intTwo,
								target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
										+ ".OperatorTypedDataRec.IntegerFieldResult"));
						for (int ii = 0; ii < 2; ii++) {
							Assert.assertEquals(
									new BigDecimal("3.000000000"),
									target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
											+ ".OperatorTypedDataRec.BDFieldResult"));
							for (int jj = 0; jj < 2; jj++) {
								Assert.assertEquals(
										new BigDecimal("2.000000000"),
										target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
												+ ".OperL5IColl." + jj + ".OperatorTypedDataRec.BDFieldResult"));

							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testOperatorOneLevelDeepConstantMapping
	 * @Description:  ICOLL0.*.DataElement OPERATOR CONSTANT void Mar 15, 2012
	 *               4:49:26 PM
	 */
	@Test
	public void testOperatorOneLevelDeepConstantMapping() {
		try {
			String[] stringsArray = new String[] { "string1", "string2" };
			BigInteger[] bigIntegersArray = new BigInteger[] { new BigInteger("90"), new BigInteger("30") };
			BigDecimal[] bigDecimalsArray = new BigDecimal[] { new BigDecimal("30"), new BigDecimal("10") };
			Integer[] integersArray = new Integer[] { 6, 3 };
			Context source = getContextByName("SrcOneLvlICollKCollCtx");
			Context target = getContextByName("SrcOneLvlICollKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorOneLevelICollConstantFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcStringField", stringsArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBIField", bigIntegersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField", integersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBDField", bigDecimalsArray[i]);
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				Assert.assertEquals(stringsArray[i] + "abc", target.getValueAt("SrcOneLvlIColl." + i + ".SrcStringField"));
				Assert.assertEquals(bigIntegersArray[i].subtract(new BigInteger("5")),
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcBIField"));
				Assert.assertEquals(integersArray[i] * 5, target.getValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField"));
				Assert.assertEquals(bigDecimalsArray[i].divide(new BigDecimal(5)).setScale(9),
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcBDField"));
				Assert.assertEquals(stringsArray[i] + "5" + "5", target.getValueAt("SrcOneLvlIColl." + i + ".SrcPlainField"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testOperatorMultiLevelDeepConstantMapping
	 * @Description:  ICOLL0.*.ICOLL2.*.DataElement OPERATOR CONSTANT void Mar
	 *               15, 2012 5:47:35 PM
	 */
	@Test
	public void testOperatorMultiLevelDeepConstantMapping() {
		try {
			String stringOne = "string1";
			String stringTwo = "string2";
			Integer intOne = 10;
			Integer intTwo = 5;
			BigInteger bigIntegerOne = new BigInteger("60");
			BigInteger bigIntegerTwo = new BigInteger("30");
			BigDecimal bigDecimalOne = new BigDecimal("9");
			BigDecimal bigDecimalTwo = new BigDecimal("3");
			Context source = getContextByName("OperSixLevelICollCtxKCollCtx");
			Context target = getContextByName("OperSixLevelICollCtxKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorMultiLevelICollConstantFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldOne", stringOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldTwo", stringTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldOne", intOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldOne", bigIntegerOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldTwo", bigIntegerTwo);

					for (int k = 0; k < 2; k++) {
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldOne", intOne);
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);

						for (int ii = 0; ii < 2; ii++) {
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldOne", bigDecimalOne);
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldTwo", bigDecimalTwo);

						}
					}
				}
			}
			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					Assert.assertEquals(stringOne + "abc",
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldResult"));
					Assert.assertEquals(intOne - 5,
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldResult"));
					Assert.assertEquals(bigIntegerOne.add(bigIntegerTwo).add(new BigInteger(new Integer(intOne + 5).toString())),
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldResult"));
					for (int k = 0; k < 2; k++) {
						Assert.assertEquals(
								intOne * 5,
								target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
										+ ".OperatorTypedDataRec.IntegerFieldResult"));
						for (int ii = 0; ii < 2; ii++) {
							Assert.assertEquals(
									new BigDecimal("3.000000000"),
									target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
											+ ".OperatorTypedDataRec.BDFieldResult"));

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * 
	 * @Title: testOperatorOneLevelDeepDataElementMapping
	 * @Description:  ICOLL0.*.DataElement OPERATOR DataElement void Mar 15,
	 *               2012 5:43:01 PM
	 */
	@Test
	public void testOperatorOneLevelDeepDataElementMapping() {
		try {
			String[] stringsArray = new String[] { "string1", "string2" };
			BigInteger[] bigIntegersArray = new BigInteger[] { new BigInteger("90"), new BigInteger("30") };
			BigDecimal[] bigDecimalsArray = new BigDecimal[] { new BigDecimal("30"), new BigDecimal("10") };
			Integer[] integersArray = new Integer[] { 6, 3 };
			Context source = getContextByName("SrcOneLvlICollKCollCtx");
			Context target = getContextByName("SrcOneLvlICollKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorOneLevelICollDataElementFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcStringField", stringsArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBIField", bigIntegersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField", integersArray[i]);
				source.setValueAt("SrcOneLvlIColl." + i + ".SrcBDField", bigDecimalsArray[i]);

			}
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.StringFieldOne",
					"aaa");
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.BIFieldOne",
					new BigInteger("5"));
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.IntegerFieldOne", 5);
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.BDFieldOne",
					new BigDecimal("5"));
			source
					.setValueAt(
							"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.PlainFieldOne",
							"5x");

			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				Assert.assertEquals(stringsArray[i] + "aaa", target.getValueAt("SrcOneLvlIColl." + i + ".SrcStringField"));
				Assert.assertEquals(bigIntegersArray[i].subtract(new BigInteger("5")),
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcBIField"));
				Assert.assertEquals(integersArray[i] * 5, target.getValueAt("SrcOneLvlIColl." + i + ".SrcIntegerField"));
				Assert.assertEquals(bigDecimalsArray[i].divide(new BigDecimal(5)).setScale(9),
						target.getValueAt("SrcOneLvlIColl." + i + ".SrcBDField"));
				Assert.assertEquals(stringsArray[i] + "5x" + "5", target.getValueAt("SrcOneLvlIColl." + i + ".SrcPlainField"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	@Test
	public void testOperatorMultiLevelDeepDataElementMapping() {
		try {
			String stringOne = "string1";
			String stringTwo = "string2";
			Integer intOne = 10;
			Integer intTwo = 5;
			BigInteger bigIntegerOne = new BigInteger("60");
			BigInteger bigIntegerTwo = new BigInteger("30");
			BigDecimal bigDecimalOne = new BigDecimal("9");
			BigDecimal bigDecimalTwo = new BigDecimal("3");
			Context source = getContextByName("OperSixLevelICollCtxKCollCtx");
			Context target = getContextByName("OperSixLevelICollCtxKCollCtx");
			DataMapperExpressionConverterFormat fmt = getFormatByName("OperatorMultiLevelICollDataElementFmt");
			source.getKeyedCollection().setDynamic(true);
			// initialize the source context values
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldOne", stringOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldTwo", stringTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldOne", intOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldOne", bigIntegerOne);
					source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldTwo", bigIntegerTwo);

					for (int k = 0; k < 2; k++) {
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldOne", intOne);
						source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
								+ ".OperatorTypedDataRec.IntegerFieldTwo", intTwo);

						for (int ii = 0; ii < 2; ii++) {
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldOne", bigDecimalOne);
							source.setValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
									+ ".OperatorTypedDataRec.BDFieldTwo", bigDecimalTwo);

						}
					}
				}
			}
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.StringFieldOne",
					"aaa");
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.BIFieldOne",
					new BigInteger("5"));
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.IntegerFieldOne", 5);
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.BDFieldOne",
					new BigDecimal("3"));
			source.setValueAt(
					"SrcOperatorOneLevelKColl.OperatorTwoLevelKColl.OperatorThreeLevelKColl.OperatorTypedDataRecCopy.PlainFieldOne", "5");

			fmt.mapContents(source, target);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					Assert.assertEquals(stringOne + "aaa",
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.StringFieldResult"));
					Assert.assertEquals(intOne - 5,
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.IntegerFieldResult"));
					Assert.assertEquals(new BigInteger("1005"),
							target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperatorTypedDataRec.BIFieldResult"));
					for (int k = 0; k < 2; k++) {
						Assert.assertEquals(
								intOne * 5,
								target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k
										+ ".OperatorTypedDataRec.IntegerFieldResult"));
						for (int ii = 0; ii < 2; ii++) {
							Assert.assertEquals(
									new BigDecimal("3.000000000"),
									target.getValueAt("OperL1IColl." + i + ".OperL2IColl." + j + ".OperL3IColl." + k + ".OperL4IColl." + ii
											+ ".OperatorTypedDataRec.BDFieldResult"));

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
