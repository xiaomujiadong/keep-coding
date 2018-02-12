package com.saint.demo.design.observer;

public class Main {
    public static void main(String[] args){
        Observer observer1 = new WeiXinUser("小明");
        Observer observer2 = new WeiXinUser("老王");

        Subject concreteSubject = new ConcreteSubject();

        concreteSubject.attach(observer1);
        concreteSubject.attach(observer2);

        concreteSubject.notify("弄啥勒");

    }
}
