package com.linkage.rainbow.util;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asiainfo.foundation.util.RandomUtils;

/*************************************************
MathUtil类主要是对字符串类型数据进行处理，
如：对数据类型进行转换
*************************************************/
public class StringUtil {
	private static Log log = LogFactory.getLog(StringUtil.class);
	/**
	 * 判断指定的Class是否简单类型，简单类型是指Java中的基本类型<br>
	 * 例：
	 *isSimpleType(int.class)输出：true
	 * @param clz
	 * @return boolean
	 */
	private static boolean isSimpleType(Class clz) {
		if (clz.equals(int.class))
			return true;
		if (clz.equals(Integer.class))
			return true;
		if (clz.equals(long.class))
			return true;
		if (clz.equals(Long.class))
			return true;
		if (clz.equals(byte.class))
			return true;
		if (clz.equals(Byte.class))
			return true;
		if (clz.equals(short.class))
			return true;
		if (clz.equals(Short.class))
			return true;
		if (clz.equals(boolean.class))
			return true;
		if (clz.equals(Boolean.class))
			return true;
		if (clz.equals(float.class))
			return true;
		if (clz.equals(Float.class))
			return true;
		if (clz.equals(double.class))
			return true;
		if (clz.equals(Double.class))
			return true;
		if (clz.equals(String.class))
			return true;

		return false;
	}

	/**
	 * 判断是否忽略类型<br>
	 * 例：isIgnoreType(int.class)输出：false
	 * @param clz
	 * @return boolean
	 */
	private static boolean isIgnoreType(Class clz) {
		if (clz.equals(Class.class))
			return true;

		return false;
	}
	/**
	 * char类型转字符串类型<br>
	 * 例：StringUtil.valueOf('a')
	 * @param c
	 * @return String
	 */
	public static String valueOf(char c) {
		char ac[] = new char[1];
		ac[0] = c;
		return new String(ac);
	}
	/**
	 * double类型转成字符串类型
	 * @param d
	 * @return String
	 */
	public static String valueOf(double d) {
		return Double.toString(d);
	}
	/**
	 * float类型转成字符串类型
	 * @param f
	 * @return String
	 */
	public static String valueOf(float f) {
		return Float.toString(f);
	}
	/**
	 * int类型转成字符串类型
	 * @param i
	 * @return String
	 */
	public static String valueOf(int i) {
		char ac[] = new char[11];
		int j = ac.length;
		boolean flag = i < 0;
		if (!flag)
			i = -i;
		for (; i <= -10; i /= 10)
			ac[--j] = Character.forDigit(-(i % 10), 10);

		ac[--j] = Character.forDigit(-i, 10);
		if (flag)
			ac[--j] = '-';
		return new String(ac, j, ac.length - j);
	}
	/**
	 * long类型转成字符串类型
	 * @param l
	 * @return String
	 */
	public static String valueOf(long l) {
		return Long.toString(l, 10);
	}
	
	
	/**
	 * 类去掉字符串首尾空格,当传入null或No data时返回"".<br>
	 * 例：String a="123 ";<br>
	 *    System.out.println(StringUtil.valueOf(a)); 输出：123
	 * @param value
	 * @return String
	 */
	public static String valueOf(String value) {
		if (value != null && !(value.equals("")) && !(value.equals("No data")))
			return value;
		else
			return "";
	}
	
	/**
	 * 对像转成字符串,当传入null返回"".<br>
	 * @param obj
	 * @return String
	 */
	public static String valueOf(Object obj) {
		return obj != null ? obj.toString() : "";
	}
	/**
	 * boolean类型转字符串类型
	 * @param flag
	 * @return String
	 */
	public static String valueOf(boolean flag) {
		return flag ? "true" : "false";
	}
	/**
	 * 字符数组转成字符串
	 * @param ac
	 * @return String
	 */
	public static String valueOf(char ac[]) {
		return new String(ac);
	}

	/**
	 * 数组转成字符串<br>
	 * 例：String aa[]=new String[]{"123","124","125"};<br>
	 *	  System.out.println(StingUtil.valueOf(aa));<br>
	 *    输出：123,124,125
	 * @param array
	 * @return String
	 */
	public static String valueOf(Object array[]) {
		StringBuffer sb = new StringBuffer();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if(i > 0)
					sb.append(",");
				sb.append(StringUtil.valueOf(array[i]));
			}
		}
		return sb.toString();
	}
	/**
	 * 函数名称：beanToString<br>
	 * 转换规则：<br>
	 * {class=类名, 属性名称1=属性值1, 属性名称2=属性值2 ... }
	 * @param bean
	 * @return String
	 */
	public static String beanToString(Object bean) throws Exception {
		if (bean == null)
			return "";

		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(bean);
		StringBuffer sb = new StringBuffer();
		Class clz = bean.getClass();

		if (isSimpleType(clz)) {
			sb.append(String.valueOf(bean));
		} else if (!isIgnoreType(clz)) {
			sb.append("{");
			sb.append("Class=");
			sb.append(clz.getName());
			for (int i = 0; i < descriptors.length; i++) {
				PropertyDescriptor des = descriptors[i];
				if (PropertyUtils.isReadable(bean, des.getName())) {
					sb.append(", ");
					sb.append(des.getName());
					sb.append("=");
					Object proValue = PropertyUtils.getSimpleProperty(bean, des.getName());
					if (isSimpleType(des.getPropertyType())) {
						sb.append(String.valueOf(proValue));
					} else if (proValue instanceof Map) {
						sb.append(mapToString((Map) proValue));
					} else if (proValue instanceof Collection) {
						sb.append(collectionToString((Collection) proValue));
					} else if (proValue instanceof Object[]) {
						sb.append(arrayToString((Object[]) proValue));
					} else {
						sb.append(beanToString(proValue));
					}
				}
			}
			sb.append("}");
		}

		return sb.toString();
	}

	/**
	 * 转换成UTF8编码<br>
	 * 例：String a="转换成UTF8编码";<br>
	 *    StringUtil.toUtf8String(a);<br> 输出：%E8%BD%AC%E6%8D%A2%E6%88%90UTF8%E7%BC%96%E7%A0%81
	 * @param s
	 * @return String
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= '\377') {
				sb.append(c);
			} else {
				byte b[];
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}

		return sb.toString();
	}
	
	/**
	 * 转换成可以标题导出
	 */
	 public static String titleToUtf8String(String s){
         StringBuffer sb  =   new  StringBuffer();
          for  ( int  i = 0 ;i < s.length();i ++ ){
              char  c  =  s.charAt(i);
             if  (c  >=   0   &&  c  <=   255 ){sb.append(c);}
             else {
                 byte [] b;
                 try  { b  =  Character.toString(c).getBytes("utf-8");}
                 catch  (Exception ex) {
                    //System.out.println(ex);
                    b  =   new   byte [ 0 ];
                }
                 for  ( int  j  =   0 ; j  <  b.length; j ++ ) {
                     int  k  =  b[j];
                     if  (k  <   0 ) k  +=   256 ;
                    sb.append( "%"   +  Integer.toHexString(k).toUpperCase());
                }
            }
        }
         return  sb.toString();
    } 
	/**
	 * 匹配pattern，使用正则表达式 <br>(注：str参数只要满足数组patterns元素中一个,则返回true.)<br>
	 *  例：String regex[] =new String[]{"/^[0-9]+$/","^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"};	<br>
	 *     //regex[0]是判断只能为数字的正则表达式，regex[1]是判断符合邮箱格式的正则表达式<br>
	 *	   System.out.println(StringUtil.matchPatter("xinxin@163.com",regex));
	 *     输出：true
	 * @param str
	 * @param patterns
	 * @return boolean
	 */
	public static boolean matchPatter(String str, String[] patterns) {
		if (str == null || patterns == null)
			return false;
		for (int i = 0; i < patterns.length; i++) {
			if (patterns[i] != null && Pattern.matches(patterns[i], str))
				return true;
		}
		return false;
	}

	/**
	 * 将一个字符串转换成一个Unicode字符串<br>
	 *  例：String a="Unicode字符串";<br>
	 *    StringUtil.toUnicode(a); 输出：Unicode\u5b57\u7b26\u4e32
	 * @param strText
	 * @return String
	 * @throws Exception
	 */
	public static String toUnicode(String strText) throws Exception {
		char c;
		String strRet = "";
		int intAsc;
		String strHex;

		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			if (intAsc > 128) {
				strHex = Integer.toHexString(intAsc);
				strRet = strRet + "\\u" + strHex;
			} else {
				strRet = strRet + c;
			}
		}
		return strRet;
	}
	
	/**
	 * 将一个数组转换成一个String<br>
	 * 转换规则：<br>
	 * {数组元素1,数组元素2,数组元素3...}<br>
	 *  例：String[] temp=new String[]{"123","124"}; <br>
	 *     System.out.println( StringUtil.arrayToString(temp));输出：{123,124}
	 * @param array
	 * @return String
	 */
	public static String arrayToString(Object[] array) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if(i > 0)
					sb.append(", ");
				sb.append(beanToString(array[i]));
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 将一个Collection转换成一个String<br>
	 * 转换规则：<br>
	 * {Collection元素1,Collection元素2,Collection元素3...}<br>
	 * 例：  List list=new ArrayList();<br>
	 *	    list.add("aaa");<br>
	 *	    list.add("bbb");<br>
	 *      System.out.println( StringUtil.collectionToString(list));输出：{aaa, bbb}
	 * @param collection
	 * @return String
	 */
	public static String collectionToString(Collection collection) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (collection != null) {
			int index = 0;
			for (Iterator iter = collection.iterator(); iter.hasNext();) {
				if(index > 0)
					sb.append(", ");
				sb.append(beanToString(iter.next()));
				
				index++;
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 将一个Map转换成一个String<br>
	 * 转换规则：<br>
	 *  {key=value ...}<br>
	 * 例：<br>
	 * 	Map tmp = new HashMap();<br>
	 *	tmp.put("hello", new String("kinz"));<br>
	 *	tmp.put("you", "are");<br>
	 *  System.out.println( StringUtil.mapToString(tmp)); 输出：{you=are, hello=kinz}
	 * @param map
	 * @return String
	 */
	public static String mapToString(Map map) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		if (map != null) {
			int index = 0;
			for (Iterator it = map.keySet().iterator(); it.hasNext();) {
				Object key = it.next();
				if (index > 0)
					sb.append(", ");
				sb.append(beanToString(key));
				sb.append("=");
				sb.append(beanToString(map.get(key)));

				index++;
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 
	 * 函数名称：matcherRegex<br>
	 * 函数功能：检查字符串str是否匹配正则表达式regex<br>
	 * 例：String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";<br>		
	 *    StringUtil.matcherRegex(regex,"xinxin@163.com")<br>
	 *    输出：true
	 * @param regex - 正则表达式
	 * @param str - 要检查的字符串
	 * @return boolean
	 */
	public static boolean matcherRegex(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 
	 * 函数名称：checkEmail<br>
	 * 函数功能：检查电子邮件是否正确<br>
	 * 例： StringUtil.checkEmail("xinxin@163.com") <br>
	 * @param email - 电子邮件地址<br>
	 * @return boolean 邮箱正确返回true，否则返回false
	 */
	public static boolean checkEmail(String email) {
		String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return matcherRegex(regex, email);
	}
	
	/**
	 * 
	  * 函数名称：isBlank<br>
	  * 函数功能：检查字符串str去掉首尾空格是否为空值(null也为空值)<br>
	  * 例： StringUtil.isBlank("   abc ") 输出：false<br>
	  * @param str - 要检查的字符串<br>
	  * @return boolean str为空返回true，否则返回false
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}
	
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
	
	/**
	 * 
	  * 函数名称：isDouble<br>
	  * 函数功能：检查字符串str是否为double类型<br>
	  * 例： StringUtil.isDouble("abc") 输出：false<br>
	  * @param str -检查字符串
	  * @return boolean
	 */
	public static boolean isDouble(String str) {
		if (isBlank(str))
			return false;
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 
	  * 函数名称：isInteger<br>
	  * 函数功能：检查字符串str是否为整型<br>
	  * 例： StringUtil.isInteger("abc") 输出：false<br>
	  * @param str -  要检查的字符串
	  * @return boolean str为整型返回true，否则返回false
	 */
	public static boolean isInteger(String str) {
		if (isBlank(str))
			return false;
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 
	  * 函数名称：isLong<br>
	  * 函数功能：检查字符串str是否为长整型<br>
	  * 例： StringUtil.isLong("123456") 输出：true<br>
	  * @param str - 要检查的字符串
	  * @return boolean str为布尔型返回true，否则返回false
	 */
	public static boolean isLong(String str) {
		if (isBlank(str))
			return false;
		try {
			Long.parseLong(str);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 
	  * 函数名称：isTimestamp<br>
	  * 函数功能：检查字符串str是否为时间<br>
	  * 例： StringUtil.isTimestamp("2008-12-23") 输出：true
	  * @param str - 要检查的字符串
	  * @return boolean str为时间型返回true，否则返回false
	 */
	public static boolean isTimestamp(String str) {
		if (isBlank(str))
			return false;
		try {
			java.sql.Date d = java.sql.Date.valueOf(str);
			return true;
		} catch (Exception ex) {
		}
		return false;
	}

	/**
	 * 
	  * 函数名称：isFullTimestamp<br>
	  * 函数功能：检查字符串str是否为(yyyy-MM-dd HH:mm:ss)模式的时间<br>
	  * 例： StringUtil.isFullTimestamp("2008-12-23") 输出：false
	  * @param str - 要检查的字符串
	  * @return boolean str为时间型返回true，否则返回false
	 */
	public static boolean isFullTimestamp(String str) {
		if (isBlank(str))
			return false;
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(str);
			return date != null;
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * 
	  * 函数名称：decodeURIFromISO88591<br>
	  * 函数功能：是将取出ISO-8859-1（UNICODE）转换为GB2312编码(可用于ajax传递的参数解码[post])<br>
	  * 例： StringUtil.decodeURIFromISO88591("GB2312编码"); 
	  * @param str - 要解码的字符串
	  * @return String 解码后的字符串
	 */
	public static String decodeURIFromISO88591(String str) {
		if (isBlank(str))
			return "";
		try {
			return new String(str.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}
	
	/**
	 * 
	  * 函数名称：isLongs<br>
	  * 函数功能：检查字符串数组str是否为长整型数组<br>
	  * 例：String[] temp=new String[]{"123","124"}; <br>
	  *    StringUtil.System.out.println( StringUtil.isLongs(temp));输出：true<br>
	  * @param str - 要检查的字符串
	  * @return boolean str为长整型数组返回true，否则返回false
	 */
	public static boolean isLongs(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Long.parseLong(str[i]);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
 
	/**
	 * 
	  * 函数名称：isIntegers<br>
	  * 函数功能：检查字符串数组str是否为整型数组<br>
	  *  例：String[] temp=new String[]{"123","124"};<br> 
	  *      System.out.println( isIntegers(temp));输出：true
	  * @param str - 要检查的字符串
	  * @return boolean str为整型数组返回true，否则返回false
	 */
	public static boolean isIntegers(String str[]) {
		try {
			for (int i = 0; i < str.length; i++)
				Integer.parseInt(str[i]);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
 
	/**
	 * 
	  * 函数名称：Md5<br>
	  * 函数功能：MD5加密字符串<br>
	  * 例： StringUtil.Md5("0000"); 输出：4a7d1ed414474e4033ac29ccb8653d9b
	  * @param str - 要加密的字符串
	  * @return String 加密后的字符串
	 */
	public static String Md5(String str) {
		StringBuffer result = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					result.append("0");
				result.append(Integer.toHexString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 
	  * 函数名称：stringToLongs<br>
	  * 函数功能：将字符数组转换为长整型数组<br>
	  *  例：String[] temp=new String[]{"123","124"}; <br>
	  *      System.out.println(StringUtil.stringToLongs(temp));输出：长整型数组
	  * @param str - 字符数组
	  * @return Long[] 长整型数组
	 */ 
	public static Long[] stringToLongs(String str[]) {
		Long lon[] = new Long[str.length];
		for (int i = 0; i < lon.length; i++)
			lon[i] = new Long(str[i]);
		return lon;
	}
 
	/**
	 * 
	  * 函数名称：stringToIntegers<br>
	  * 函数功能：将字符数组转换为整型数组<br>
	  *   例：String[] temp=new String[]{"123","124"}; <br>
	  *      System.out.println( StringUtil.stringToIntegers(temp));输出：整型数组
	  * @param str - 字符数组
	  * @return Integer[] 整型数组
	 */
	public static Integer[] stringToIntegers(String str[]) {
		Integer array[] = new Integer[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = new Integer(str[i]);
		return array;
	}

	/**
	 * 
	  * 函数名称：stringToDoubles<br>
	  * 函数功能：将字符数组转换为浮点型数组<br>
	  * 例：String[] temp=new String[]{"123.12","124.13"}; <br>
	  *    System.out.println( StringUtil.stringToDoubles(temp));输出：浮点型数组
	  * @param str - 字符数组
	  * @return double[] 浮点型数组
	 */
	public static double[] stringToDoubles(String str[]) {
		double array[] = new double[str.length];
		for (int i = 0; i < array.length; i++)
			array[i] = Double.parseDouble(str[i]);
		return array;
	}

	/**
	 * 
	  * 函数名称：getOnlyStringByTime<br>
	  * 函数功能：通过时间得到唯一字符串（全为数字）<br>
	  * 例：System.out.println(StringUtil.getOnlyStringByTime(); 
	  * @return String
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getOnlyStringByTime() throws NoSuchAlgorithmException, NoSuchProviderException {
		// 加上随机数，使1ms内的重复机会更小
		return System.currentTimeMillis() + "" + RandomUtils.nextInt();
	}

	/**
	 * 
	  * 函数名称：decodeHTML<br>
	  * 函数功能：解码HTML(将&gt;,&lt;,&quot;,&amp;转换成>,<,",& )<br>
	  *  例：String temp="&lt;input type=text name='test' value='123' /&gt;"; <br>
	  *    System.out.println( StringUtil.decodeHTML(temp));<br>
	  *    输出：<input type=text name='test' value='123' />
	  * @param s - 解码字符串
	  * @return String
	 */
	public static String decodeHTML(String s) {
		if (isBlank(s))
			return "";
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&quot;", "\"");
		return s;
	}

	/**
	 * 
	 * 函数名称： encodeHTML<br>
	 * 函数功能：编码HTML(将>,<,",&转换成&gt;,&lt;,&quot;,&amp;)
	 * (高效率，来自FreeMarker模板源码，比replaceAll速度快很多) <br>
	 * 例：String temp="<input type=text name='test' value='123' />";<br> 
	 *    System.out.println( StringUtil.decodeHTML(temp));<br>
	 *    输出：&lt;input type=text name='test' value='123' /&gt;
	 * @param s
	 * @return String
	 */
	public static String encodeHTML(String s) {
		if (isBlank(s))
			return "";
		int ln = s.length();
		char c;
		StringBuffer b;
		for (int i = 0; i < ln; i++) {
			c = s.charAt(i);
			if (c == '<' || c == '>' || c == '&' || c == '"') {
				b = new StringBuffer(s.substring(0, i));
				switch (c) {
				case '<':
					b.append("&lt;");
					break;
				case '>':
					b.append("&gt;");
					break;
				case '&':
					b.append("&amp;");
					break;
				case '"':
					b.append("&quot;");
					break;
				}
				i++;
				int next = i;
				while (i < ln) {
					c = s.charAt(i);
					if (c == '<' || c == '>' || c == '&' || c == '"') {
						b.append(s.substring(next, i));
						switch (c) {
						case '<':
							b.append("&lt;");
							break;
						case '>':
							b.append("&gt;");
							break;
						case '&':
							b.append("&amp;");
							break;
						case '"':
							b.append("&quot;");
							break;
						}
						next = i + 1;
					}
					i++;
				}
				if (next < ln)
					b.append(s.substring(next));
				s = b.toString();
				break;
			}
		}
		return s;
	}
 
	/**
	 * 
	  * 函数名称：getIpAddr<br>
	  * 函数功能：获取客户端IP
	  * @param request
	  * @return String 客户端IP
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
 
	/**
	 * 
	  * 函数名称：getRamCode<br>
	  * 函数功能：得到多少位数的随机函数<br>
	  * 例: System.out.println(StringUtil.getRamCode(9));
	  * @param f_length - 指定长度
	  * @return String
	 */
	public static String getRamCode(int f_length){		
		String f_Randchar="0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
		String f_Randomizecode="";
		int f_Random;
		int f_RandLen;
		String[] f_Randchararr=f_Randchar.split(",");
		f_RandLen=f_length; //定义密码的长度或者是位数
		for(int i=1;i<=f_RandLen;i++)
		{	
			f_Random= (int) (RandomUtils.nextInt() * 21);
			f_Randomizecode=f_Randomizecode + f_Randchararr[f_Random];
		}	
		return f_Randomizecode;
	}

	/**
	 * 
	  * 函数名称：getRandListByLength<br>
	  * 函数功能：在指定范围内生成N个随机数<br>
	  * 例: System.out.println(StringUtil.getRandListByLength(9,9));
	  * @param f_RandNum --产生个数
	  * @param f_length --随机数范围
	  * @return String
	 */
	public static String getRandListByLength(int f_RandNum,int f_length){
		if (f_RandNum==0 || f_length==0) return null;
		String f_Randomizecode="";
		String f_Random;
		for(int i=1;i <= f_RandNum; ){
			f_Random = Integer.toString(RandomUtils.nextInt(f_length) + 1);   //产生随机数
			if(f_Randomizecode.indexOf(f_Random)==-1){                 //存在则另取一个，不存在则加入字符串
			 f_Randomizecode += f_Random;
			 if(i!=f_RandNum) f_Randomizecode +=",";
			 i++;
			}
		} 
		return f_Randomizecode;
	}

	/**
	 * 
	  * 函数名称：getRandListByArr<br>
	  * 函数功能：在指定数组中内生成N个随机数<br>
	  * 例: List list=new ArrayList();<br>
	  *     list.add("aaa");<br>
	  *     list.add("bbb");<br>
	  *	    list.add("123");<br>
	  *     System.out.println(StringUtil.getRandListByArr(list,2));<br>
	  *     输出如：123,bbb
	  * @param f_Randchar --指定的数组
	  * @param f_RandNum  --产生个数
	  * @return String
	 */
	public static String getRandListByArr(List f_Randchar,int f_RandNum){
		int f_length = f_Randchar.size();
		if (f_length==0 || f_RandNum==0) return null;
		String f_Randomizecode=",";
		String f_Random;
		if(f_length<f_RandNum) f_RandNum = f_length;
		for(int i=1;i <= f_RandNum; ){
			f_Random = ""+f_Randchar.get(RandomUtils.nextInt(f_length));   //产生随机数
			if(f_Randomizecode.indexOf(","+f_Random+",")==-1){                 //存在则另取一个，不存在则加入字符串
			 f_Randomizecode += f_Random ;
			 if(i!=f_RandNum) f_Randomizecode +=",";
			 i++;
			}
		} 
		return f_Randomizecode.substring(1);
	}
	
	/**
	 * 
	  * 函数名称：showFormat<br>
	  * 函数功能：格式化输出字符串<br>
	  * 例：System.out.println(StringUtil.showFormat("是,否","1,0",1));<br>
	  *    输出：是
	  * @param str
	  * @param value
	  * @param sel
	  * @return String
	 */
	public static String showFormat(String str,String value,Object sel){
		String result="";
		String[] strArr = str.split(",");
		String[] valueArr = value.split(",");
		if(strArr.length!=valueArr.length){
			return result;
		}
		for(int i=0;i<valueArr.length;i++){
			String selStr = StringUtil.valueOf(sel);
			if (selStr != null && !selStr.equals("") && !selStr.equalsIgnoreCase("null")) {
				if (selStr.equals(valueArr[i])) {
					result += strArr[i];
				}
			}
		}
		return result;
	}
	
	/**
	 * 将字符串转换为Base64编码
	 * @param sURL
	 * @return
	 */
	public static String toBASE64String(String sURL) {
		try {
			byte[] aUrl = sURL.getBytes();
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			String url = encoder.encode(aUrl);

			return url;
		} catch (Exception ex) {
		}

		return null;
	}

	/**
	 * 将Base64编码字符串的解码.
	 */
	public static String fromBASE64String(String sURL) {
		try {
			sun.misc.BASE64Decoder encoder = new sun.misc.BASE64Decoder();
			byte[] aUrl = encoder.decodeBuffer(sURL);

			return new String(aUrl);
		} catch (Exception ex) {
		}

		return null;
	}

	/**  
     * 删除input字符串中的html格式  
     *   
     * @param input  
     * @param length  
     * @return  
     */  
    public static String splitAndFilterString(String input, int length) {   
        if (input == null || input.trim().equals("")) {   
            return "";   
        }   
        // 去掉所有html元素,   
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(   
                "<[^>]*>", "");   
        str = str.replaceAll("[(/>)<]", "");   
        int len = str.length();   
        if (len <= length) {   
            return str;   
        } else {   
            str = str.substring(0, length);   
            str += "......";   
        }   
        return str;   
    }  
    
    /**
     * 将字符串转为javascript变量时的转义
     * @param s 字符串
     * @return javascript变量时的转义
     */
    public static String javaScriptStringEnc(String s)
    {
        int ln = s.length();
        for(int i = 0; i < ln; i++)
        {
            char c = s.charAt(i);
            if(c != '"' && c != '\'' && c != '\\' && c != '>' && c >= ' ')
                continue;
            StringBuffer b = new StringBuffer(ln + 4);
            b.append(s.substring(0, i));
            do
            {
                if(c == '"')
                    b.append("\\\"");
                else
                if(c == '\'')
                    b.append("\\'");
                else
                if(c == '\\')
                    b.append("\\\\");
                else
                if(c == '>')
                    b.append("\\>");
                else
                if(c < ' ')
                {
                    if(c == '\n')
                        b.append("\\n");
                    else
                    if(c == '\r')
                        b.append("\\r");
                    else
                    if(c == '\f')
                        b.append("\\f");
                    else
                    if(c == '\b')
                        b.append("\\b");
                    else
                    if(c == '\t')
                    {
                        b.append("\\t");
                    } else
                    {
                        b.append("\\x");
                        int x = c / 16;
                        b.append((char)(x >= 10 ? (x - 10) + 65 : x + 48));
                        x = c & 0xf;
                        b.append((char)(x >= 10 ? (x - 10) + 65 : x + 48));
                    }
                } else
                {
                    b.append(c);
                }
                if(++i >= ln)
                    return b.toString();
                c = s.charAt(i);
            } while(true);
        }

        return s;
    }

	/**
	 * 得到随机的信息资源
	 * 
	 * @param lngMax
	 * @return
	 * @throws Exception
	 */
	public static String makeAwardNo(long lngMax){
		 
		String sNo = String.valueOf(Math.abs(RandomUtils.nextLong() % lngMax));
		return sNo;
	}

    
	
	/**
	 * char字符转byte
	 * @param paramChar
	 * @return
	 */
	private static byte charToByte(char paramChar){
		return (byte)"0123456789ABCDEF".indexOf(paramChar);
	}
	
	/**
	 * InteHEX转Byte
	 * @param paramString
	 * @return
	 */
	public static byte[] hexStringToBytes(String paramString){
		
		
	    if ((paramString == null) || (paramString.equals(""))) {
	    	
	    	return null;
	    }
	    paramString = paramString.toUpperCase();
	    int i = paramString.length() / 2;
	    char[] arrayOfChar = paramString.toCharArray();
	    byte[] arrayOfByte = new byte[i];
	    for (int j = 0; j < i; j++) {
	    	int k = j * 2;
	    	arrayOfByte[j] = (byte)(charToByte(arrayOfChar[k]) << 4 | charToByte(arrayOfChar[(k + 1)]));
	    }
	    return arrayOfByte;
	}
	/**
	 * 3des加密
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String threeDES(String str, String key)
	    throws Exception{
		
	    byte[] arrayOfByte1 = str.getBytes();

	    Mac localMac = Mac.getInstance("HmacMD5");
	    byte[] arrayOfByte2 = StringUtil.hexStringToBytes(key);
	    SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte2, "DESede");
	    localMac.init(localSecretKeySpec);
	    byte[] arrayOfByte3 = localMac.doFinal(arrayOfByte1);
	    return StringUtil.bytesToHexString(arrayOfByte3);
	}
	
	/**
	 * byte转hex
	 * @param paramArrayOfByte
	 * @return
	 */
	public static String bytesToHexString(byte[] paramArrayOfByte){
		
		StringBuilder localStringBuilder = new StringBuilder("");
	    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
	    	return null;
	    }
	    for (int i = 0; i < paramArrayOfByte.length; i++) {
	    	
		    int j = paramArrayOfByte[i] & 0xFF;
		    String str = Integer.toHexString(j);
		    if (str.length() < 2) {
		    	localStringBuilder.append(0);
		    }
		    localStringBuilder.append(str);
	    }
	    return localStringBuilder.toString();
	}
}


