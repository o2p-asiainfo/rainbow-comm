package com.linkage.rainbow.util.spring;

public class SpringBeanInvoker {
	public static Object getBean(String beanName){
		return ContextHolder.getSpringContext().getBean(beanName);
	}
}
