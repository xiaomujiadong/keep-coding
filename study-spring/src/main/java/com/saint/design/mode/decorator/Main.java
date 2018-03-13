package com.saint.design.mode.decorator;


/**
 * 装饰模式：
 *
 */
public class Main {

    public static void main(String[] args){
        Beverage espresso = new Espresso();
        espresso = new MinSize(espresso);
        espresso = new MoCha(espresso);
        espresso = new Milk(espresso);
        System.out.println("description: "+ espresso.getDescription());

        System.out.println("cost: "+espresso.cost());
    }
}
