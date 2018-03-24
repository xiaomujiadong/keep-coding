package com.saint.demo.design.model.decorator;

public class MidSize extends SizeDecorator {

    private Beverage beverage;

    public MidSize(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return "要了一杯中杯 "+beverage.getDescription();
    }

    @Override
    public double cost() {
        return 0.15+beverage.cost();
    }
}
