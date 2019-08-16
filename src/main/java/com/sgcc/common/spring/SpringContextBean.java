package com.sgcc.common.spring;


import org.springframework.context.ApplicationContext;

/**
 * 利用Service ID从Spring中得到Service对象
 */
public class SpringContextBean {

	private static ApplicationContext ctx = null;

	private SpringContextBean() {
		super();
	}

	/**
	 * 获取spring 管理的bean对象
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

	/**
	 * 设置application context
	 * @param context
	 */
	public static void setApplicationContext(ApplicationContext context) {
		ctx = context;
	}

	public static ApplicationContext getAc() {
		return ctx;
	}
	
}
