/** 
 * Project Name:platform-api-core 
 * File Name:LoggerContextHolder.java 
 * Package Name:com.asiainfo.foundation.log 
 * Date:2014年9月28日下午8:51:45 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.log;  
/** 
 * ClassName:LoggerContextHolder <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午8:51:45 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LoggerContextHolder {
	//private static final ThreadLocal<LogContext> logContextHolder = new ThreadLocal<LogContext>();

	private static final ThreadLocal<LoggerContext> logContextHolder = new InheritableThreadLocal<LoggerContext>();
	public static void clearLogContext(){
		setLoggerContext(null);
		logContextHolder.remove();
	}

	public static void setLoggerContext(LoggerContext logContext) {
		logContextHolder.set(logContext);
	}

	public static LoggerContext getLoggerContext() {
		return logContextHolder.get();
	}
}
