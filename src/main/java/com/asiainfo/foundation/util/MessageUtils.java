/** 
 * Project Name:platform-api-core 
 * File Name:MessageUtils.java 
 * Package Name:com.asiainfo.foundation.util 
 * Date:2014年9月28日下午7:26:50 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.util;  

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/** 
 * ClassName:MessageUtils <br/> 
 * Function: 格式化消息. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午7:26:50 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class MessageUtils
{
	/**
	 * 
	 * resolve:格式化消息参数xxx {0},{1},参数是参数值数组，参数的索引号与消息串'{index}'匹配. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * 
	 * @author 颖勤 
	 * @param format
	 * @param args
	 * @return 
	 * @since JDK 1.6
	 */
  public static String resolve(String msg, Object[] args)
  {
    @SuppressWarnings("rawtypes")
	Map parameters = new HashMap();
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        parameters.put(i, args[i]);
      }
    }
    return resolve(msg, parameters, "{", "}");
  }

  public static String resolve(String format, Object[] args, String leftFlag, String rightFlag) {
    Map parameters = new HashMap();
    if (args != null) {
      for (int i = 0; i < args.length; i++) {
        parameters.put(i, args[i]);
      }
    }
    return resolve(format, parameters, leftFlag, rightFlag);
  }

  public static String resolve(String format, Map<String, ? extends Object> params) {
    return resolve(format, params, "{", "}");
  }

  public static String resolve(String format, Map<String, ? extends Object> params, String leftFlag, String rightFlag)
  {
    if (params == null) {
      params = new HashMap();
    }

    if ((format == null) || (format.length() == 0)) {
      String result = "";
      return result;
    }

    if ((format.indexOf(leftFlag) < 0) || (format.indexOf(rightFlag) < 0)) {
      String result = format;
      return result;
    }

    int INIT = 0;
    int LEFT = 1;
    int RIGHT = 2;
    int index = 0;
    int rightIndex = 0;
    int flag = 0;
    String tmp = format;
    String result = "";
    String tmpStr="";
    while (true)
    {
      if ((flag != 2) && ((index = tmp.indexOf(rightFlag)) >= 0)) {
        flag = 2;
      }
      else {
        if ((flag == 1) || ((index = tmp.substring(0, index).lastIndexOf(leftFlag)) < 0)) break;
        flag = 1;
      }

      switch (flag) {
      case 2:
        rightIndex = index;
        break;
      case 1:
        String variable = tmp.substring(index + leftFlag.length(), rightIndex);
        if(variable.contains(":")){
        	break;
        }
        Object value = params.get(Integer.valueOf(variable));
        String tran = "";
        if (value != null) {
          tran = value.toString();
        }
        if ((tran == null) || (tran.equals("null"))) {
          tran = "";
        }
        tmpStr+=tmp.substring(0, index) + tran;
        tmp = tmp.substring(rightIndex + 1);
      }

    }

    result = tmpStr +tmp;
    return result;
  }

  public static Collection<String> resolveParameters(String format, String leftFlag, String rightFlag)
  {
    if (!StringUtils.hasText(format)) {
      return Collections.EMPTY_SET;
    }

    if ((format.indexOf(leftFlag) < 0) || (format.indexOf(rightFlag) < 0)) {
      return Collections.EMPTY_SET;
    }

    Collection params = new HashSet();

    int INIT = 0;
    int LEFT = 1;
    int RIGHT = 2;
    int index = 0;
    int rightIndex = 0;
    int flag = 0;
    String tmp = format;
    while (true)
    {
      if ((flag != 2) && ((index = tmp.indexOf(rightFlag)) >= 0)) {
        flag = 2;
      }
      else {
        if ((flag == 1) || ((index = tmp.substring(0, index).lastIndexOf(leftFlag)) < 0)) break;
        flag = 1;
      }

      switch (flag) {
      case 2:
        rightIndex = index;
        break;
      case 1:
        String variable = tmp.substring(index + leftFlag.length(), rightIndex);
        params.add(variable);
        tmp = tmp.substring(0, index) + tmp.substring(rightIndex + 1);
      }
    }

    return params;
  }
}
