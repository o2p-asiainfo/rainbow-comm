package com.linkage.rainbow.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonUtils {
	private static Log log = LogFactory.getLog(CommonUtils.class);
	/**
	 * 等待时间，毫秒为单位
	 * @param millseconds
	 */
	public static void waitTime(int millseconds) {
		try {
			Thread.sleep(millseconds);
		} catch (InterruptedException e) {
			log.error(e.getStackTrace());
		}
	}
}
