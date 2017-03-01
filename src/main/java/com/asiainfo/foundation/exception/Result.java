/** 
 * Project Name:platform-api-core 
 * File Name:Result.java 
 * Package Name:com.asiainfo.foundation.exception 
 * Date:2014年9月28日下午5:49:27 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.exception;  

import java.io.Serializable;

/** 
 * ClassName:Result <br/> 
 * Function: 定义的错误编码体系 <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年9月28日 下午5:49:27 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6961977579213088716L;

	/**
	 * 系统共用的错误编码,成功与系统错误, 0与-9开头的为内部保留编码,其他子系统不可使用

	 */
	public final static Result SUCCESS = new Result("0", "success");
	public final static Result SYS_ERROR = new Result("-9999", "system error");

	private String code = "0";
	private String msg = "success";

	/**
	 * Result构造函数

	 * @param code
	 * @param msg
	 */
	public Result(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg) {
		this.code = code+"";
		this.msg = msg;
	}
	
	
	public Result(Result result) {
		this.code = result.getCode();
		this.msg = result.getMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 只要错误编码相同,就认为两个对象相同

	 * @param r
	 * @return
	 */
	@Override
	public boolean equals(Object r) {
		boolean b = false;
		if (r instanceof Result) {
			if (getCode() == ((Result) r).getCode())
				b = true;
			else
				b = false;
		} else
			b = super.equals(r);
		return b;
	}
	@Override
	public int hashCode(){
		return super.hashCode();
	}
	/**
	 * 返回Result对象的toString字符串

	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<code>");
		sb.append(getCode());
		sb.append("</code>");
		sb.append("<msg>");
		sb.append(getMsg());
		sb.append("</msg>");
		return sb.toString();
	}
}
