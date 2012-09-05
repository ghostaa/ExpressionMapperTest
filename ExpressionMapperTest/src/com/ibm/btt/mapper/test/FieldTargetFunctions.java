package com.ibm.btt.mapper.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FieldTargetFunctions {

	public static int sum(String[] numbers) {
		int result = 0;
		for (String d : numbers)
			result += Integer.parseInt(d);
		return result;
	}
	
	public static int countHigherThan(String[] numbers, Number limit) {
		int result = 0;
		for (String d : numbers) {
			if (Integer.parseInt(d) > limit.intValue())
				result ++;
		}
		return result;
	}
	
	public static Date latestDate(String[] dates) {
		Date date = null;
		SimpleDateFormat fmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		for (String d : dates) {
			Date temp;
			try {
				temp = fmt.parse(d);
			} catch (ParseException e) {
				continue;
			}
			if (date == null || temp.compareTo(date) > 0)
				date = temp;
		}
		return date;
	}
}
