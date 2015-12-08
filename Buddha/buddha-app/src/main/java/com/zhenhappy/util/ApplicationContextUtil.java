package com.zhenhappy.util;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author  苏志锋
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	public static <T> T getBean(Class<T> clasz){
		return  context.getBean(clasz);
	}
}
