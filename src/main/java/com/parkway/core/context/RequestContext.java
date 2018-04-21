package com.parkway.core.context;

import java.util.Map;

public class RequestContext {
	
	@SuppressWarnings("rawtypes")
	private static InheritableThreadLocal threadLocal = new InheritableThreadLocal();

	/**
	 * This method retrieves the RequestContext data.
	 * @return context
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static Map get() {
		return (Map)threadLocal.get();
	}

	/**
	 * This method stores the RequestContext data.
	 * @param context
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void set(Map context) {
		threadLocal.set(context);
	}

	/**
	 * This method clears the RequestContext data.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void clearContext() {
		threadLocal.set(null); 
	}
}
