package com.saint.aop.staticproxy;

/**
 * Created by wdcao on 2017/11/28.
 */
public class RealSubject implements ISubject{
    public void doSomething() {
        System.out.println("real subject");
    }
}
