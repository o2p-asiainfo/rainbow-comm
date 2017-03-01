/** 
 * Project Name:platform-api-core 
 * File Name:LoggerWrapper.java 
 * Package Name:com.asiainfo.foundation.log 
 * Date:2014年9月28日下午8:39:13 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.log;  

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asiainfo.foundation.util.MessageUtils;
/** 
 * ClassName:LoggerWrapper <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午8:39:13 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LoggerWrapper{

	// 这里可以替换成Log4jLogger
	private Logger _logger;

	public LoggerWrapper(String name) {
		// 这里可以替换成Log4jLogger
		_logger = LoggerFactory.getLogger(name);
	}

	public void debug(String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.debug(message);
	}

	public void info(String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.info(message);
	}

	public void warn(String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.warn(message);
	}

	public void trace(String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.trace(message);
	}

	public void error(String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.error(message);
	}
	
	//lity ADD
	public void error(Throwable e,String message, Object... args) {
		if(args.length>0){
			message = MessageUtils.resolve(message, args);
		}
		_logger.error(message,e);
	}
	
	
	public void error(String message, Throwable e) {
		_logger.error(message, e);
	}

	public boolean isDebugEnabled() {
		if (_logger == null)
			return false;
		return _logger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		if (_logger == null)
			return false;
		return _logger.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		if (_logger == null)
			return false;
		return _logger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		if (_logger == null)
			return false;
		return _logger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		if (_logger == null)
			return false;
		return _logger.isWarnEnabled();
	}
}
