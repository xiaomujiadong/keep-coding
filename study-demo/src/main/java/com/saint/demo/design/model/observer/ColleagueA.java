package com.saint.demo.design.model.observer;

public class ColleagueA implements Observer {

    private String name;

    public ColleagueA(String name){
        this.name = name;
    }

    @Override
    public void xiaBan() {
        System.out.println("我是同事"+name+"，你们下班，我加班，呵呵");
    }
}
