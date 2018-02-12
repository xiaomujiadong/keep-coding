package com.saint.aop.staticproxy;

/**
 * Created by wdcao on 2017/11/28.
 */
public class SubjectProxy implements ISubject{

    private ISubject subject = new RealSubject();

    public void doSomething() {
        System.out.println("------------before------------");
        subject.doSomething();
        System.out.println("------------after------------");
    }
}
