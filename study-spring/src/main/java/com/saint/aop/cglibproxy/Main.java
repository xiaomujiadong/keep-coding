package com.saint.aop.cglibproxy;

public class Main {

    public static void main(String[] args){
        CglibProxy proxy = new CglibProxy();

        RealObject targetObject = (RealObject) proxy.getProxy(RealObject.class);
        targetObject.realDoSomething();

        RealObject2 targetObject2 = (RealObject2) proxy.getProxy(RealObject2.class);
        targetObject2.targetMethod();

        System.out.println(targetObject2.getClass().getName());

    }
}
