package com.asiainfo.foundation.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import com.asiainfo.foundation.util.ExceptionUtils;
import com.asiainfo.foundation.util.MessageSourceUtils;
/**
 * 
 * ClassName: BusinessException <br/> 
 * Function: 业务异常类. <br/> 
 * Reason: TODO ADD REASON(可选). <br/> 
 * date: 2014年9月28日 下午5:54:01 <br/> 
 * 
 * @author 颖勤 
 * @version  
 * @since JDK 1.6
 */
public class BusinessException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7636252675570711205L;
	private Result result;
	private Throwable cause = this;
	
	public BusinessException() {
		
	}
	
	/**
	 * 构造方法
	 * @param result 返回值
	 * @param cause  异常堆栈
	 */
	public BusinessException(Result result, Throwable cause) {
		super(result.getMsg(), cause);
		this.result = result;
	}

	/**
	 * 构造方法
	 * @param code 返回码
	 * @param msg  错误消息
	 */
	public BusinessException(int code, String msg) {
		super(msg);
		this.result = new Result(code, msg);
	}
	
	public BusinessException(String code, String msg) {
		super(msg);
		this.result = new Result(code, msg);
	}
	
	

	/**
	 * 构造方法
	 * @param result 返回值
	 * @param detail 具体的返回消息
	 */
	public BusinessException(Result result, String detail) {
		super(result.getMsg() + "," + detail);
		this.result = new Result(result.getCode(), result.getMsg() + "," + detail);
	}

	/**
	 * 构造方法
	 * @param result 返回值
	 * @param detail 具体的返回消息
	 * @param cause  异常堆栈
	 */
	public BusinessException(Result result, String detail, Throwable cause) {
		super(result.getMsg() + "," + detail, cause);
		this.result = new Result(result.getCode(), result.getMsg() + "," + detail);
	}

	/**
	 * 构造方法
	 * @param code	返回码
	 * @param msg	返回消息
	 * @param cause 异常堆栈
	 */
	public BusinessException(int code, String msg, Throwable cause) {
		super(msg, cause);
		
		if(cause != null) {
			if(cause.getCause() != null) {
				msg += " cause:" + ExceptionUtils.populateExecption(cause.getCause(), 500);
			}
			msg += " StackTrace:"+ExceptionUtils.populateExecption(cause, 500);
		}
		this.result = new Result(code, msg);
	}
	/**
	 * 构造方法，支持国际化
	 * @param code	返回码
	 * @param key   消息 key
	 * @param msgArgs  消息参数
	 * @param msg	返回消息
	 * @param cause 异常堆栈
	 */
	public BusinessException(int code, String key,String[] msgArgs, Throwable cause) {
		String msg =getMessage(key, msgArgs);
		this.cause = cause;
		
		if(cause != null) {
			if(cause.getCause() != null) {
				msg += " cause:" + ExceptionUtils.populateExecption(cause.getCause(), 500);
			}
			msg += " StackTrace:"+ExceptionUtils.populateExecption(cause, 500);
		}
		this.result = new Result(code, msg);
	}
	/**
	 * 构造方法
	 * @param code	返回码
	 * @param cause	异常堆栈
	 */
	public BusinessException(int code, Throwable cause) {
		super(cause);
		String msg = "";
		
		if(cause != null) {
			if(cause.getCause() != null) {
				msg += " cause:" + ExceptionUtils.populateExecption(cause.getCause(), 500);
			}
			msg += " StackTrace:"+ExceptionUtils.populateExecption(cause, 500);
		}
		this.result = new Result(code, msg);
	}
	/**
	 * 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see java.lang.Throwable#getCause()
	 */
    public synchronized Throwable getCause() {
        return (cause==this ? super.getCause() : cause);
    }	
	/**
	 * 
	 * getMessage:从资源文件获取消息. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * 
	 * @author 颖勤 
	 * @return 
	 * @since JDK 1.6
	 */
	protected static String getMessage(String key,String[] msgArgs){
		return MessageSourceUtils.getMessage(key, msgArgs);
	}
	/**
	 * 返回异常消息
	 * @return 异常消息
	 */
	@Override
	public String getMessage() {
		return ExceptionUtils.buildMessage(super.getMessage(), getCause());
	}
	/**
	 * 异常
	 * @return
	 */
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<exception>");
		
		if (getResult() != null)
			sb.append(result.toString());

		sb.append("<exceptionTrace>");
		sb.append(getMessage());
		sb.append("</exceptionTrace>");
		
		sb.append("<exception/>");
		return sb.toString();
	}

	@Override
	public void printStackTrace(PrintStream ps) {
		ps.print("<exception>");
		if (getResult() != null) {
			ps.print(result.toString());
		} 
		ps.append("<exceptionTrace>");
		
		Throwable cause = getCause();
		if (cause == null) {
			super.printStackTrace(ps);
		} else {
			ps.println(this);
			ps.print("Caused by: ");
			cause.printStackTrace(ps);
		}
		ps.append("</exceptionTrace>");
		ps.println("</exception>");
	}

	@Override
	public void printStackTrace(PrintWriter pw) {
		pw.print("<exception>");
		if (getResult() != null) {
			pw.print(result.toString());
		} 
		pw.append("<exceptionTrace>");
		
		Throwable cause = getCause();
		if (cause == null) {
			super.printStackTrace(pw);
		} else {
			pw.println(this);
			pw.print("Caused by: ");
			cause.printStackTrace(pw);
		}
		pw.append("</exceptionTrace>");
		pw.println("</exception>");
	}

	/**
	 * 返回异常值
	 * @return	异常值对象
	 */
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
