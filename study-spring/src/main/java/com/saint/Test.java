package com.saint;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static  void main(String[] args) throws ClassNotFoundException{
//		TestBean testBean = new TestBean();
//
//		List<Class<?>> list = getClasses("cwd.ioc.bean");
//
//		for(Class clazz : list){
//
//			SaintBean cwdBean = (SaintBean)clazz.getAnnotation(SaintBean.class);
//
//
//			System.out.println(clazz.getName());
//		}

        Integer x = 6;
        int y= 6;
        System.out.println(x == y);
    }
	
	private static List<Class<?>> getClasses(String packageName) throws ClassNotFoundException{
        String path = packageName.replace('.', '/');
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), packageName);
    }
	
	private static List<Class<?>> getClasses(File dir, String packageName) throws ClassNotFoundException{

        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (dir == null || !dir.exists() || dir.listFiles() == null) {
            return classes;
        }

        for (File f : dir.listFiles()) {
            if (f.isDirectory()) {
                classes.addAll(getClasses(f, packageName + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                classes.add(Class.forName(packageName + "." + name.substring(0, name.length() - 6)));
            }
        }
        return classes;
    }
}
