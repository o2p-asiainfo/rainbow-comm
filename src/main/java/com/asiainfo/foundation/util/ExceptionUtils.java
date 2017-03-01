package com.asiainfo.foundation.util;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public abstract class ExceptionUtils
{
  public static Throwable resolveActualException(Throwable t)
  {
    Throwable cause = t;
    while (cause != null) {
      if (ObjectUtils.nullSafeClassName(cause).startsWith("com.asiainfo.")) {
        return cause;
      }
      cause = cause.getCause();
    }
    return cause;
  }

  public static void rethrowException(Throwable t) {
    if ((t instanceof RuntimeException)) {
      throw ((RuntimeException)t);
    }

    throw new RuntimeException(t);
  }

  public static String populateExecption(Throwable t)
  {
    return populateExecption(t, 2000);
  }

  public static String populateExecption(Throwable t, int descLimit) {
    if (t != null) {
      String message = "";
      StringWriter writer = null;
      PrintWriter pw = null;
      try{
    	  
    	  writer = new StringWriter();
          pw = new PrintWriter(writer);
          t.printStackTrace(pw);
          message = writer.toString();
         
      } finally  {
    	  if(pw != null) {
    		  pw.close();
    	  }
    	  if(writer != null) {
    		  try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	  }
      }
      return StringUtils.truncateMessage(message, descLimit);
    }
    return null;
  }
	/**
	 * 创建异常堆栈信息
	 * @param message
	 * @param cause
	 * @return
	 */
	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuilder buf = new StringBuilder();
			if (message != null) {
				buf.append(message).append("; ");
			}
			buf.append("nested exception is ").append(cause);
			return buf.toString();
		}
		else {
			return message;
		}
	} 
}
