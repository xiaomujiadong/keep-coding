package com.saint.demo.design.model.component;

public abstract class MenuComponent {
    String getName(){
        throw  new UnsupportedOperationException();
    }
    String getDescription(){
        throw  new UnsupportedOperationException();
    }
    String getPrice(){
        throw  new UnsupportedOperationException();
    }
    boolean isVegelarian(){
        throw  new UnsupportedOperationException();
    }
    void print(){
        throw  new UnsupportedOperationException();
    }
    void add(MenuComponent menuComponent){
        throw  new UnsupportedOperationException();
    }
    void remove(MenuComponent menuComponent){
        throw  new UnsupportedOperationException();
    }
    MenuComponent getChild(int index){
        throw  new UnsupportedOperationException();
    }
}
