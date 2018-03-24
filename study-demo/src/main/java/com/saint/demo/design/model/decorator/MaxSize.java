package com.saint.demo.design.model.decorator;

public class MaxSize extends SizeDecorator {

    private Beverage beverage;

    public MaxSize(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return "要了一杯大杯 "+beverage.getDescription();
    }

    @Override
    public double cost() {
        return 0.2+beverage.cost();
    }
}
