package com.saint.ioc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.saint.annotation.SaintAop;
import com.saint.annotation.SaintAopAfter;
import com.saint.annotation.SaintAopBefore;
import com.saint.annotation.SaintBean;
import com.saint.annotation.SaintComponent;
import com.saint.aop.saintproxy.SaintProxy;

/**实现控制反转的类
 * Created by wdcao on 2017/11/23.
 */
public class IocFactory {
    /**
     * 用来存放通过反射创建的对象，key为className,value: class的object，
     */
    public static Map<String, Object> beanOfTypeMap = new HashMap<String, Object>();

    /**
     * 用来存放通过反射创建的对象，key为注解上的值，如果注解上未给定值，则不保存,value: class的object，
     */
    private Map<String, Object> annotationBeanMap = new HashMap<String, Object>();

    /**
     *记录委托类，即类似 com.saint.aop.jdkproxy.MyProxy 类
     */
    public static Map<String, Class> aopProxyMap = new HashMap<String, Class>();

    /**
     * 存的值如： aopProxy:<注解名称:方法名称列表>
     */
    public static Map<String, Map<String, List<Method>>> aopProxyMethodMap = new HashMap<String, Map<String, List<Method>>>();

    public static Map<String, Map<String, Class>> saintBeforeMap = new HashMap<String, Map<String, Class>>();

    public static Map<String, Map<String, Class>> saintAfterMap = new HashMap<String, Map<String, Class>>();

    public static Map<String, Set<String>> aopRealClassMap = new HashMap<String, Set<String>>();

    public void createBean(List<Class> clazzList) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(clazzList == null || clazzList.size() <= 0)
            return;

        for(Class clazz : clazzList){

            if(isProxy(clazz)){
                createBeanByJdkProxy(clazz);
            }else{
                createBean(clazz);
            }
        }
    }

    public boolean isProxy(Class clazz){
        String className = clazz.getName();
        for(String key : aopRealClassMap.keySet()){
            Set<String> classSet = aopRealClassMap.get(key);
            if(classSet.contains(className))
                return true;
        }

        return false;
    }

    private void createBean(Class clazz) throws IllegalAccessException, InstantiationException {
        Object object = clazz.newInstance();

        beanOfTypeMap.put(clazz.getName(), object);

        //将Bean实现的接口通过bean放入在map里面
        Class[] beanInterfaces = clazz.getInterfaces();
        if(beanInterfaces!=null){
            for(Class interfaceClazz : beanInterfaces){
                beanOfTypeMap.put(interfaceClazz.getName(), object);
            }
        }

        SaintComponent saintComponent = (SaintComponent)clazz.getAnnotation(SaintComponent.class);

        if(saintComponent != null && saintComponent.id() != null && !saintComponent.id().equals("")){
            beanOfTypeMap.put(saintComponent.id(), object);
        }else {
            SaintBean saintBean = (SaintBean)clazz.getAnnotation(SaintBean.class);

            if(saintBean != null && saintBean.id() != null && !saintBean.id().equals("")){
                annotationBeanMap.put(saintBean.id(), object);
            }
        }
    }

    public Map<String, Object> getBeanMap(){
        return beanOfTypeMap;
    }

    public Map<String, Object> getAnnotationBeanMap(){
        return annotationBeanMap;
    }

    public void createBeanByJdkProxy(Class clazz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        SaintProxy saintProxy = new SaintProxy(clazz.newInstance());

        beanOfTypeMap.put(clazz.getName(), saintProxy.getProxy());
    }

    /**
     * 找到被SaintAop注解的类
     * @param classList 扫描出来的类
     * @throws ClassNotFoundException
     */
    public void findAopClass(List<Class> classList) throws ClassNotFoundException {
        if(classList != null){
            for(Class clazz : classList){
                SaintAop saintAop = (SaintAop)clazz.getAnnotation(SaintAop.class);

                if(saintAop != null){
                    aopProxyMap.put(clazz.getName(), clazz);

                    Method[] methods = clazz.getMethods();

                    if(methods != null){
                        Map<String, List<Method>> methodMap = aopProxyMethodMap.get(clazz.getName());
                        for(Method method : methods){


                            findContainsSaintAopBeforeMethod(clazz, method);
                            findContainsSaintAopAfterMethod(clazz, method);
                        }
                    }
                }
            }
        }
    }

    /**
     * 找到有SaintAopBeforez注解的方法， 获取注解上的class
     * @param method
     * @throws ClassNotFoundException
     */
    private void findContainsSaintAopBeforeMethod(Class proxyClass, Method method) throws ClassNotFoundException {
        SaintAopBefore before = (SaintAopBefore) method.getDeclaredAnnotation(SaintAopBefore.class);

        if(before != null){
            String[] classNames = before.classNames();

            if(classNames != null){

                Map<String, Class> classMap = saintBeforeMap.get(proxyClass.getName());
                if(null == classMap){
                    classMap = new HashMap<String, Class>();
                    saintBeforeMap.put(proxyClass.getName(), classMap);
                }

                for(String className : classNames){
                    Class clazz = Class.forName(className);

                    classMap.put(className, clazz);

                    Set<String> classNameSet = aopRealClassMap.get(proxyClass.getName());
                    if(classNameSet == null){
                        classNameSet = new HashSet<String>();
                        aopRealClassMap.put(proxyClass.getName(), classNameSet);
                    }

                    classNameSet.add(className);
                }
            }
        }
    }

    /**
     * 找到有SaintAopAfter修改的方法
     * @param method
     */

    private void findContainsSaintAopAfterMethod(Class proxyClass, Method method) throws ClassNotFoundException {
        SaintAopAfter aopAfter = (SaintAopAfter) method.getDeclaredAnnotation(SaintAopAfter.class);

        if(aopAfter != null) {
            String[] classNames = aopAfter.classNames();

            if (classNames != null) {

                Map<String, Class> classMap = saintAfterMap.get(proxyClass.getName());
                if (null == classMap) {
                    classMap = new HashMap<String, Class>();
                    saintAfterMap.put(proxyClass.getName(), classMap);
                }

                for (String className : classNames) {
                    Class clazz = Class.forName(className);

                    classMap.put(className, clazz);

                    Set<String> classNameSet = aopRealClassMap.get(proxyClass.getName());
                    if (classNameSet == null) {
                        classNameSet = new HashSet<String>();
                        aopRealClassMap.put(proxyClass.getName(), classNameSet);
                    }

                    classNameSet.add(className);
                }
            }
        }
    }
}
