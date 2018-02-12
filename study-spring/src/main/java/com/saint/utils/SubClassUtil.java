package com.saint.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * SubClassUtil 类是�?个工具类，可以获取与接口在同�?个包中的�?有子类实�?
 *
 */
public class SubClassUtil 
{
	/**
     *获取跟入参同�?个包下的�?有cls的子类实�?
     * @param cls
     * @return cls�?有的子类实现的类
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (Class<?> c : getClasses(cls)) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    private static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException{
        String packageName = cls.getPackage().getName();

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
