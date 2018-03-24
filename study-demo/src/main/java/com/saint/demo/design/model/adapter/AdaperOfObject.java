package com.saint.demo.design.model.adapter;

/**
 * Created by wdcao on 2018/3/12.
 */
public class AdaperOfObject implements Target{

    private Adaptee adaptee;

    public AdaperOfObject(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void reqeust() {
        System.out.print("这是通过关联形式，实现适配器模式： ");
        adaptee.specificReqeust();
    }
}
