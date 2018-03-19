package com.saint.design.mode.factory;

public class SaloonCarFactory extends CarFactory {
    @Override
    public Car createCar() {
        return new SaloonCar();
    }
}
