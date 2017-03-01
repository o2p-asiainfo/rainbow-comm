/** 
 * Project Name:rainbow-comm 
 * File Name:MessageSourceUtils.java 
 * Package Name:com.asiainfo.foundation.util 
 * Date:2014年10月27日下午2:22:40 
 * Copyright (c) 2014, www.asiainfo.com All Rights Reserved. 
 * 
*/  
  
package com.asiainfo.foundation.util;  

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.asiainfo.foundation.log.Logger;

/** 
 * ClassName:MessageSourceUtils <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年10月27日 下午2:22:40 <br/> 
 * @author   颖勤 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class MessageSourceUtils {
	private final static Logger log = Logger.getLog(MessageSourceUtils.class);
	private static ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	public static Locale locale = null;
	static {
		setResBundleCfg();
	}
	public static MessageSource getMessageSource(){
		return messageSource;
	} 
	public static String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, locale);
	}
	private static void setResBundleCfg(){
		Properties prop = new Properties();
		String localeVariable = null;
		try {
			prop.load(MessageSourceUtils.class.getClassLoader().getResourceAsStream("META-INF/internal/i18n-resources.properties"));
			String resources = prop.getProperty("o2p.custom.i18n.resources");
			String[] baseNames =  resources.split(",");
			messageSource.setBasenames(baseNames);
			localeVariable = prop.getProperty("i18n.locale");
			if(localeVariable == null) {
				locale = Locale.getDefault();
			} else {
				String[] array = localeVariable.split("_");
				if(array.length == 1) {
					locale = new Locale(array[0]);
				} else if(array.length > 1) {
					locale = new Locale(array[0], array[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Can't find the file {0},and load i18n resources from /messages/messages*.properties, and load the localeVariable {1}", "/META-INF/internal/i18n-resources.properties", localeVariable);
			messageSource.setBasename("messages.messages");
		}
	}
}
