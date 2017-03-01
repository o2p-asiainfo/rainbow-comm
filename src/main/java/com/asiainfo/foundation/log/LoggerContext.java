/** 
 * Project Name:platform-api-core 
 * File Name:LoggerContext.java 
 * Package Name:com.asiainfo.foundation.log 
 * Date:2014年9月28日下午8:52:33 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.log;  

import java.io.Serializable;

/** 
 * ClassName:LoggerContext <br/> 
 * Function: 保存日志的上下文信息，如服务调用实例、日志开关等. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午8:52:33 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class LoggerContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1824909131431581344L;
	private String serInvokeInstId;
	private boolean logSwitch;
	public LoggerContext(String serInvokeInstId, boolean logSwitch){
		this.serInvokeInstId = serInvokeInstId;
		this.logSwitch = logSwitch;
	}

	public String getSerInvokeInstId() {
		return serInvokeInstId;
	}

	public void setSerInvokeInstId(String serInvokeInstId) {
		this.serInvokeInstId = serInvokeInstId;
	}

	/**
	 * 日志开关

	 * @return true表示该服务调用实例开启了日志,false表示关闭日志
	 */
	public boolean getLogSwitch() {
		return logSwitch;
	}
	public void setLogSwitch(boolean logSwitch) {
		this.logSwitch = logSwitch;
	}
}
