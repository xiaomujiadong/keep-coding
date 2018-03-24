package com.saint.demo.design.model.factory;

public class Main {

    public static void main(String[] args){
        CarFactory saloonCarFactory = new SaloonCarFactory();
        CarFactory trucksFactory = new TrucksFactory();

        Car salonCar = saloonCarFactory.createCar();
        Car trucksCar = trucksFactory.createCar();

        System.out.println("第一种车名是: "+salonCar.getName());
        System.out.println("第二种车名是： " + trucksCar.getName());
    }
}
