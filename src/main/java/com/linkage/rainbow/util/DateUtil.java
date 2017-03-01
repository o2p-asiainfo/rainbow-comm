package com.linkage.rainbow.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/*************************************************
为适应不同显示风格的日期格式，
DateUtil 类对日期类进行不同类型的处理
*************************************************/
public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);
	/**
	 * 
	 * 函数名称：nowDate <br>
	 * 函数功能：获取当前日期 格式为:2008年12月22日
	 * @return String
	 */
	public static String nowDate() {
		String date = new Date().toLocaleString();
		String out = "";
		String xArr[] = date.split(" ");
		String yArr[] = xArr[0].split("-");
		out = yArr[0] + "年" + yArr[1] + "月" + yArr[2] + "日";
		return out;
	}

	/**
	 * 
	 * 函数名称：now <br>
	 * 函数功能：获取当前日期 格式为:2008年12月22日 8时52分30秒
	 * @return String
	 */
	public static String now() {
		String date = new Date().toLocaleString();
		String out = "";
		String xArr[] = date.split("-");
		out = xArr[0] + "年" + xArr[1] + "月" + xArr[2];
		String yArr[] = out.split(":");
		out = yArr[0] + "时" + yArr[1] + "分" + yArr[2];
		String zArr[] = out.split(" ");
		out = zArr[0] + "日 " + zArr[1] + "秒";
		return out;
	}

	/**
	 * 
	 * 函数名称：nowDateNDay <br>
	 * 函数功能：获取当前日期 格式为：2008年12月22日  星期一
	 * @return String
	 */
	public static String nowDateNDay() {
		Date d = new Date();
		String date = d.toLocaleString();
		String out = "";
		String xArr[] = date.split(" ");
		String yArr[] = xArr[0].split("-");
		out = yArr[0] + "年" + yArr[1] + "月" + yArr[2] + "日";
		int w = d.getDay();
		String week = "";
		switch (w) {
		case 0:
			week = " 星期天";
			break;
		case 1:
			week = " 星期一";
			break;
		case 2:
			week = " 星期二";
			break;
		case 3:
			week = " 星期三";
			break;
		case 4:
			week = " 星期四";
			break;
		case 5:
			week = " 星期五";
			break;
		case 6:
			week = " 星期六";
			break;
		default:
			week = "";
			break;
		}
		out += " " + week;
		return out;
	}

	/**
	 * 
	 * 函数名称：dateToString<br>
	 * 函数功能：根据日期参数 输出日期格式为:2008年12月22日<br>
	 * 例：DateUtil.dateToString(new Date()) 
	 * 输出：2008年12月22日
	 * @param date - Date
	 * @return String
	 */
	public static String dateToString(Date date) {
		String d = date.toLocaleString();
		String out = "";
		String xArr[] = d.split(" ");
		String yArr[] = xArr[0].split("-");
		out = yArr[0] + "年" + yArr[1] + "月" + yArr[2] + "日";
		return out;
	}
	/**
	 * 
	 * 函数名称：regularize<br>
	 * 函数功能：将日期格式为2008-1-2 替换为20080102<br>
	 * 例：DateUtil.regularize("2008-1-2") 输出：20080102
	 * 修改：DateUtil.regularize("2008-1") 输出：200801
	 * @param input - String
	 * @return String
	 */
	public static String regularize(String input) {
		/*String out = "";
		String[] arr = input.split("-");
		if (arr[1].length() < 2) {
			arr[1] = "0" + arr[1];
		}
		if (arr[2].length() < 2) {
			arr[2] = "0" + arr[2];
		}
		out = arr[0] + arr[1] + arr[2];
		return out;*/
		
	 
		String out = "";
		String[] arr = input.split("-");
		for(int i=0;i<arr.length;i++){
			if (arr[i].length() < 2) {
				out += "0" + arr[i];
			}else{
				out += arr[i];
			}
		}
		return out;
	}

	/**
	 * 
	 * 函数名称：yesterday <br>
	 * 函数功能：获取昨天日期 格式为：2008-12-21
	 * @return String
	 */
	public static String yesterday() {
		Date td = new Date();
		Date tmr = new Date(td.getTime() - 60 * 60 * 24 * 1000);
		String tomorrow = tmr.toLocaleString().split(" ")[0];
		String[] tArr = tomorrow.split("-");
		if (tArr[1].length() < 2)
			tArr[1] = "0" + tArr[1];
		if (tArr[2].length() < 2)
			tArr[2] = "0" + tArr[2];
		return tArr[0] + "-" + tArr[1] + "-" + tArr[2];
	}

	/**
	 * 
	 * 函数名称：tomorrow <br>
	 * 函数功能：获取明天日期 格式为：2008-12-23
	 * @return String
	 */
	public static String tomorrow() {
		Date td = new Date();
		Date tmr = new Date(td.getTime() + 60 * 60 * 24 * 1000);
		/*
		String tomorrow = tmr.toLocaleString().split(" ")[0];
		String[] tArr = tomorrow.split("-");
		if (tArr[1].length() < 2)
			tArr[1] = "0" + tArr[1];
		if (tArr[2].length() < 2)
			tArr[2] = "0" + tArr[2];
		return tArr[0] + "-" + tArr[1] + "-" + tArr[2];
		*/
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		return df.format(tmr);
	}

	/**
	 * 
	 * 函数名称：dateToDateCN <br>
	 * 函数功能：将日期格式为2008-12-1 转化为 2008年12月1日 <br>
	 * 例：DateUtil.dateToDateCN("2008-12-1") 输出：2008年12月1日
	 * @param in - String
	 * @return String
	 */
	public static String dateToDateCN(String in) {
		String[] tmp = in.split("-");
		return tmp[0] + "年" + tmp[1] + "月" + tmp[2] + "日";
	}

	/**
	 * 
	 * 函数名称：dateToDateEN <br>
	 * 函数功能：将日期格式为2008年12月1日 转化为 2008-12-1<br>
	 * 例：DateUtil.dateToDateCN("2008年12月1日") 输出：2008-12-1
	 * @param in - String
	 * @return String
	 */
	public static String dateToDateEN(String in) {
		return in.replace("年", "-").replace("月", "-").replace("日", "");
	}

	/**
	 * 程序中主要的日期分隔符为"-"和"/"，且日期序列为“年/月/日”型，其内容缺一不可 例如:09/02/02或2009-02-02
	 */
	public static final String DATE_SEPARATOR = "-/";

	/** 作日期分析之用 */
	static StringTokenizer sToken;

	/**
	 * 
	 * 函数名称：GregorianCalendar<br>
	 * 函数功能：将字符串格式的日期转换为Calender<br>
	 * 例：parse2Cal("2008-12-1") 
	 * @param pDateStr
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar parse2Cal(String pDateStr) {
		sToken = new StringTokenizer(pDateStr, DATE_SEPARATOR);
		int vYear = Integer.parseInt(sToken.nextToken());
		// GregorianCalendar的月份是从0开始算起的
		int vMonth = Integer.parseInt(sToken.nextToken()) - 1;
		int vDayOfMonth = Integer.parseInt(sToken.nextToken());
		return new GregorianCalendar(vYear, vMonth, vDayOfMonth);
	}

	/**
	 * 
	 * 函数名称：monthsBetween<br>
	 * 函数功能：给定两个时间相差的月数,String版<br>
	 * 例：DateUtil.monthsBetween("2008-12-1","2009-1-1")输出：1
	 * @param pFormerStr
	 * @param pLatterStr
	 * @return int
	 */
	public static int monthsBetween(String pFormerStr, String pLatterStr) {
		GregorianCalendar vFormer = DateUtil.parse2Cal(pFormerStr);
		GregorianCalendar vLatter = DateUtil.parse2Cal(pLatterStr);
		return monthsBetween(vFormer, vLatter);
	}

	/**
	 * 
	 * 函数名称：monthsBetween<br>
	 * 函数功能：给定两个时间相差的月数<br>
	 * 例：DateUtil.monthsBetween(parse2Cal("2008-12-1"),parse2Cal("2009-1-1"))输出：1
	 * @param pFormer GregorianCalendar
	 * @param pLatter GregorianCalendar
	 * @return int
	 */
	public static int monthsBetween(GregorianCalendar pFormer,
			GregorianCalendar pLatter) {
		GregorianCalendar vFormer = pFormer, vLatter = pLatter;
		boolean vPositive = true;
		if (pFormer.before(pLatter)) {
			vFormer = pFormer;
			vLatter = pLatter;
		} else {
			vFormer = pLatter;
			vLatter = pFormer;
			vPositive = false;
		}

		int vCounter = 0;
		while (vFormer.get(vFormer.YEAR) != vLatter.get(vLatter.YEAR)
				|| vFormer.get(vFormer.MONTH) != vLatter.get(vLatter.MONTH)) {
			vFormer.add(Calendar.MONTH, 1);
			vCounter++;
		}
		if (vPositive)
			return vCounter;
		else
			return -vCounter;
	}

	/**
	 * 
	 * 函数名称：getMonth<br>
	 * 函数功能：返回给定日期的月份<br>
	 * 例：DateUtil.getMonth("2008-12-2")输出：12
	 * @param pFormattedDate 格式为：2008-12-2
	 * @return int 结果：12
	 */
	public static int getMonth(String pFormattedDate) {
		StringTokenizer vSt = new StringTokenizer(pFormattedDate, "-");
		vSt.nextToken();//跳过年份
		int val = Integer.parseInt(vSt.nextToken());
		return val;
	}

	/**
	 * 
	 * 函数名称：getYear<br>
	 * 函数功能：返回给定日期的年份<br>
	 * 例：DateUtil.getYear("2008-12-2")输出：2008
	 * @param pFormattedDate 格式为：2008-12-2
	 * @return int 结果：2008
	 */
	public static int getYear(String pFormattedDate) {
		StringTokenizer vSt = new StringTokenizer(pFormattedDate, "-");
		int val = Integer.parseInt(vSt.nextToken());
		return val;
	}

	/**
	 * 
	 * 函数名称：dayOfWeek<br>
	 * 函数功能：返回给定日期的周数<br>
	 * 例：DateUtil.dayOfWeek("2008-12-2")输出：3
	 * @param pFormerStr 字符型 格式为：2008-12-2
	 * @return int 结果：3
	 */
	public static int dayOfWeek(String pFormerStr) {
		GregorianCalendar vTodayCal = parse2Cal(pFormerStr);
		return vTodayCal.get(vTodayCal.DAY_OF_WEEK);
	}

	/**
	 * 
	 * 函数名称：dayOfWeek<br>
	 * 函数功能：返回给定日期的周数<br>
	 * 例：DateUtil.dayOfWeek(new Date("2008-12-2"))输出：3
	 * @param date -日期型
	 * @return int 
	 */
	public static int dayOfWeek(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		return gc.get(gc.DAY_OF_WEEK);
	}

	/**
	 * 
	 * 函数名称：stringToDate<br>
	 * 函数功能：字符串转日期<br>
	 * 简要说明：注意：这里的pattern参数要与date参数格式相一致，如举例<br>
	 * 显示格式如：yyyy-MM-dd hh:mm:ss,
	 *           MM-dd-yyyy hh:mm:ss,
	 *           yy-MM-dd HH:mm,
	 *           yyyy-MM-dd,
	 *           yyyy/MM/dd,
	 *           MM/dd/yyyy,
	 *           yyyyMMdd等<br>
	 * 例：正确：DateUtil.stringToDate("2008-9-2","yy-MM-dd");<br>
	 *         DateUtil.stringToDate("2008-9-2 12:30","yy-MM-dd");<br>
	 *    错误：DateUtil.stringToDate("2008-9-2 12:30","yyyy/MM/dd");<br>
	 *         DateUtil.stringToDate("2008/9/2 12:30","yyyyMMdd HH:mm");
	 * @param date - String
	 * @param pattern
	 * @return Date
	 */
	public final static java.util.Date stringToDate(String date, String pattern)
			throws ParseException {
		if (date == null || pattern == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}

	/**
	 * 
	 * 函数名称：convDateToString<br>
	 * 函数功能：日期转字符串<br>
	 * 显示格式如：yyyy-MM-dd hh:mm:ss,
	 *           MM-dd-yyyy hh:mm:ss,
	 *           yy-MM-dd HH:mm,
	 *           yyyy-MM-dd,
	 *           yyyy/MM/dd,
	 *           MM/dd/yyyy,
	 *           yyyyMMdd等<br>
	 * 例：DateUtil.convDateToString(new Date(),"yy-MM-dd HH:mm")<br>
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public final static String convDateToString(Date date, String pattern) {
		if (date == null || pattern == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 
	 * 函数名称：calcMonday<br>
	 * 函数功能：根据日期计算这个星期的星期一是多少，并且星期一以00:00:00开头 <br>
	 * 例：DateUtil.calcMonday("2008-12-2")输出：2008-12-01 00:00:00<br>
	 * @param queryDate
	 * @return String
	 */
	public static String calcMonday(String queryDate) {
		String result = null;
		if (queryDate != null) {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				gc.setTime(df.parse(queryDate));
				gc.add(5, -1);
				while (gc.get(7) != 1) {
					gc.add(5, -1);
				}
				gc.add(5, 1);
				result = df.format(gc.getTime()) + " 00:00:00";
			} catch (ParseException e) {
				log.error(e.getStackTrace());
			}
		}
		return result;
	}

	/**
	 * 
	 * 函数名称：calcSunday<br>
	 * 函数功能：根据日期计算这个星期的星期日是多少，并且星期日以23:59:59开头<br>
	 * 例：DateUtil.calcSunday("2008-12-2")输出：2008-12-07 23:59:59
	 * @param queryDate
	 * @return String
	 */
	public static String calcSunday(String queryDate) {
		String result = null;
		if (queryDate != null) {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				gc.setTime(df.parse(queryDate));
				gc.add(5, -1);
				while (gc.get(7) != 7) {
					gc.add(5, 1);
				}
				gc.add(5, 1);
				result = df.format(gc.getTime()) + " 23:59:59";
			} catch (ParseException e) {
				log.error(e.getStackTrace());
			}
		}
		return result;
	}

	/**
	 * 
	 * 函数名称：calcBeginMonth<br>
	 * 函数功能：根据日期计算这个月的第一天<br>
	 * 例：DateUtil.calcBeginMonth("2008-12-2")输出：Mon Dec 01 00:00:00 GMT 2008
	 * @param queryDate
	 * @return Date
	 */
	public static Date calcBeginMonth(String queryDate) {
		Date result = null;
		if (queryDate != null) {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				gc.setTime(df.parse(queryDate));
				int i = gc.get(Calendar.MONTH);
				// 11表示第12月
				while (gc.get(Calendar.MONTH) != (i == 0 ? 11 : i - 1)) {
					gc.add(5, -1);
				}
				gc.add(5, 1);
				result = gc.getTime();
			} catch (ParseException e) {
				log.error(e.getStackTrace());
			}
		}
		return result;
	}

	/**
	 * 
	 * 函数名称：calcEndMonth<br>
	 * 函数功能：根据日期计算这个月的最后一天<br>
	 * 例：DateUtil.calcEndMonth("2008-12-2")输出：Wed Dec 31 00:00:00 GMT 2008
	 * @param queryDate
	 * @return Date
	 */
	public static Date calcEndMonth(String queryDate) {
		Date result = null;
		if (queryDate != null) {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				gc.setTime(df.parse(queryDate));
				int i = gc.get(Calendar.MONTH);
				// 11表示第12月
				while (gc.get(Calendar.MONTH) != (i == 11 ? 0 : i + 1)) {
					gc.add(5, 1);
				}
				gc.add(5, -1);
				result = gc.getTime();
			} catch (ParseException e) {
				log.error(e.getStackTrace());
			}
		}
		return result;
	}

	/**
	 * 
	 * 函数名称：getWeekString<br>
	 * 函数功能：根据数值给出中文的星期表示<br>
	 * 例：DateUtil.getWeekString(1)输出：星期日
	 * @param week
	 * @return String
	 */
	private static String getWeekString(int week) {
		String weeks[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return weeks[week - 1];
	}

	/**
	 * 
	 * 函数名称：isDateBefore<br>
	 * 函数功能：判断时间date1是否在时间date2之前<br>
	 * 使用说明：时间格式 2005-4-21 16:16:34<br>
	 * 例:DateUtil.isDateBefore("2008-12-2 16:16:34","2008-12-1 16:16:34")输出：false
	 * 
	 * @param date1 字符型
	 * @param date2 字符型
	 * @return boolean
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			return stringToDate(date1, "yyyy-MM-dd HH:mm:ss").before(
					stringToDate(date2, "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			log.error(e.getStackTrace());
			return false;
		}
	}

	/**
	 * 
	 * 函数名称：isDateBefore<br>
	 * 函数功能：判断时间date1是否在时间date2之前<br>
	 * 例:DateUtil.isDateBefore(DateUtil.getDate(),DateUtil.getDate())输出：false
	 * @param date1 日期型
	 * @param date2 日期型
	 * @return boolean 是否相等
	 */
	public static boolean isDateBefore(Date date1, Date date2) {
		try {
			return date1.before(date2);
		} catch (Exception e) {
			log.error(e.getStackTrace());
			return false;
		}
	}

	/**
	 * 
	 * 函数名称：getDateBefore<br>
	 * 函数功能：得到几天后的时间<br>
	 * 例:DateUtil.getDateBefore(DateUtil.getDate(),2)输出：Sat Feb 21 07:14:57 GMT 2009<br>
	 * DateUtil.getDate()为Thu Feb 19 07:14:57 GMT 2009
	 * @param d - 指定日期
	 * @param day - 指定天数
	 * @return Date 指定日期几天后的时间
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 
	 * 函数名称：getDateBeforeHour<br>
	 * 函数功能：指定日期小时后的时间<br>
	 * 例:DateUtil.getDateBeforeHour(DateUtil.getDate(),2)输出：Thu Feb 19 09:17:36 GMT 2009<br>
	 * DateUtil.getDate()为Thu Feb 19 07:17:36 GMT 2009
	 * @param d
	 * @param hour
	 * @return
	 * Date
	 */
	public static Date getDateBeforeHour(Date d, int hour) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
		return now.getTime();
	}

	/**
	 * 函数名称：getNow<br>
	 * 函数功能：获取当前时间 -年<br>
	 * 简要说明：
	 * @return String 
	 *
	 */
	public static String getYear() {
		Date date = Calendar.getInstance().getTime();
		String sDate = convDateToString(date, "yyyy");
		return sDate;
	}
	
	/**
	 * 函数名称：getMon<br>
	 * 函数功能：获取当前时间 -月<br>
	 * 简要说明：
	 * @return String
	 *
	 */
	public static String getMon() {
		Date date = Calendar.getInstance().getTime();
		String sDate = convDateToString(date, "MM");
		return sDate;
	}
	/**
	 * 函数名称：getDaily<br>
	 * 函数功能：获取当前时间 -日<br>
	 * 简要说明：
	 * @return String
	 *
	 */
	public static String getDaily() {
		Date date = Calendar.getInstance().getTime();
		String sDate = convDateToString(date, "dd");
		return sDate;
	}
	
	/**
	 * 函数名称：getNow<br>
	 * 函数功能：获取当前日期 格式为:2008-12-22<br>
	 * 简要说明：
	 * @return String
	 *
	 */
	public static String getNow() {
		Date date = Calendar.getInstance().getTime();
		String sDate = convDateToString(date, "yyyy-MM-dd");
		return sDate;
	}
	
	/**
	 * 函数名称：getNowTime<br>
	 * 函数功能：获取当前日期 格式为:2008-12-22 00:00:00<br>
	 * 简要说明：
	 * @return String
	 *
	 */
	public static String getNowTime() {
		Date date = Calendar.getInstance().getTime();
		String sDate = convDateToString(date, "yyyy-MM-dd HH:mm:ss");
		return sDate;
	}
	
	/**
	 * 函数名称：getDate<br>
	 * 函数功能：获取当前日期 格式为:Thu Feb 19 02:01:37 GMT 2009<br>
	 * 简要说明：
	 * @return Date
	 *
	 */
	public static Date getDate() {
		Date date = Calendar.getInstance().getTime();
		return date;
	}

	public static void main(String[] args) throws ParseException {
	 System.out.println(stringToDate("2008-12-12 06:30","yy-MM-dd"));
	 System.out.println(tomorrow());
}
}
