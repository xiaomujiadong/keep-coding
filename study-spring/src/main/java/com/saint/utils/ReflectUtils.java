package com.saint.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectUtils {
	
	public static Class getClass(String className) throws ClassNotFoundException{
//		if(!isExists(className))
//			return null;
		
		return Class.forName(className);
	}
	
	public static Object getBeanObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		return getClass(className).newInstance();
	}

	public static boolean isExists(String className){
		String path = className.replace(".", "/")+".java";
		
		System.out.println(path);
		File file = new File(path);
		System.out.println(file.exists());
		return file.exists();
	}
	
	public static void paddingPropertyByField(Object object, Class clazz, Map<String, String> valueMap) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields){
			field.setAccessible(true);
			field.set(object, valueMap.get(field.getName()));
		}
	}

	public static void paddingPropertyByFieldObject(Object object, Class clazz, Map<String, String> valueMap, Map<String, Object> beanObejct) throws IllegalArgumentException, IllegalAccessException{
		Field[] fields = clazz.getDeclaredFields();

		for(Field field : fields){
			field.setAccessible(true);
			String fileAnnotationName = valueMap.get(field.getName());
			Object needWire = beanObejct.get(fileAnnotationName);

			field.set(object, needWire);
		}
	}
	
	public static void paddingPropertyByMethod(Object object, Class clazz, Map<String, String> valueMap) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){
			String methodName = method.getName();
			
			if(methodName.contains("set")){
				String fieldName = methodName.replace("set", "");
				String firstChar = fieldName.substring(0, 1);
				
				String realFieldName = fieldName.replaceFirst(firstChar, firstChar.toLowerCase());
				
				method.invoke(object, valueMap.get(realFieldName));
			}
		}
	}
	
	public static void main(String[] args) {
		String test = "com.cwd.test.TestBean";
		String path = test.replace(".", "/");
		
		
		System.out.println(path+".java");
		File file = new File(path);
		
		System.out.println(file.exists());
		
	}

}
