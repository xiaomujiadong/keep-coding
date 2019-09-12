package com.saint.demo.agent;

import java.lang.instrument.Instrumentation;

/**
 * @description:
 * @date: 2019/9/12 15:52
 */
public class MyPreMain {

    /**
     * 在main()方法之前运行，与main()运行在同一个JVM中
     * 并且被同一个System ClassLoader装载
     * 被同一个安全策略(security policy)和上下文（Context）管理
     * @param agentOps
     * @param inst
     */
    public static void premain(String agentOps, Instrumentation inst){
        System.out.println("--------PreMain.java的premain（obj1, obj2）方法执行了");
        System.out.println("--------agentOps: "+agentOps);
    }

    /**
     * 如果不存在premain(String agentOps, Instrumentation inst)
     * 则执行
     * @param agentOps
     */
    public static void premain(String agentOps){
        System.out.println("--------PreMain.java的premain（obj1）方法执行了");
        System.out.println("--------agentOps: "+agentOps);
    }

    public static void main(String[] args){
    }
}
