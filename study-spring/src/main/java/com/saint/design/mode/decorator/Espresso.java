package com.saint.design.mode.decorator;

public class Espresso extends Beverage {

    public Espresso(){
        this.description = "浓缩咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
