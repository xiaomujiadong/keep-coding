package com.saint;

import java.util.List;
import java.util.Map;

import src.main.java.com.saint.annotation.AnnotationUtil;
import src.main.java.com.saint.aop.saintproxy.IGod;
import src.main.java.com.saint.dependency.Injection;
import src.main.java.com.saint.ioc.IocFactory;
import src.main.java.com.saint.resource.ClasspathPackageScanner;

/**
 * Hello world!
 *
 */
public class Main{

	public static void main(String[] args) throws Exception {
		String packageName = "com.saint.aop";
		ClasspathPackageScanner classpathPackageScanner = new ClasspathPackageScanner(packageName);

		List<Class> classList = classpathPackageScanner.getFullyQualifiedClass();

		IocFactory iocFactory = new IocFactory();
		//获取有注解的class文件
		classList = AnnotationUtil.getHasAnnotationClass(classList);

		iocFactory.findAopClass(classList);

		//创建了packageName下所有类的对象，存放在beanMap中
		iocFactory.createBean(classList);

		Map<String, Object> beanMap = iocFactory.getBeanMap();
		System.out.println("beanMap size: "+beanMap.keySet().size());
		Injection injection = new Injection();
		for(String key : beanMap.keySet() ){
			Object object = beanMap.get(key);

			injection.paddingField(beanMap, iocFactory.getAnnotationBeanMap(), object);

//			System.out.println("after padding: "+object+", className: "+object.getClass().getName());
		}

		Map<String, Object> beanOfTypeMap = iocFactory.beanOfTypeMap;

		IGod person = (IGod) beanOfTypeMap.get("com.saint.aop.saintproxy.Person");

		person.setName("saint");

	}
}
