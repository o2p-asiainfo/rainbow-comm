package com.linkage.rainbow.util.expr.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式处理类
 * @version 1.0
 * @author 陈亮 2011-03-04
 *         <hr>
 *         修改记录
 *         <hr>
 *         1、修改人员:陈亮 修改时间:2011-03-04<br>
 *         修改内容:新建
 *         <hr>
 *
 */
public class DateUtil {
	private static DateFormat dateF = null;

	private static DateFormat timeF = null;

	private static DateFormat timestampF = null;
	static {
		dateF = new SimpleDateFormat("yyyy-MM-dd");
		timeF = new SimpleDateFormat("HH:mm:ss");
		timestampF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化输出日期为String,格式为:yyyy-MM-dd
	 * @param date 日期对象
	 * @return 格式为:yyyy-MM-dd
	 */
	public static String formatDate(Date date) {
		return dateF.format(date);
	}

	/**
	 * 格式化输出时间为String,格式为:HH:mm:ss
	 * @param date 日期对象
	 * @return 格式为:HH:mm:ss
	 */
	public static String formatTime(Date date) {
		return timeF.format(date);
	}

	/**
	 * 格式化输出时间为String,格式为:HH:mm:ss
	 * @param date 日期对象
	 * @return 格式为:HH:mm:ss
	 */
	public static String formatTimestamp(Date date) {
		return timestampF.format(date);
	}

	/**
	 * 字符串转为日期类,格式为:yyyy-MM-dd
	 * @param date 字符串转为日期类,格式为:yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date parseDate(String data) throws ParseException {
		if (data == null)
			return null;
		else
			return dateF.parse(data);
	}

	public static Date parseDate(String data, int beginIndex)
			throws ParseException {
		if (data == null)
			return null;
		else
			return dateF.parse(data, new ParsePosition(beginIndex));
	}

	/**
	 * 字符串转为日期类,格式为:HH:mm:ss
	 * @param date 字符串转为日期类,格式为:HH:mm:ss
	 * @return 日期对象
	 */
	public static Date parseTime(String data) throws ParseException {
		if (data == null)
			return null;
		else
			return timeF.parse(data);
	}

	public static Date parseTime(String data, int beginIndex)
			throws ParseException {
		if (data == null)
			return null;
		else
			return timeF.parse(data, new ParsePosition(beginIndex));
	}
	/**
	 * 字符串转为日期类,格式为:yyyy-MM-dd HH:mm:ss
	 * @param date 字符串转为日期类,格式为:yyyy-MM-dd HH:mm:ss
	 * @return 日期对象
	 */
	public static Date parseTimestamp(String data) throws ParseException {
		if (data == null)
			return null;
		else
			return timestampF.parse(data);
	}

	public static Date parseTimestamp(String data, int beginIndex)
			throws ParseException {
		if (data == null)
			return null;
		else
			return timestampF.parse(data, new ParsePosition(beginIndex));
	}
	
	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar =  Calendar.getInstance();
		//calendar.setTime(new Date());
		for(int i=1;i<=31 ;i++){
			calendar.set(2011, 6,i);
			//calendar.setFirstDayOfWeek(1);
			int WEEK_OF_MONTH =  calendar.get(calendar.WEEK_OF_MONTH);
			System.out.println(WEEK_OF_MONTH);
		}
//		int DAY_OF_MONTH =  calendar.get(calendar.DAY_OF_MONTH);
//		System.out.println(DAY_OF_MONTH);
//		int MONTH =  calendar.get(calendar.MONTH);
//		System.out.println(MONTH);
//		System.out.println( 3/4);
		
	}
}
