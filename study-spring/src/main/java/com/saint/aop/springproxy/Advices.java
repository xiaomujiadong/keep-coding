package com.saint.aop.springproxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Advices {

    @Before("execution(* com.saint.aop.springproxy.IClass.*(..))")
    public void before(JoinPoint jp){
        System.out.println("---------------前置通知---------------");
        System.out.println(jp.getSignature().getName());
    }

//    @Before("execution(* com.saint.aop.springproxy.IClass.*(..))")
    public void beforeTwo(JoinPoint jp){
        System.out.println("---------------前置通知 2---------------");
        System.out.println(jp.getSignature().getName());
    }

//    @After("execution(* com.saint.aop.springproxy.IClass.*(..))")
    public void after(JoinPoint jp){
        System.out.println("---------------最终通知---------------");
        System.out.println(jp.getSignature().getName());
    }
}
