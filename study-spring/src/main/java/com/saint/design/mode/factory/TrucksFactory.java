package com.saint.design.mode.factory;

public class TrucksFactory extends CarFactory{
    @Override
    public Car createCar() {
        return new TrucksCar();
    }
}
