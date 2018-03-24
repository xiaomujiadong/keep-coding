package com.saint.demo.design.model.decorator;

public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "houseblend 咖啡";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
