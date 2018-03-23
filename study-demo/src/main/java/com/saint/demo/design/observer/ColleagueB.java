package com.saint.demo.design.observer;

public class ColleagueB implements Observer {

    private String name;

    public ColleagueB(String name){
        this.name = name;
    }

    @Override
    public void xiaBan() {
        System.out.println("我是同事"+name+"，饿死了，我要去吃饭！！！！");
    }
}
