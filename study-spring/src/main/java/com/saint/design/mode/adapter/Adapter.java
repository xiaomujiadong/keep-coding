package com.saint.design.mode.adapter;

public class Adapter extends Adaptee implements Target {
    @Override
    public void reqeust() {
        super.specificReqeust();
    }
}
