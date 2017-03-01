package com.asiainfo.foundation.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.asiainfo.foundation.log.Logger;

/**
 * 
 * @author 颖勤
 *
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;

	private static Logger log = Logger.getLog(SpringContextHolder.class);

	/*
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中.
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		log.debug("Inject ApplicationContext to SpringContextHolder:" + applicationContext);

		if (SpringContextHolder.applicationContext != null) {
			log.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
							+ SpringContextHolder.applicationContext);
		}

		SpringContextHolder.applicationContext = applicationContext;
	}

	/*
	 * 实现DisposableBean接口,在Context关闭时清理静态变量.
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	public void destroy() throws Exception {
		SpringContextHolder.clear();
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	public static boolean containsBean(String id) {
		assertContextInjected();
		return applicationContext.containsBean(id);
	}

	/**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 
	 * @param <T>
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

/*	*//**
	 * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 *//*
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}*/

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	public static void clear() {
		log.debug("Clear ApplicationContext of  SpringContextHolder:" + applicationContext);
		applicationContext = null;
	}

	/**
	 * 检查ApplicationContext不为空.
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}
}

