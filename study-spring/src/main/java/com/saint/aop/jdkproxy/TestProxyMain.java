package com.saint.aop.jdkproxy;

import org.junit.Test;

/**
 * 这是jdk的动态代理
 */
public class TestProxyMain {

    @Test
    public void testProxy(){
        IUserService userService = new UserServiceImpl();

        MyProxy myProxy = new MyProxy(userService);

        IUserService proxy = (IUserService)myProxy.getTarget();

        proxy.add();
    }

    @Test
    public void testGenerateProxyClass(){
        ProxyGeneratorUtils.writeProxyClassToHardDisk("D:\\$Proxy11.class");
    }

    public static void main(String[ ]args){
//        IUserService userService = new UserServiceImpl();
//
//        MyProxy myProxy = new MyProxy(userService);
//
//        IUserService proxy = (IUserService)myProxy.getTarget();
//
//        proxy.add();

        ISubject subject = new RealSubject();

        MyProxy myProxy = new MyProxy(subject);

        ISubject proxy = (ISubject)myProxy.getTarget();

        proxy.doSomething();
    }
}
