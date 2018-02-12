package com.saint.aop.cglibproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class targetClass){
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before targetObject invoke realDoSomething");

        Object result = methodProxy.invokeSuper(o, objects);

        System.out.println("after targetObject invoke realDoSomething");
        return null;
    }
}
