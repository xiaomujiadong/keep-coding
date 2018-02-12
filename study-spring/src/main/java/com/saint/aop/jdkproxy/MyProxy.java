package com.saint.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy implements InvocationHandler{

    private Object target;

    public MyProxy(Object target){
        super();
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke target method");
        Object result = method.invoke(target, args);
        System.out.println("after invoke target method");
        return result;
    }

    public Object getTarget() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}
