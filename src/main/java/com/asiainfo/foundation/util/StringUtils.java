package com.asiainfo.foundation.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

public abstract class StringUtils extends org.springframework.util.StringUtils
{
	private static final Log logger = LogFactory.getLog(StringUtils.class);

	public static boolean isNullOrEmpty(String string) {
		return (string == null) || (string.trim().length() == 0);
	}
	@Deprecated
	public static boolean isEmptyString(String str) {
		return isNullOrEmpty(str);
	}

	public static boolean isNotEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	public static String getBlank(int blankNum) {
		StringBuffer blanks = new StringBuffer();
		for (int i = 0; i < blankNum; i++) {
			blanks.append(" ");
		}
		return blanks.toString();
	}

	public static boolean containInArray(String target, String[] list) {
		for (String dest : list) {
			if (dest.equals(target)) {
				return true;
			}
		}
		return false;
	}

	public static String join(String[] strs)
	{
		return org.apache.commons.lang.StringUtils.join(strs);
	}

	public static String truncateMessage(String description, int length) {
		Assert.state(length > 0);
		if ((description != null) && (description.length() > length)) {
			logger.debug("Truncating long message, original message is: " + description);
			return description.substring(0, length);
		}

		return description;
	}

	public static String dateToStr(Date date, String pattern)
	{
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (pattern != null)
			formater.applyPattern(pattern);
		return formater.format(date);
	}

	public static Map<String, String> splitPara(String paraSrc, String sepa)
	{
		if ((paraSrc == null) || (paraSrc.trim().length() == 0)) {
			return null;
		}

		LinkedHashMap paraMap = new LinkedHashMap();
		if ((sepa == null) || (sepa.equals(""))) {
			sepa = ",";
		}

		String[] paras = paraSrc.split(sepa);
		int i = 0; for (int j = 0; i < paras.length; i++) {
			String[] tmpResult = paras[i].split("=");
			if (tmpResult.length >= 2) {
				paraMap.put(tmpResult[0].trim(), tmpResult[1]);
			}
			else if (tmpResult.length == 1) {
				if (paras[i].indexOf("=") >= 0) {
					paraMap.put(tmpResult[0].trim(), "");
				}
				else {
					paraMap.put("TEXT." + j, paras[i]);
					j++;
				}
			}
		}

		return paraMap;
	}

	public static String pathname(String name, String split)
	{
		if ((name == null) || (name.equals(""))) {
			return "";
		}
		if ((split == null) || (split.equals(""))) {
			split = ".";
		}

		int index = name.lastIndexOf(split);
		if (index >= 0) {
			return name.substring(0, index);
		}

		return name;
	}

	public static String basename(String name, String split)
	{
		if ((name == null) || (name.equals(""))) {
			return "";
		}
		if ((split == null) || (split.equals(""))) {
			split = ".";
		}

		int index = name.lastIndexOf(split);
		if (index >= 0) {
			return name.substring(index + split.length());
		}

		return "";
	}

	public static String replaceAll(String src, String pattern, String to)
	{
		if (src == null) {
			return null;
		}
		if (pattern == null) {
			return src;
		}

		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(src);

		int i = 1;
		while (m.find())
		{
			m.appendReplacement(sb, to);
			i++;
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static String[] findAll(String src, String pattern)
	{
		return findAll(src, pattern, 0);
	}

	public static String[] findAll(String src, String pattern, int flags)
	{
		if (src == null) {
			return new String[0];
		}
		if (pattern == null) {
			return new String[0];
		}

		Pattern p = Pattern.compile(pattern, flags);
		Matcher m = p.matcher(src);
		Collection l = new ArrayList();
		while (m.find()) {
			l.add(m.group());
		}

		return (String[])l.toArray(new String[0]);
	}

	public static boolean match(String src, String regexp)
	{
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(src);
		return m.find();
	}

	public static Locale toLocale(String locale)
	{
		if (hasText(locale)) {
			String[] values = (String[])null;
			if (locale.indexOf("_") > 0) {
				values = split(locale, "_");
			}
			else if (locale.indexOf("-") > 0) {
				values = split(locale, "-");
			}
			else {
				values = new String[0];
			}
			if (values.length == 1) {
				return new Locale(values[0]);
			}
			if (values.length == 2) {
				return new Locale(values[0], values[1]);
			}
			if (values.length == 3) {
				return new Locale(values[0], values[1], values[2]);
			}
		}
		return null;
	}

	public static byte[] getUtf8Bytes(String str)
	{
		try
		{
			return str.getBytes("UTF-8"); } catch (UnsupportedEncodingException uee) {
			}
		return str.getBytes();
	}

	public static String getStringFromUtf8(byte[] utf8)
	{
		try
		{
			return new String(utf8, "UTF-8"); } catch (UnsupportedEncodingException uee) {
			}
		return new String(utf8);
	}

	public static String toString(List<?> list)
	{
		if (list == null) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer(256);
		for (Iterator iter = list.iterator(); iter.hasNext(); ) {
			stringBuffer.append((String)iter.next());
			if (iter.hasNext()) {
				stringBuffer.append(",");
			}
		}
		return stringBuffer.toString();
	}

	public static String toString(Object[] objs)
	{
		if ((objs == null) || (objs.length == 0)) {
			return "";
		}
		return toString(Arrays.asList(objs));
	}

	@Deprecated
	public static String str_replace(String con, String tag, String rep)
	{
		int j = 0;
		int i = 0;
		String RETU = "";
		String temp = con;
		int tagc = tag.length();
		while (i < con.length()) {
			if (con.substring(i).startsWith(tag)) {
				temp = con.substring(j, i) + rep;
				RETU = RETU + temp;
				i += tagc;
				j = i;
			}
			else {
				i++;
			}
		}
		RETU = RETU + con.substring(j);
		return RETU;
	}

	public static boolean isContainChar(String a, String b)
	{
		if ((a == null) || (b == null))
			return false;
		char[] bset = b.toCharArray();
		for (int i = 0; i < bset.length; i++) {
			for (int j = 0; j < a.length(); j++) {
				if (bset[i] == a.charAt(j))
					return true;
			}
		}
		return false;
	}

	@Deprecated
	public static String htmlToStr(String htmlStr)
	{
		String regEx = "<\\s*img\\s+([^>]+)\\s*>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(htmlStr);
		return m.replaceAll("").replace("&nbsp;", "");
	}

	public static String toUpperCase(String value) {
		return value == null ? null : value.toUpperCase();
	}
	@Deprecated
	public static String upperCase(String s) {
		return toUpperCase(s);
	}

	public static boolean isSameByTrim(String str1, String str2) {
		if ((str1 == null) || (str1 == null)) {
			return false;
		}
		return str1.trim().equals(str2.trim());
	}

	@Deprecated
	public static String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length) && (toCount > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr = reStr + tempChar[kk];
		}
		if ((toCount == reInt) || (toCount == reInt - 1))
			reStr = reStr + more;
		return reStr;
	}
	@Deprecated
	public static String mbToTGM(BigDecimal mb) {
		return NumberUtils.mbToTGM(mb);
	}
	@Deprecated
	public static boolean isNumeric(String str) {
		return NumberUtils.isNumeric(str);
	}
	@Deprecated
	public static String bytesToHexString(byte[] ba) {
		return ByteUtils.bytes2hex(ba);
	}
	@Deprecated
	public static String bytesToHexString(byte[] ba, String prefix) {
		if ((ba == null) || (prefix == null)) {
			throw new NullPointerException();
		}

		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		sb.append(ByteUtils.bytes2hex(ba));
		return sb.toString();
	}
	@Deprecated
	public static String bytesToHexStringForDebug(byte[] ba, String prefix) {
		return bytesToHexString(ba, prefix);
	}
	@Deprecated
	public static byte[] hexStringToBytes(String hexStr) {
		return ByteUtils.hex2bytes(hexStr);
	}
	@Deprecated
	public static byte[] hexStringToBytes(String hexStr, String prefix) {
		if ((hexStr == null) || (prefix == null)) {
			throw new NullPointerException();
		}
		String myHexStr = hexStr.trim();
		if (myHexStr.startsWith(prefix)) {
			myHexStr = myHexStr.substring(prefix.length());
		}

		return ByteUtils.hex2bytes(myHexStr);
	}
	@Deprecated
	public static byte[] intToByte4(int i) {
		return ByteUtils.int2bytes(i);
	}
	@Deprecated
	public static byte[] shortToByte2(short i) {
		return ByteUtils.short2bytes(i);
	}
	@Deprecated
	public static int byte4ToInt(byte[] b) {
		return ByteUtils.bytes2int(b);
	}
	@Deprecated
	public static short byte2ToShort(byte[] b) {
		return (short)ByteUtils.byte2short(b);
	}
}