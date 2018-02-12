package com.saint.demo.design.observer;

public class WeiXinUser implements Observer {

    private String name;

    public WeiXinUser(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("observer name : "+name+", message: "+message);
    }
}
