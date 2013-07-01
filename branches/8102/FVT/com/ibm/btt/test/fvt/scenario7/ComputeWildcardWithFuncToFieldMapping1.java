package com.ibm.btt.test.fvt.scenario7;

import java.math.BigDecimal;
import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import com.ibm.btt.base.Context;
import com.ibm.btt.base.DataMapperExpressionConverterFormat;
import com.ibm.btt.base.IndexedCollection;
import com.ibm.btt.base.KeyedCollection;
import com.ibm.btt.base.types.impl.Currency;
import com.ibm.btt.test.fvt.common.CommonTestCase;

public class ComputeWildcardWithFuncToFieldMapping1 extends CommonTestCase {

	/**
	 * global function within expression to wildcard mapping
	 */
	@Test
	public void testSingleLevelS7PersonStatiscalDataMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("SingleLevelS7PersonStatiscalDataFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			long cur = 20120319185828178L;
			int looperSum = 0;
			BigDecimal pma = BigDecimal.ZERO;
			for (int i = 0; i < 16; i++) {
				String lastName = i % 3 == 0 ? i % 2 == 0 ? "LIU" : "LI" : "LV";
				source.setValueAt("S7PL1I." + i + ".lastName", lastName);
				source.setValueAt("S7PL1I." + i + ".extrNames", "Person " + i);
				// TODO change the birthday value if the toString way is changed
				source.setValueAt("S7PL1I." + i + ".birthDay", "201" + (i % 10) + "1" + (i % 3) + "1" + (i % 10));
				source.setValueAt("S7PL1I." + i + ".age", (byte) (26 + i));
				BigDecimal bd = new BigDecimal("1048576.12").multiply(new BigDecimal(16 + i));
				source.setValueAt("S7PL1I." + i + ".moneyInPocket", bd);
				source.setValueAt("S7PL1I." + i + ".activeSince", cur + i);
				source.setValueAt("S7PL1I." + i + ".gender", i % 3 == 0);
				looperSum += i;
				pma = pma.add(bd);
			}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			int bAge = 26 + looperSum / 16;
			Assert.assertEquals(bAge, target.getValueAt("groupedData.avgAge"));
			Assert.assertEquals(pma.setScale(9), target.getValueAt("groupedData.sumPocketMoney"));
			Assert.assertEquals("Female: 10; Male: 6.", target.getValueAt("groupedData.genderSummary"));
			Assert.assertEquals(cur, target.getValueAt("groupedData.minActiveTime"));
			Assert.assertEquals("LV", target.getValueAt("groupedData.maxLastName"));
			Assert.assertEquals(2010, target.getValueAt("groupedData.maxBirthYear"));
			Assert.assertEquals((byte) 10, target.getValueAt("groupedData.mostBirthMonth"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * global function within expression to wildcard mapping in 3 levels of icoll
	 */
	@Test
	public void testThreeLevelS7PersonStatiscalDataMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("ThreeLevelS7PersonStatiscalDataFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			long cur = 20120319185828178L;
			int looperSum = 0;
			BigDecimal pma = BigDecimal.ZERO;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					for (int k = 0; k < 16; k++) {
						String lastName = i % 3 == 0 ? j % 2 == 0 ? "LIU" : "LI" : "LV";
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".lastName", lastName);
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".extrNames", "Person " + i * j * k);
						// TODO change the birthday value if the toString way is changed
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".birthDay", "201" + (i % 10) + "1" + (i % 3)
								+ "1" + (i % 10));
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".age", (byte) (26 + i + j + k));
						BigDecimal bd = new BigDecimal("1048576.12").multiply(new BigDecimal(16 + i + j + k));
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".moneyInPocket", bd);
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".activeSince", cur + i + j + k);
						source.setValueAt("S7PL3I." + i + ".S7PL2I." + j + ".S7PL1I." + k + ".gender", (i + j + k) % 3 == 0);
						looperSum += (i + j + k);
						pma = pma.add(bd);
					}
				}
			}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			int bAge = 26 + looperSum / (4 * 8 * 16);
			Assert.assertEquals(bAge, target.getValueAt("groupedData.avgAge"));
			Assert.assertEquals(pma.setScale(9), target.getValueAt("groupedData.sumPocketMoney"));
			Assert.assertEquals("Female: 341; Male: 171.", target.getValueAt("groupedData.genderSummary"));
			Assert.assertEquals(cur, target.getValueAt("groupedData.minActiveTime"));
			Assert.assertEquals("LV", target.getValueAt("groupedData.maxLastName"));
			Assert.assertEquals(2010, target.getValueAt("groupedData.maxBirthYear"));
			Assert.assertEquals((byte) 10, target.getValueAt("groupedData.mostBirthMonth"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * global function within expression to wildcard mapping in 5 levels of icoll
	 */
	@Test
	public void testFiveLevelS7PersonStatiscalDataMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FiveLevelS7PersonStatiscalDataFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			long cur = 20120319185828178L;
			int looperSum = 0;
			BigDecimal pma = BigDecimal.ZERO;
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 8; j++)
					for (int k = 0; k < 16; k++)
						for (int m = 0; m < 8; m++)
							for (int n = 0; n < 4; n++) {
								String lastName = i % 3 == 0 ? j % 2 == 0 ? "LIU" : "LI" : "LV";
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".lastName", lastName);
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".extrNames", "Person " + i * j * k + m + n);
								// TODO change the birthday value if the toString way is changed
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".birthDay", "201" + (i % 10) + "1" + (i % 3) + "1" + (i % 10));
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n + ".age",
										(byte) (1 + i + j + k + m + n));
								BigDecimal bd = new BigDecimal("1048576.12").multiply(new BigDecimal(16 + i + j + k + m + n));
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".moneyInPocket", bd);
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".activeSince", cur + i + j + k + m + n);
								source.setValueAt(
										"S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n + ".gender", (i + j + k
												+ m + n) % 3 == 0);
								looperSum += (i + j + k + m + n);
								pma = pma.add(bd);
							}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			int bAge = 1 + looperSum / (4 * 8 * 16 * 8 * 4);
			Assert.assertEquals(bAge, target.getValueAt("groupedData.avgAge"));
			Assert.assertEquals(pma.setScale(9), target.getValueAt("groupedData.sumPocketMoney"));
			Assert.assertEquals("Female: 10923; Male: 5461.", target.getValueAt("groupedData.genderSummary"));
			Assert.assertEquals(cur, target.getValueAt("groupedData.minActiveTime"));
			Assert.assertEquals("LV", target.getValueAt("groupedData.maxLastName"));
			Assert.assertEquals(2010, target.getValueAt("groupedData.maxBirthYear"));
			Assert.assertEquals((byte) 10, target.getValueAt("groupedData.mostBirthMonth"));
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
	public void testSingleLevelS7PersonStatiscalFilterDataMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("SingleLevelS7PersonStatiscalFilterDataFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			source.setValueAt("groupedData.genderSummary", "19");
			long cur = 20120319185828178L;
			int looperSum = 0;
			BigDecimal pma = BigDecimal.ZERO;
			for (int i = 0; i < 128; i++) {
				String lastName = i % 3 == 0 ? i % 2 == 0 ? "LIU" : "LI" : "LV";
				source.setValueAt("S7PL1I." + i + ".lastName", lastName);
				source.setValueAt("S7PL1I." + i + ".extrNames", "Person " + i);
				// TODO change the birthday value if the toString way is changed
				source.setValueAt("S7PL1I." + i + ".birthDay", "201" + (i % 10) + "1" + (i % 3) + "1" + (i % 10));
				source.setValueAt("S7PL1I." + i + ".age", (byte) ((26 + i) % 120));
				BigDecimal bd = new BigDecimal("1048576.12").multiply(new BigDecimal(16 + i));
				source.setValueAt("S7PL1I." + i + ".moneyInPocket", bd);
				source.setValueAt("S7PL1I." + i + ".activeSince", cur + i);
				source.setValueAt("S7PL1I." + i + ".gender", i % 3 == 0);
				looperSum += i;
				pma = pma.add(bd);
			}
			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			Assert.assertEquals("Age greater than 19, we have Female: 64; Male: 33.", target.getValueAt("groupedData.genderSummary"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.avgAge"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.sumPocketMoney"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.minActiveTime"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.maxLastName"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.maxBirthYear"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.mostBirthMonth"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * global function within expression to wildcard mapping in 5 levels of icoll
	 */
	@Test
	public void testFiveLevelS7PersonStatiscalFilterDataMapping() {
		try {
			Context source = getContextByName("WildcardToFieldCtxt");
			Context target = getContextByName("WildcardToFieldCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("FiveLevelS7PersonStatiscalFilterDataFmt");
			source.getKeyedCollection().setDynamic(true);
			source.setValueAt("groupedData.genderSummary", "29");

			// initialize the source context
			long cur = 20120319185828178L;
			int looperSum = 0;
			BigDecimal pma = BigDecimal.ZERO;
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 8; j++)
					for (int k = 0; k < 16; k++)
						for (int m = 0; m < 8; m++)
							for (int n = 0; n < 4; n++) {
								String lastName = i % 3 == 0 ? j % 2 == 0 ? "LIU" : "LI" : "LV";
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".lastName", lastName);
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".extrNames", "Person " + i * j * k + m + n);
								// TODO change the birthday value if the toString way is changed
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".birthDay", "201" + (i % 10) + "1" + (i % 3) + "1" + (i % 10));
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n + ".age",
										(byte) (1 + i + j + k + m + n));
								BigDecimal bd = new BigDecimal("1048576.12").multiply(new BigDecimal(16 + i + j + k + m + n));
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".moneyInPocket", bd);
								source.setValueAt("S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n
										+ ".activeSince", cur + i + j + k + m + n);
								source.setValueAt(
										"S7PL5I." + i + ".S7PL4I." + j + ".S7PL3I." + k + ".S7PL2I." + m + ".S7PL1I." + n + ".gender", (i + j + k
												+ m + n) % 3 == 0);
								looperSum += (i + j + k + m + n);
								pma = pma.add(bd);
							}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			Assert.assertEquals("Age greater than 29, we have Female: 1362; Male: 346.",
					target.getValueAt("groupedData.genderSummary"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.avgAge"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.sumPocketMoney"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.minActiveTime"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.maxLastName"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.maxBirthYear"));
			Assert.assertNull("Not inclued in mapping definition", target.getValueAt("groupedData.mostBirthMonth"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Calculate the real sum amount of books bought
	 */
	@Test
	public void testCalculateBookPayInfoDataMapping() {
		try {
			Context source = getContextByName("BooksInCartCtxt");
			Context target = getContextByName("BooksInCartCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("CalculateBookPayInfoFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			IndexedCollection booksList = (IndexedCollection) source.getElementAt("booksList");
			for (int i = 0; i < 8; i++) {
				KeyedCollection book = (KeyedCollection) booksList.createElement(true);
				book.setValueAt("bookName", "朝那些事儿" + (i + 1));
				book.setValueAt("bookBarCode", "978780165575" + (i + 1));
				book.setValueAt("bookPrice", new Currency("CNY", new BigDecimal(25.90)));
				book.setValueAt("bookQuantity", new BigInteger("20"));
				book.setValueAt("bookDiscount", new BigDecimal(0.5));
				book.setValueAt("comments", "Send me the new book. NO DAMAGE!!");
				booksList.addElement(book);
			}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			Assert.assertEquals(new BigInteger("160"), target.getValueAt("sumQuantity"));
			Assert.assertEquals(new BigDecimal("4144.000000000"), target.getValueAt("sumAmount"));
			Assert.assertEquals(new BigDecimal("2072.000000000"), target.getValueAt("realSumAmount"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}

	/**
	 * Calculate the real sum amount of books bought
	 */
	@Test
	public void testCalculateBookPayInfoDataMappingWithPartialNullValues() {
		try {
			Context source = getContextByName("BooksInCartCtxt");
			Context target = getContextByName("BooksInCartCtxt");
			DataMapperExpressionConverterFormat fmt = getFormatByName("CalculateBookPayInfoFmt");
			source.getKeyedCollection().setDynamic(true);

			// initialize the source context
			IndexedCollection booksList = (IndexedCollection) source.getElementAt("booksList");
			for (int i = 0; i < 8; i++) {
				KeyedCollection book = (KeyedCollection) booksList.createElement(true);
				book.setValueAt("bookName", "朝那些事儿" + (i + 1));
				book.setValueAt("bookBarCode", "978780165575" + (i + 1));
				book.setValueAt("bookPrice", new Currency("CNY", new BigDecimal(25.90)));
				book.setValueAt("bookQuantity", i == 4 ? null : new BigInteger("20"));
				book.setValueAt("bookDiscount", i == 3 ? null : new BigDecimal(0.5));
				book.setValueAt("comments", "Send me the new book. NO DAMAGE!!");
				booksList.addElement(book);
			}

			// mapping the source values to target
			fmt.mapContents(source, target);

			// check the target values
			Assert.assertEquals(new BigInteger("140"), target.getValueAt("sumQuantity"));
			Assert.assertEquals(new BigDecimal("3626.000000000"), target.getValueAt("sumAmount"));
			Assert.assertEquals(new BigDecimal("1554.000000000"), target.getValueAt("realSumAmount"));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception encountered while executing test case [" + getClass().getSimpleName()
					+ "], detailed excaption messages are:\n" + e.getMessage());
		}
	}
}
