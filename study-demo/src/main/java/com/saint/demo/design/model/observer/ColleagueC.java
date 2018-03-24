package com.saint.demo.design.model.observer;

public class ColleagueC implements Observer {

    private String name;

    public ColleagueC(String name){
        this.name = name;
    }

    @Override
    public void xiaBan() {
        System.out.println("我是同事"+name+"，我约了妹子，所以，嘿嘿嘿");
    }
}
