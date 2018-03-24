package com.saint.demo.design.model.decorator;

public class MoCha extends CondimentDecorator {

    private Beverage beverage;

    public MoCha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+" 加了 摩卡";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }
}
