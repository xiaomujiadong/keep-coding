package com.saint.aop.saintproxy;

import com.saint.annotation.SaintAop;
import com.saint.annotation.SaintAopAfter;
import com.saint.annotation.SaintAopBefore;
import com.saint.annotation.SaintComponent;

@SaintAop
@SaintComponent
public class AopProxy {

    @SaintAopBefore(classNames = {"com.saint.aop.saintproxy.Person"})
    public void before(){
        System.out.println("这是saint定义的 aop before 方法");
    }

    @SaintAopAfter(classNames = {"com.saint.aop.saintproxy.Person"})
    public void after(){
        System.out.println("这是saint定义的 aop after 方法");
    }
}
