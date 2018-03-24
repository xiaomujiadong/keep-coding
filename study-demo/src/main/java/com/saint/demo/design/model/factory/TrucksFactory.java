package com.saint.demo.design.model.factory;

public class TrucksFactory extends CarFactory{
    @Override
    public Car createCar() {
        return new TrucksCar();
    }
}
