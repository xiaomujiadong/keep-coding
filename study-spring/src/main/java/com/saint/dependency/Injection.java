package com.saint.dependency;

import java.lang.reflect.Field;
import java.util.Map;

import src.main.java.com.saint.annotation.SaintAutowire;

/**实现依赖注入的类
 * Created by wdcao on 2017/11/23.
 */
public class Injection {
    public void paddingField(Map<String, Object> beanMap, Map<String, Object> annotationBeanMap, Object object) throws IllegalAccessException, ClassNotFoundException {
        Class clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields){
            String fieldClassName = field.getType().getName();
            String beanClassName = null;
            if(!beanMap.containsKey(fieldClassName) && !annotationBeanMap.containsKey(fieldClassName)){
                beanClassName = getBeanIdBySuperClass(field.getType(), beanMap);

                if(null == beanClassName)
                    continue;
            }

            SaintAutowire saintAutowire = (SaintAutowire)field.getAnnotation(SaintAutowire.class);

            field.setAccessible(true);
            if(saintAutowire != null && saintAutowire.id() != null && !saintAutowire.id().equals("")){
                String beanId = saintAutowire.id();
                field.set(object, annotationBeanMap.get(beanId));
            }else{
                String beanId = field.getType().getName();

                Object beanObject = beanMap.get(beanId);

                if(beanObject != null){
                    field.set(object, beanObject);
                }else{
                    field.set(object, beanMap.get(beanClassName));
                }

            }
        }
    }

    /**
     * 类中的属性类型可能是一个接口
     * @param clazz
     * @param beanMap
     * @return
     */
    public String getBeanIdBySuperClass(Class clazz, Map<String, Object> beanMap) throws ClassNotFoundException {
        for(String key : beanMap.keySet()){
            Class beanClazz = Class.forName(key);

            Class[] interfaces = beanClazz.getInterfaces();

            if(interfaces != null){
                for(Class interfaceClazz : interfaces){
                    if(clazz == interfaceClazz){
                        return beanClazz.getName();
                    }
                }
            }
        }
        return null;
    }

    public boolean isSameClassType(Class superClass, Class clazz){
        if(clazz == Object.class){
            return false;
        }else{
            if(superClass == clazz)
                return true;
            return isSameClassType(superClass, clazz.getSuperclass());
        }
    }

    public static void main(String[] args){
        boolean s = false;

    }
}
