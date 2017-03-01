/** 
 * Project Name:platform-api-core 
 * File Name:Log.java 
 * Package Name:com.asiainfo.foundation.log 
 * Date:2014年9月28日下午8:40:49 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.log;  

import java.io.Serializable;

import com.asiainfo.foundation.exception.BusinessException;


/** 
 * ClassName:Log <br/> 
 * Function: 封装的日志类,所有业务模块的代码请引用这个类. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午8:40:49 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class Logger implements Serializable{

	private static final long serialVersionUID = 1L;
	private LoggerWrapper log;
	private static final String defaultLogName = "com.asiainfo.fundation.log";

	/**
	 * 构造函数
	 * @param name
	 */
	private Logger(String name) {
		log = new LoggerWrapper(name);
	}

	/**
	 * 静态构造函数,根据name构造log,客户端主调用这个方法
	 * @param name
	 * @return
	 */
	public static Logger getLogger(String name) {
		String s = name;
		if (s == null)
			s = defaultLogName;
		return new Logger(s);
	}

	/**
	 * 静态构造函数,根据name构造log,客户端主调用这个方法
	 * @param clazz
	 * @return
	 */
	public static Logger getLog(Class clazz) {
		String s = defaultLogName;
		if (clazz != null)
			s = clazz.getName();
		return getLogger(s);
	}

	/**
	 * 判断日志的记录模式
	 * @return 枚举类型LogModelStatus,分别为RunTimeClose,RunTimeOpen,RunTimeClose
	 */
	private static LogModelStatus getLogModelStatus() {
		LogModelStatus model = LogModelStatus.RunTimeClose;
		LoggerContext logContext = LoggerContextHolder.getLoggerContext();
		if (logContext != null) {
			if (logContext.getLogSwitch()) {
				model = LogModelStatus.RunTimeOpen;
			} else
				model = LogModelStatus.RunTimeClose;
		} else {
			//日志模式为:开发模式
			model = LogModelStatus.Develop;
		}
		return model;
	}

	/**
	 * 判断是否开日志,LogModelStatus为开发模式默认为开日志,由logback.xml去判断
	 * @param model
	 * @return
	 */
	private static boolean isLogOpen(LogModelStatus model) {
		if (model == LogModelStatus.RunTimeOpen || model == LogModelStatus.Develop)
			return true;
		else
			return false;
	}

	/**
	 * 默认判断日志开关方法
	 * @return
	 */
	private static boolean isLogOpen() {
		return isLogOpen(getLogModelStatus());
	}

	/**
	 * 增加日志中的服务调用实例，线程号等信息
	 * @return
	 */
	private static String buildLogContextInfo() {
		long threadId = Thread.currentThread().getId();
		String serInvokeInstId = null;
		LoggerContext logContext = LoggerContextHolder.getLoggerContext();
		if (logContext != null) {
			serInvokeInstId = logContext.getSerInvokeInstId();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("AI_FUNDATION_LOGGER:threadId=");
		sb.append(threadId);
		sb.append(",serInvokeInstId=");
		sb.append(serInvokeInstId);
		sb.append(",info=");
		return sb.toString();
	}

	/**
	 * 功能	：记录debug日志
	 * 示例	：log.debug("My name is {0}, i am {1} years old", "zhuangyq", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void debug(String message, Object... args) {
		try{
			LogModelStatus model = getLogModelStatus();
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.debug(buildLogContextInfo() + message, args);
				} else
					log.debug(message, args);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}

	}
	
	/**
	 * 功能	：记录debug日志
	 * 示例	：log.debug(LogModel.EVENT_USER_LOGON,"user {0} logs on System,say {1}","david","hello world");
	 * 
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void debug(LogModel logModel,String message, Object... args) {
		try{
			LogModelStatus model = getLogModelStatus();
			
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.debug(buildLogContextInfo()+logModel+"_"+message, args);
				} else
					log.debug(logModel+"_"+message, args);
			}
		}catch(Throwable e){
			e.printStackTrace();
		}

	}	

	/**
	 * 功能	：记录info日志
	 * 示例	：log.info("My name is {0}, i am {1} years old", "david", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void info(String message, Object... args) {
		try {
			LogModelStatus model = getLogModelStatus();
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.info(buildLogContextInfo() + message, args);
				} else
					log.info(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录info日志
	 * 示例	：log.info(LogModel.EVENT_USER_LOGON,"user {0} logs on System,say {1}","david","hello world");
	 * 
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void info(LogModel logModel,String message, Object... args) {
	  try{
			LogModelStatus model = getLogModelStatus();				
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.info(buildLogContextInfo()+logModel+"_" + message, args);
				} else
					log.info(logModel+"_"+message, args);
				}
			}catch(Throwable e){
			e.printStackTrace();
		}
	
	}
	
	/**
	 * 功能	：记录warn日志
	 * 示例	：log.warn("My name is {0}, i am {1} years old", "david", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void warn(String message, Object... args) {
		try {
			LogModelStatus model = getLogModelStatus();
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.warn(buildLogContextInfo() + message, args);
				} else
					log.warn(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录warn日志
	 * 示例	：log.warn(LogModel.EVENT_USER_LOGON,"user {0} logs on System,say {1}","david","hello world");
	 * 
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void warn(LogModel logModel,String message, Object... args) {
	  try{
			LogModelStatus model = getLogModelStatus();				
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.warn(buildLogContextInfo()+logModel+"_"  + message, args);
				} else
					log.warn(logModel+"_"+message, args);
				}
			}catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能	：记录trace日志
	 * 示例	：log.trace("My name is {0}, i am {1} years old", "david", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void trace(String message, Object... args) {
		try {
			LogModelStatus model = getLogModelStatus();
			if (isLogOpen(model)) {
				if (model == LogModelStatus.RunTimeOpen) {
					log.trace(buildLogContextInfo() + message, args);
				} else
					log.trace(message, args);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录trace日志
	 * 示例	：log.trace(LogModel.EVENT_USER_LOGON,"user {0} logs on System,say {1}","david","hello world");
	 * 
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void trace(LogModel logModel,String message, Object... args) {
	   try {
		  LogModelStatus model = getLogModelStatus();
		  if (isLogOpen(model)) {
			if (model == LogModelStatus.RunTimeOpen) {
				log.trace(buildLogContextInfo() +logModel+"_"+ message, args);
			} else
				log.trace(logModel+"_"+message, args);
		 }
	   } catch (Throwable e) {
		e.printStackTrace();
	  }
	}
	
	/**
	 * 功能	：记录error日志
	 * 示例	：log.error("My name is {0}, i am {1} years old", "david", "30");
	 * 
	 * @param message	日志消息,参数变量可以用{}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{}号对应
	 */
	public void error(String message, Object... args) {
		try {
			LogModelStatus model = getLogModelStatus();
			//error级别不受运行时控制 if (isLogOpen(model)){
			if (model == LogModelStatus.RunTimeOpen) {
				log.error(buildLogContextInfo() + message, args);
			} else
				log.error(message, args);
			//}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能	：记录error日志
	 * 示例	：log.error("My name is " + "david", exception);
	 * 
	 * @param message	日志消息,
	 * @param e			异常
	 */
	public void error(String message, Throwable e) {
		try {
			LogModelStatus model = getLogModelStatus();
			//error级别不受运行时控制if (isLogOpen(model)){
			if (model == LogModelStatus.RunTimeOpen) {
				log.error(buildLogContextInfo() + message, e);
			} else
				log.error(message, e);
			//}
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	

	public void error(LogModel logModel, String message, Throwable e) {
		try {
			LogModelStatus model = getLogModelStatus();
			//error级别不受运行时控制if (isLogOpen(model)){
			if (model == LogModelStatus.RunTimeOpen) {
				log.error(buildLogContextInfo()  +logModel+"_" +  message, e);
			} else
				log.error(logModel+"_" + message, e);
			//}
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	
	/**
	 * 功能	：记录error日志
	 * 示例	：log.error(LogModel.EVENT_USER_LOGON,"user {0} logs on System,say {1}","david","hello world");
	 * 
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param message	日志消息,参数变量可以用{_idx}来表示
	 * @param args		可变参数变量,可以传入0到多个,与Message中的{_idx}号对应
	 */
	public void error(LogModel logModel,String message, Object... args) {
	   try {
		  LogModelStatus model = getLogModelStatus();
		  //error级别不受运行时控制if (isLogOpen(model)){
		  if (model == LogModelStatus.RunTimeOpen) {
			log.error(buildLogContextInfo() +logModel+"_" + message, args);
		  } else
			log.error(logModel+"_"+message, args);
		  //}
	   } catch (Throwable error) {
		error.printStackTrace();
	  }
	}
	
	/**
	 * 功能	：记录error日志
	 * 示例	：	log.error(LogModel.EVENT_SYS_ERR,new BusinessException(9001, "The component of initiator  authenticates failure ",null));
	 * @param logModel 日志类型：如安全事件下的各种类型、错误事件下的各种类型
	 * @param e	 异常
	 */
	public void error(LogModel logModel, Throwable e) {
		try {
			LogModelStatus model = getLogModelStatus();
			//error级别不受运行时控制if (isLogOpen(model)){
			if (model == LogModelStatus.RunTimeOpen) {
				if(e instanceof BusinessException){
					log.error(e,buildLogContextInfo() +logModel+"_", ((BusinessException) e).getResult().toString());
				}			
			} else
				if(e instanceof BusinessException){
					BusinessException be = (BusinessException)e;
					/*if(be.getCause()!=null){
						log.error(be,logModel+"_[resultCode:{0},resultDesc:{1},cause:{2}]", be.getResult().getCode(),be.getResult().getMsg(),be.getCause());
					}else
						log.error(logModel+"_[resultCode:{0},resultDesc:{1}]", be.getResult().getCode(),be.getResult().getMsg());
					*/
					log.error(be,logModel+"_[resultCode:{0},resultDesc:{1}]", be.getResult().getCode(),be.getResult().getMsg());
				}else{
					log.error(logModel.name(), e);
				}				
			//}
		} catch (Throwable error) {
			error.printStackTrace();
		}
	}
	
	/**
	 * 是否debug可用
	 * @return
	 */
	public boolean isDebugEnabled() {
		try {
			if (log == null)
				return false;

			return isLogOpen() && log.isDebugEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否error级别可用
	 * @return
	 */
	public boolean isErrorEnabled() {
		try {
			if (log == null)
				return false;
			return log.isErrorEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否info级别可用
	 * @return
	 */
	public boolean isInfoEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isInfoEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否trace级别可用
	 * @return
	 */
	public boolean isTraceEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isTraceEnabled();
		} catch (Throwable e) {
			return false;
		}
	}
	/**
	 * 是否warn级别可用
	 * @return
	 */
	public boolean isWarnEnabled() {
		try {
			if (log == null)
				return false;
			return isLogOpen() && log.isWarnEnabled();
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 日志状态，分别为'运行时开启','运行时关闭','开发模式'
	 *
	 */
	private enum LogModelStatus {
		RunTimeOpen, RunTimeClose, Develop
	}
}
