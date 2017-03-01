package com.linkage.rainbow.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

/*************************************************
MathUtil类主要是对数字类型数据进行处理，
如对数据类型进行转换
*************************************************/
public class MathUtil {
	
	static DecimalFormat format = new DecimalFormat("0.#################");
    static NumberFormat cash = new DecimalFormat("#,##0.00");
 
	/**
	 * 
	  * 函数名称：round<br>
	  * 函数功能：四舍五入,并支持小数位的的方法<br>
	  * 例：  MathUtil.round(120345607.089,2) 输出：1.2034560709E8
	  * @param v --数值
	  * @param scale - 精确位数
	  * @return Double
	 */
	public static Double round(Double v, int scale) {
		if (v != null) {
			String temp = "###0.";
			for (int i = 0; i < scale; i++) {
				temp += "0";
			}
			return Double.valueOf(new java.text.DecimalFormat(temp).format(v));
		} else {
			return null;
		}
	} 
	/**
	 * 
	  * 函数名称：round<br>
	  * 函数功能：四舍五入,并支持小数位的的方法<br>
	  * 例：  MathUtil.round(45607.089,2) 输出：45607.09
	  * @param v --数值
	  * @param scale - 精确位数
	  * @return Double
	 */
	public static Float round(Float v, int scale){
		if (v != null) {
			String temp = "###0.";
			for (int i = 0; i < scale; i++) {
				temp += "0";
			}
			return Float.valueOf(new java.text.DecimalFormat(temp).format(v));
		} else {
			return null;
		}
		
	}
	/**
	 * 
	  * 函数名称：roundToString<br>
	  * 函数功能：将Double型转成字符型<br>
	  * 例：  MathUtil.roundToString(120345607.089,2) 输出：1,2034,5607.09
	  * @param v --double
	  * @param scale -- 精确位数
	  * @return String
	 */
	public static String roundToString(Double v, int scale) {
		if (v != null) {
			String temp = "###,###,###,###0.";
			for (int i = 0; i < scale; i++) {
				temp += "0";
			}
			return new java.text.DecimalFormat(temp).format(v);
		} else {
			return null;
		}
	} 
	
	/**
	 * 
	  * 函数名称：roundToString<br>
	  * 函数功能：将Float型转成字符型<br>
	  * 例：  MathUtil.roundToString(120345607.089,2) 输出：1,2034,5607.09
	  * @param v --Float
	  * @param scale -- 精确位数
	  * @return String
	 */
	public static String roundToString(Float v, int scale) {
		if (v != null) {
			String temp = "###,###,###,###0.";
			for (int i = 0; i < scale; i++) {
				temp += "0";
			}
			return new java.text.DecimalFormat(temp).format(v);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	  * 函数名称：getSingleChineseNumber<br>
	  * 函数功能：获取数字（0-9）的中文大写,超出9输出为空<br>
	  * 例：MathUtil.getSingleChineseNumber(2) 输出：贰<br>；
	  * @param num
	  * @return String
	 */
	public static String getSingleChineseNumber(int num) {
		switch (num) {
			case 0:
				return "零";
			case 1:
				return "壹";
			case 2:
				return "贰";
			case 3:
				return "叁";
			case 4:
				return "肆";
			case 5:
				return "伍";
			case 6:
				return "陆";
			case 7:
				return "柒";
			case 8:
				return "捌";
			case 9:
				return "玖";
			default:
				return "";
		}
	}
	
	/**
	 * 
	  * 函数名称：getChineseMedianu<br>
	  * 函数功能：获取数字所在位（0-15）的中文单位,超出15输出为空<br>
	  * 例：个位：MathUtil.getChineseMedianu(0) 输出：空；<br>
	  *    十位：MathUtil.getChineseMedianu(1) 输出：拾； <br>
	  *    佰位：MathUtil.getChineseMedianu(2) 输出：佰；<br>
	  * @param median   
	  * @return String
	 */
	public static String getChineseMedianu(int median) {
		switch (median) {
			case 0:
				return "";
			case 1:
				return "拾";
			case 2:
				return "佰";
			case 3:
				return "仟";
			case 4:
				return "万";
			case 5:
				return "拾";
			case 6:
				return "佰";
			case 7:
				return "仟";
			case 8:
				return "亿";
			case 9:
				return "拾";
			case 10:
				return "佰";
			case 11:
				return "仟";
			case 12:
				return "兆";
			case 13:
				return "拾";
			case 14:
				return "佰";
			case 15:
				return "仟";
			default:
				return "";
		}
	}
	
	/**
	 * 把数字以小数点分割存放到二维数组中<br>
	 * 简要说明：注意：参数count一般用-1,或大于等于整数部分位数的数字<br>
	 * 例：int[][] intArr=MathUtil.splitNumbers(120345607.089,-1);<br>
	 *     int[][] intArr1=MathUtil.splitNumbers(120345607.089,10);<br>
	 *     //intArr[0]存放整数部分{1,2,0,3,4,5,6,0,7} intArr[0]存放小数部分{0,8,9}<br>
	 *     //intArr1[0]存放整数部分{1,2,0,3,4,5,6,0,7} intArr1[0]存放小数部分{0}
	 * @param num
	 * @param count
	 * @return int[][]
	 */
	public static int[][] splitNumbers(double num, int count) {
		int[] ints = null;
		int[] floats = null;
		String str = format.format(num);
		int idx = str.indexOf(".");

		if (count <= 0) {
			count = str.length();
		} else if (idx != -1) {
			count++;
		}

		if (str.length() > count) {
			str = str.substring(0, count);
		} else {
			while (str.length() < count) {
				str = "0" + str;
			}
		}

		String intStr = null;
		String floatStr = null;
		if (idx != -1) {
			intStr = str.substring(0, idx);
			floatStr = str.substring(idx + 1);
		} else {
			intStr = str;
		}
		if (intStr != null) {
			ints = new int[intStr.length()];
			for (int i = 0; i < intStr.length(); i++) {
				ints[i] = Integer.parseInt(String.valueOf(intStr.charAt(i)));
			}
		}
		if (floatStr != null) {
			floats = new int[floatStr.length()];
			for (int i = 0; i < floatStr.length(); i++) {
				floats[i] = Integer.parseInt(String.valueOf(floatStr.charAt(i)));
			}
		}
		return new int[][] { ints, floats };
	}
	
	/**
	 * 
	  * 函数名称：getChineseNumber<br>
	  * 函数功能：把数字转换为中文数字<br>
	  * 例：MathUtil.getChineseNumber(120345607.089) 输出：壹亿贰仟叁拾肆万伍仟陆佰柒点零捌玖
	  * @param num
	  * @return String
	 */
	public static String getChineseNumber(double num) {
		int[][] nums = splitNumbers(num, -1);
		String str = "";
		//处理整数部分
		int[] ints = nums[0];
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] == 0){
                            if((ints.length - (i+1)==4)||(ints.length - (i+1)==8)){
                                str += ""+ getChineseMedianu(ints.length - (i+1));
                            }
                            continue;
                        }
			str += getSingleChineseNumber(ints[i]) + getChineseMedianu(ints.length - (i+1));
		}
		//如果有小数部分
		int[] floats = nums[1];
		if(floats != null && floats.length > 0){
			str += "点";
			for(int i=0;i<floats.length;i++){
				str += getSingleChineseNumber(floats[i]);
			}
		}

		return str;
	}
	
	/**
	 * 
	  * 函数名称：getUnderChineseNumber<br>
	  * 函数功能：把数字转换为中文数字<br>
	  * 例：MathUtil.getUnderChineseNumber(120345607.089) 输出：壹亿贰仟零佰叁拾肆万伍仟陆佰零拾柒点零捌玖
	  * @param num
	  * @return String
	 */
	public static String getUnderChineseNumber(double num) {
         int[][] nums = splitNumbers(num, -1);
         String str = "";
         //处理整数部分
         int[] ints = nums[0];
         for(int i=0;i<(6-ints.length);i++){

          str +=getChineseMedianu(6 - (i+1));
         }

         int f=0;
         f=f+1;
         for (int i = 0; i < ints.length; i++) {
         	/*
                 if (ints[i] == 0){
                         if((ints.length - (i+1)==4)||(ints.length - (i+1)==8)){
                             str += ""+ getChineseMedianu(ints.length - (i+1));
                         }else if((ints.length - (i+1)==0)){
                             if(ints.length<2)
                             str += "<u>&nbsp;&nbsp;</u>";
                         }
                         continue;
                }
                 */
                //if(ints.length<6&&i==0)str+="<u>&nbsp;&nbsp;</u>拾";
                str +=getSingleChineseNumber(ints[i]) + getChineseMedianu(ints.length - (i+1));
         }
         //如果有小数部分
         int[] floats = nums[1];
         if(floats != null && floats.length > 0){
                 str += "点";
                 for(int i=0;i<floats.length;i++){
                         str += getSingleChineseNumber(floats[i]);
                 }
         }

         return str;
 }
	
	/**
	 * 
	  *
	  * 函数名称：objToInt<br>
	  * 函数功能：将对像转成数值型<br>
	  * 例：String a="123";<br>
	  *    int b=MathUtil.objToInt(a);<br>
	  *    System.out.println(b);输出：123
	  * @param o - object
	  * @return Integer
	  *
	 */
	public static Integer objToInt(Object o) {
		Integer result = null;
		try {
			String str = StringUtil.valueOf(o);
			if(str=="") return Integer.parseInt("0");
			result = Integer.valueOf(StringUtil.valueOf(o));
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 
	  *
	  * 函数名称：objToLong<br>
	  * 函数功能：将对像转成长整型<br>
	  * 例：String a="123";<br>
	  *    long b=MathUtil.objToLong(a);<br>
	  *    System.out.println(b);输出：123
	  * @param o - object
	  * @return Long
	  *
	 */
	public static Long objToLong(Object o) {
		Long result = null;
		try {
			String str =StringUtil.valueOf(o);
			if(str=="") return Long.parseLong("0");
			result = Long.valueOf(StringUtil.valueOf(o));
		} catch (Exception e) {

		}
		return result;
	}
	/**
	 * 
	  *
	  * 函数名称：objToFloat<br>
	  * 函数功能：将对像转成浮点型<br>
	  * 例：String a="123.12";<br>
	  *    float b=MathUtil.objToFloat(a);<br>
	  *    System.out.println(b);输出：123.12
	  * @param o - object
	  * @return Float
	  *
	 */
	public static Float objToFloat(Object o) {
		Float result = null;
		try {
			String str = StringUtil.valueOf(o);
			if(str=="") return Float.parseFloat("0");
			result = Float.valueOf(StringUtil.valueOf(o));
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 
	  *
	  * 函数名称：objToDouble<br>
	  * 函数功能：将对像转成双进度型<br>
	  * 例：String a="123.12";<br>
	  *    float b=MathUtil.objToFloat(a);<br>
	  *    System.out.println(b);输出：123.12
	  * @param o - object
	  * @return Float
	  *
	 */
	public static Double objToDouble(Object o) {
		Double result = null;
		try {
			String str = StringUtil.valueOf(o);
			if(str=="") return Double.parseDouble("0");
			result = Double.valueOf(StringUtil.valueOf(o));
		} catch (Exception e) {

		}
		return result;
	}
	
	/**
	 * 是否为数字类型的类 instanceof Number
	 * @param o
	 * @return
	 */
	public static boolean isNumber(Object o) {
		return o instanceof Number;
	}
	
	protected static int getMaxNumberType(Object o1, Object o2) {
		int type1 = getNumberType(o1);
		int type2 = getNumberType(o2);
		return type1 < type2 ? type2 : type1;
	}

	protected static int getNumberType(Object o) {
		if (o instanceof Integer)
			return 1;
		if (o instanceof Long)
			return 2;
		if (o instanceof Double)
			return 3;
		if (o instanceof BigInteger)
			return 4;
		if (o instanceof BigDecimal)
			return 4;
		if (o instanceof Byte)
			return 1;
		if (o instanceof Short)
			return 1;
		if (o instanceof Float)
			return 3;
		else
			throw new RuntimeException();
	}
	
	/**
	 * 取得数值类类型
	 * @param o
	 * @return
	 */
	public static String getDataType(Object o) {
		if (o == null)
			return "空对象";
		if (o instanceof String)
			return "字符串";
		if (o instanceof Double)
			return "双精度浮点数";
		if (o instanceof Integer)
			return "整数";
		if (o instanceof Long)
			return "长整数";
		if (o instanceof Boolean)
			return "布尔型";
		if (o instanceof BigDecimal)
			return "长小数";
		if (o instanceof Short)
			return "短整数";
		if (o instanceof Date)
			return "日期";
		if (o instanceof Byte)
			return "字节";
		else
			return o.getClass().getName();
	}
	
	private static int intValue(Object o) {
		if (o instanceof Number)
			return ((Number) o).intValue();
		else
			throw new RuntimeException("不能取整数");
	}
	/**
	 * object转为long数值
	 * @param o
	 * @return
	 */
	private static long longValue(Object o) {
		if (o instanceof Number)
			return ((Number) o).longValue();
		else
			throw new RuntimeException("不能取长整数");
	}

	/**
	 * object转为double数值
	 * @param o
	 * @return
	 */
	private static double doubleValue(Object o) {
		if(o == null)
			throw new RuntimeException("不能取双精度浮点数");
		if (o instanceof Number)
			return ((Number) o).doubleValue();
		else{
			try {
				return Double.parseDouble(o.toString());
			} catch (Exception e) {
				throw new RuntimeException("不能取双精度浮点数");
			}
		}
			
	}
	
	/**
	 * object转为BigDecimal数值
	 * @param o
	 * @return
	 */
	private static BigDecimal toBigDecimal(Object o) {
		if (o instanceof BigDecimal)
			return (BigDecimal) o;
		if (o instanceof BigInteger)
			return new BigDecimal((BigInteger) o);
		else
			return new BigDecimal(doubleValue(o));
	}
	
	/**
	 * 求余数
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Object mod(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			return null;
		if (isNumber(o1) && isNumber(o2)) {
			int type = getMaxNumberType(o1, o2);
			switch (type) {
			case 1:
				return new Integer(intValue(o1) % intValue(o2));

			case 2:
				return new Long(longValue(o1) % longValue(o2));

			case 3:
			case 4:
				return new Double(doubleValue(o1) % doubleValue(o2));

			default:
				break;
			}
		}
		throw new RuntimeException(getDataType(o1) + "与" + getDataType(o2)
				+ "不能求余");
	}

	/**
	 * 乘法运算
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Object multiply(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			return null;
		if (isNumber(o1) && isNumber(o2)) {
			int type = getMaxNumberType(o1, o2);
			switch (type) {
			case 1:
				return new Integer(intValue(o1) * intValue(o2));

			case 2:
				return new Long(longValue(o1) * longValue(o2));

			case 3:
				return new Double(doubleValue(o1) * doubleValue(o2));

			case 4:
				return toBigDecimal(o1).multiply(toBigDecimal(o2));

			default:
				break;
			}
		}
		throw new RuntimeException(getDataType(o1) + "与" + getDataType(o2)
				+ "不能相乘");
	}

	/**
	 * 负运算
	 * 
	 * @param o
	 * @return
	 */
	public static Object negate(Object o) {
		if (o == null)
			return null;
		if (isNumber(o)) {
			int type = getNumberType(o);
			switch (type) {
			case 1:
				return new Integer(-intValue(o));

			case 2:
				return new Long(-longValue(o));

			case 3:
				return new Double(-doubleValue(o));

			case 4:
				return toBigDecimal(o).negate();

			default:
				break;
			}
		}
		throw new RuntimeException(getDataType(o) + "不能求负");
	}

	/**
	 * 减法运算
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Object subtract(Object o1, Object o2) {

		if (o1 == null)
			return negate(o2);
		if (o2 == null)
			return o1;
		if (isNumber(o1) && isNumber(o2)) {
			int type = getMaxNumberType(o1, o2);
			switch (type) {
			case 1:
				return new Integer(intValue(o1) - intValue(o2));

			case 2:
				return new Long(longValue(o1) - longValue(o2));

			case 3:
				return new Double(doubleValue(o1) - doubleValue(o2));

			case 4:
				return toBigDecimal(o1).subtract(toBigDecimal(o2));

			default:
				break;
			}
		}
		throw new RuntimeException(getDataType(o1) + "与" + getDataType(o2)
				+ "不能相减");
	}

	/**
	 * 除法运算
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static Object divide(Object o1, Object o2) {
		if (o1 == null || o2 == null)
			return null;
		if (isNumber(o1) && isNumber(o2)) {
			int type = getMaxNumberType(o1, o2);
			if (type < 3) {
				return new Double(doubleValue(o1) / doubleValue(o2));
			} else {
				BigDecimal result = toBigDecimal(o1).divide(toBigDecimal(o2), 20, 4);
				
				result = BigDecimal.valueOf(result.doubleValue());
				return result;
			}
		}
		throw new RuntimeException(getDataType(o1) + "与" + getDataType(o2)
				+ "不能相除");
	}
	
	/**
	 * 是否为负数
	 * @param n
	 * @return
	 */
	public static boolean isNegative(Number n){
		return n.doubleValue()<0;
	}
	
	public static void main(String []args){
		double num=120345607.089;
		int[][] intArr=MathUtil.splitNumbers(120345607.089,-1);
	}
}
