package com.saint.demo.design.model.decorator;

public class MinSize extends SizeDecorator {

    private Beverage beverage;

    public MinSize(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return "要了一杯小杯 "+beverage.getDescription();
    }

    @Override
    public double cost() {
        return 0.1+beverage.cost();
    }
}
