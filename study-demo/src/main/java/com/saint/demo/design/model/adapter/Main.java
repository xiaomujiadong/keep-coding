package com.saint.demo.design.model.adapter;

/**
 * 这是学习适配器设计模式的主函数
 *
 * 适配器模式：
 *      适配器类Adapter
 *      被适配的类Adaptee
 *      目标接口Target
 *      已经实现Target的参照接口ConcreteTarget
 *
 * 有2中方式实现适配器模式：
 *      通过继承实现适配器模式（不推荐使用）
 *      通过关联实现适配器模式
 */
public class Main {
    public static void main(String[] args){
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.reqeust();

        //这是继承的适配器模式
        Target adapter = new AdapterOfExtends();
        adapter.reqeust();

        //这是关联的适配器模式
        Adaptee adaptee = new Adaptee();
        AdaperOfObject adaperOfObject = new AdaperOfObject(adaptee);
        adaperOfObject.reqeust();
    }
}
