package com.saint.demo.design.model.component;

public class MenuItem extends MenuComponent {

    private String name;
    private String description;
    private boolean isVegelarian;
    private String price;

    public MenuItem(String name, String description, boolean isVegelarian, String price){
        this.name = name;
        this.description = description;
        this.isVegelarian = isVegelarian;
        this.price = price;
    }

    String getName(){
        return name;
    }
    String getDescription(){
        return description;
    }
    String getPrice(){
        return price;
    }
    boolean isVegelarian(){
        return isVegelarian;
    }
    void print(){
        System.out.print(" "+getName());
        if(isVegelarian){
            System.out.print("(v)");
        }
        System.out.println(", "+getPrice());
        System.out.println(" --"+getDescription());
    }
}
