package com.saint.aop.saintproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;

import com.saint.ioc.IocFactory;

public class SaintProxy implements InvocationHandler {
    private Object target;

    public SaintProxy(Object target){
        super();
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取被aop注解的类集合
        Map<String, Class> aopProxyMap = IocFactory.aopProxyMap;

        //获取被aop注解的类，与需要使用AOP功能的类的关系集合
        Map<String, Set<String>> aopClassMap = IocFactory.aopRealClassMap;

        String aopClassName = null;
        for(String key : aopProxyMap.keySet()){
            String aopClassNameTemp = aopProxyMap.get(key).getName();

            Set<String> classNameSet = aopClassMap.get(aopClassNameTemp);
            String proxyClassName = proxy.getClass().getName();

            Class[] interfaces = proxy.getClass().getInterfaces();

            for(Class clazz : interfaces){
                if(proxy instanceof IGod){
                    aopClassName = aopClassNameTemp;
                    break;
                }
            }
        }
        Object result = null;
        if(aopClassName != null){
            Class aopClass  = Class.forName(aopClassName);



            Method beforeMethod = aopClass.getMethod("before");
            beforeMethod.invoke(aopClass.newInstance());

            result = method.invoke(target, args);

            Method afterMethod = aopClass.getMethod("after");
            afterMethod.invoke(aopClass.newInstance());
        }else{
            System.out.println("before invoke target method");
            result = method.invoke(target, args);
            System.out.println("after invoke target method");
        }

        return result;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}
