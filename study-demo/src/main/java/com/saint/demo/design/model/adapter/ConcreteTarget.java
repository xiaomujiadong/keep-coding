package com.saint.demo.design.model.adapter;

public class ConcreteTarget implements Target {
    @Override
    public void reqeust() {
        System.out.println("这是普通的类");
    }
}
