package cn.gzggzy.yyh.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	
	private static DateFormat getInstance(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	public static String toChar(String pattern) {
		Calendar c = Calendar.getInstance();
		DateFormat sdf = getInstance(pattern);
		return sdf.format(c.getTime());
	}
	
	public static String toChar(Date date, String pattern) {
		DateFormat sdf = getInstance(pattern);
		return sdf.format(date);
	}
	
}
