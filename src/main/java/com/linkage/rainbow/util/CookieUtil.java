package com.linkage.rainbow.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieUtil<br>
 * 该类主要用于对cookie进行操作
 * <p>
 * @version 1.0
 * @author chenl Jan 19, 2010
 * <hr>
 * 修改记录
 * <hr>
 * 1、修改人员:    修改时间:<br>       
 *    修改内容:
 * <hr>
 */

public class CookieUtil {

	/**
	 * 设置Cookie
	 * @param request
	 * @param response
	 * @param cookieKey
	 * @param cookieValue
	 * @param timeOut
	 */
	public static void setCookie(HttpServletRequest request,HttpServletResponse response,String cookieKey,String cookieValue,int timeOut){ 
		Cookie[] cookies = request.getCookies();
		cookieValue = StringUtil.toBASE64String(cookieValue);
	    Cookie cookie = null;
	    if(cookies != null){
		    for(int i=0;i<cookies.length;i++){
			    if(cookies[i].getName().equalsIgnoreCase(cookieKey)){	
				     cookie = cookies[i];
				     break;
			    }	
		   }
	   }
	   if(cookie == null){
		    cookie = new Cookie(cookieKey,cookieValue);
		    cookie.setPath("/");
		    cookie.setMaxAge(timeOut);
	   }  
	   else{
		    cookie.setValue(cookieValue);
		    cookie.setPath("/");
		    cookie.setMaxAge(timeOut);
	   }
	   response.addCookie(cookie);
    }
	
	/**
	 * 取Cookie中所需的值,Cookie没有值返回空串
	 * @param request
	 * @param cookieKey
	 * @return String
	 */
	public static String getCookie(HttpServletRequest request,String cookieKey){ 
		Cookie[] cookies = request.getCookies();
	    Cookie cookie = null;
	    String cookieValue = "";
	    if(cookies != null){
		    for(int i=0;i<cookies.length;i++){	
			    if(cookies[i].getName().equalsIgnoreCase(cookieKey)){	
				     cookie = cookies[i];
				     break;
			    }	
		   }
	   }
	   if(cookie != null){
		   cookieValue = StringUtil.fromBASE64String(cookie.getValue());
	   }
	   return cookieValue;
    }
}
