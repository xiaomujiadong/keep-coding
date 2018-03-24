package com.saint.demo.design.model.factory;

public class SaloonCarFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new SaloonCar();
    }
}
