package com.saint.aop.saintproxy;

import src.main.java.com.saint.annotation.SaintComponent;

@SaintComponent
public class Person implements IGod{
    private String name;
    private int age;

    public void setName(String name){
        System.out.println("设置的名称是： "+name);
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public String info(){
        String info = "name: "+name+", age: "+age;
        System.out.println(info);
        return info;
    }
}
