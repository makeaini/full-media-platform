package com.inred.media.util;



import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring获取spring上下文对象工具
 * 
 * 
 */
public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 根据bean名称获取bean对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	/**
	 * 根据bean名称获取bean对象
	 * 
	 * @param beanName
	 * @return
	 */
	public static <T> T getBean(Class<T> beanClass) {
		return applicationContext.getBean(beanClass);
	}

	/**
	 * 获取spring 上下文对象
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
