package com.ibm.btt.test.fvt.scenario7;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class S7PersonFunctions {
	/**
	 * calculate the average age
	 * 
	 * @param strAges
	 * @return
	 */
	public static int averageAge(String[] strAges) {
		if (isStrArrayNull(strAges))
			return 0;

		int sumAge = 0;
		for (int i = 0; i < strAges.length; i++) {
			sumAge += Integer.parseInt(strAges[i]);
		}
		return sumAge / strAges.length;
	}

	/**
	 * Summary the pocket money
	 * 
	 * @param strPocketMoney
	 * @return
	 */
	public static BigDecimal summaryPocketMoney(String[] strPocketMoney) {
		if (isStrArrayNull(strPocketMoney))
			return BigDecimal.ZERO.setScale(9);
		BigDecimal sumPocketMoney = BigDecimal.ZERO;
		for (String str : strPocketMoney)
			sumPocketMoney = sumPocketMoney.add(new BigDecimal(str));
		return sumPocketMoney;
	}

	/**
	 * Summary the genders while age is greater than @param theAge(if theAge is
	 * null, use default @param constantAge instead)
	 * 
	 * @return "Age greater than *, we have Female: *; Male: *.
	 */
	public static String summaryGenderWithFilter(String[] strAges, String[] strGenders, String theAge, Integer constantAge) {
		int male = 0, female = 0;
		if (null == theAge || "".equals(theAge) || "undefined".equals(theAge))
			theAge = String.valueOf(constantAge);
		if (!isStrArrayNull(strGenders) && !isStrArrayNull(strAges)) {
			for (int i = 0; i < strGenders.length; i++) {
				if (strAges[i].compareTo(theAge) >= 0) {
					String str = strGenders[i];
					if (Boolean.parseBoolean(str))
						male++;
					else
						female++;
				}
			}
		}
		return new StringBuilder().append("Age greater than ").append(theAge).append(", we have Female: ").append(female)
				.append("; Male: ").append(male).append(".").toString();
	}

	/**
	 * Summary the genders
	 * 
	 * @param strGenders
	 * @return "Female: *; Male: *.
	 */
	public static String summaryGender(String[] strGenders) {
		int male = 0, female = 0;
		if (!isStrArrayNull(strGenders)) {
			for (String str : strGenders) {
				if (Boolean.parseBoolean(str))
					male++;
				else
					female++;
			}
		}
		return new StringBuilder().append("Female: ").append(female).append("; Male: ").append(male).append(".").toString();
	}

	/**
	 * calculate the minimum value of activate time
	 * 
	 * @param strActiveTime
	 * @return
	 */
	public static long minimumActiveTime(String strActiveTime[]) {
		if (isStrArrayNull(strActiveTime))
			return 0l;
		Arrays.sort(strActiveTime);
		return Long.parseLong(strActiveTime[0]);
	}

	/**
	 * calculate the maximum value of birth year
	 * 
	 * @param strBirthDay
	 * @return
	 */
	public static int maximumBirthDate(String strBirthDay[]) {
		if (isStrArrayNull(strBirthDay))
			return 1900;
		strBirthDay = changeDateFormat(strBirthDay);
		StringGroupContainer lc = new StringGroupContainer();
		for (String str : strBirthDay) {
			lc.put(str.substring(0, 4));
		}
		return Integer.parseInt(lc.getMaxStrGroup());
	}

	/**
	 * return the most used birth month
	 * 
	 * @param strBirthDay
	 * @return
	 */
	public static byte mostUsedBirthMonth(String[] strBirthDay) {
		if (isStrArrayNull(strBirthDay))
			return 0;
		strBirthDay = changeDateFormat(strBirthDay);
		StringGroupContainer lc = new StringGroupContainer();
		for (String str : strBirthDay) {
			lc.put(str.substring(4, 6));
		}
		return (byte) Integer.parseInt(lc.getMaxStrGroup());
	}

	public static String maximumLastName(String strLastNames[]) {
		if (isStrArrayNull(strLastNames))
			return "";
		StringGroupContainer lc = new StringGroupContainer();
		for (String str : strLastNames) {
			lc.put(str);
		}
		return lc.getMaxStrGroup();
	}

	private static boolean isStrArrayNull(String[] str) {
		return (null == str || 0 == str.length);
	}
	
	@SuppressWarnings("deprecation")
	private static String[] changeDateFormat(String strBirthDay[]){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for(int i=0;i<strBirthDay.length;i++){
			Date date = new Date(strBirthDay[i]);
			strBirthDay[i] = sdf.format(date);
		}
		return strBirthDay;
	}

	static class StringGroupContainer {
		public StringGroupContainer() {

		}

		List<StringGroupRank> lastNameRanks = new LinkedList<StringGroupRank>();

		public boolean put(String lastName) {
			StringGroupRank lnr = new StringGroupRank(lastName);
			if (!lastNameRanks.contains(lnr))
				return lastNameRanks.add(lnr);
			else
				for (StringGroupRank lr : lastNameRanks) {
					if (lr.equals(lnr)) {
						lr.increaseRank();
						return true;
					}
				}
			return false;
		}

		public String getMaxStrGroup() {
			Collections.sort(lastNameRanks);
			return lastNameRanks.get(0).getStrGroup();
		}

		public String getMinStrGroup() {
			Collections.sort(lastNameRanks);
			return lastNameRanks.get(lastNameRanks.size() - 1).getStrGroup();
		}
	}

	static class StringGroupRank implements Comparable<StringGroupRank> {
		public StringGroupRank(String strGroup) {
			this.strGroup = strGroup;
			rank++;
		}

		private String strGroup = "";
		private Integer rank = 0;

		public String getStrGroup() {
			return strGroup;
		}

		public void setStrGroup(String strGroup) {
			this.strGroup = strGroup;
		}

		public Integer getRank() {
			return rank;
		}

		public void setRank(Integer rank) {
			this.rank = rank;
		}

		public Integer increaseRank() {
			return ++this.rank;
		}

		@Override
		public boolean equals(Object o) {
			StringGroupRank lnr = (StringGroupRank) o;
			return strGroup.equals(lnr.getStrGroup());
		}

		/**
		 * DESCEND
		 */
		@Override
		public int compareTo(StringGroupRank o) {
			return o.getRank().compareTo(this.getRank());
		}
	}

	// ////////////////////////////////////////////////////////
	/**
	 * <strong>* REQUIRES ALL THE ARRAY PARAMETERS HAVE SAME LENGTH<strong>
	 * Calculate the real amount of books that customer bought<br>
	 * 
	 * @param strPrices
	 * @param strQuantities
	 * @param strDiscounts
	 * @return
	 */
	public static BigDecimal calculateRealSumAmount(String[] strPrices, String[] strQuantities, String[] strDiscounts) {
		if (isStrArrayNull(strPrices) || isStrArrayNull(strQuantities) || isStrArrayNull(strDiscounts))
			return BigDecimal.ZERO;
		BigDecimal realSumAmt = BigDecimal.ZERO;
		for (int i = 0; i < strPrices.length; i++) {
			if (null == strPrices[i] || null == strQuantities[i] || null == strDiscounts[i])
				continue;
			// price, quantity and its discount
			String[] strCurrency = strPrices[i].split(":");
			BigDecimal price = new BigDecimal(strCurrency[1]);
			BigDecimal quantity = new BigDecimal(strQuantities[i]);
			BigDecimal discount = new BigDecimal(strDiscounts[i]);
			BigDecimal realAmt = price.multiply(quantity).multiply(discount);
			realSumAmt = realSumAmt.add(realAmt);
		}
		return realSumAmt.setScale(9, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * <strong>* REQUIRES ALL THE ARRAY PARAMETERS HAVE SAME LENGTH<strong>
	 * Calculate the amount of books that customer bought<br>
	 * 
	 * @param strPrices
	 * @param strQuantities
	 * @return
	 */
	public static BigDecimal calculateSumAmount(String[] strPrices, String[] strQuantities) {
		if (isStrArrayNull(strPrices) || isStrArrayNull(strQuantities))
			return BigDecimal.ZERO;
		BigDecimal realSumAmt = BigDecimal.ZERO;
		for (int i = 0; i < strPrices.length; i++) {
			if (null == strPrices[i] || null == strQuantities[i])
				continue;
			// price, quantity
			String[] strCurrency = strPrices[i].split(":");
			BigDecimal price = new BigDecimal(strCurrency[1]);
			BigDecimal quantity = new BigDecimal(strQuantities[i]);
			BigDecimal realAmt = price.multiply(quantity);
			realSumAmt = realSumAmt.add(realAmt);
		}
		return realSumAmt.setScale(9, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Calculate the summary quantity of books that customer bought<br>
	 * 
	 * @param strQuantities
	 * @return
	 */
	public static BigInteger calculateSumQuantity(String[] strQuantities) {
		if (isStrArrayNull(strQuantities))
			return BigInteger.ZERO;
		BigInteger qty = BigInteger.ZERO;
		for (int i = 0; i < strQuantities.length; i++) {
			if (null == strQuantities[i])
				continue;
			// quantity
			BigInteger quantity = new BigInteger(strQuantities[i]);
			qty = qty.add(quantity);
		}
		return qty;
	}
	// ////////////////////////////////////////////////////////
}
