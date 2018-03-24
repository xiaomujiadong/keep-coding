package com.saint.demo.design.model.adapter;

public class AdapterOfExtends extends Adaptee implements Target {
    @Override
    public void reqeust() {
        System.out.print("这是通过继承形式，实现适配器模式： ");
        super.specificReqeust();
    }
}
