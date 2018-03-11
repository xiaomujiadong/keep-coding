package com.saint.design.mode.adapter;

/**
 * 这是学习适配器设计模式的主函数
 *
 * 适配器模式：
 *      适配器类Adapter
 *      被适配的类Adaptee
 *      目标接口Target
 *      已经实现Target的参照接口ConcreteTarget
 */
public class Main {
    public static void main(String[] args){
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.reqeust();

        Target adapter = new Adapter();
        adapter.reqeust();
    }
}
