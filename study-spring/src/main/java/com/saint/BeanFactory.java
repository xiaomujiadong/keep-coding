package com.saint;

import java.util.Map;

import src.main.java.com.saint.utils.ReflectUtils;

public class BeanFactory {

	public static Object getBean(String className, Map<String, String> valueMap) throws Exception{
		Class clazz = ReflectUtils.getClass(className);
		Object beanObject = ReflectUtils.getBeanObject(className);
		
		ReflectUtils.paddingPropertyByField(beanObject, clazz, valueMap);
		
		return beanObject;
	}

	public static Object getBeanObject(String className, Map<String, String> valueMap, Map<String, Object> beanObjectMap) throws Exception{
		Class clazz = ReflectUtils.getClass(className);
		Object beanObject = ReflectUtils.getBeanObject(className);

		ReflectUtils.paddingPropertyByFieldObject(beanObject, clazz, valueMap, beanObjectMap);

		return beanObject;
	}
}
