package com.saint.demo.design.model.decorator;

public class Milk extends CondimentDecorator {

    private Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " 加了 牛奶";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.65;
    }
}
