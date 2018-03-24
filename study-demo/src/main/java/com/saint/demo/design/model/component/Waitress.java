package com.saint.demo.design.model.component;

public class Waitress {
    MenuComponent menuComponent;

    public Waitress(MenuComponent menuComponent){
        this.menuComponent = menuComponent;
    }

    public void print(){
        menuComponent.print();
    }
}
