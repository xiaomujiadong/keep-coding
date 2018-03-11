package com.saint.design.mode.adapter;

public class Main {
    public static void main(String[] args){
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.reqeust();

        Target adapter = new Adapter();
        adapter.reqeust();
    }
}
