package com.saint.demo.design.model.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu extends MenuComponent {
    private List<MenuComponent> menuComponentList ;
    private String name;
    private String description;

    public Menu(String name, String description){
        this.description = description;
        this.name = name;
        this.menuComponentList = new ArrayList<MenuComponent>();
    }

    String getName(){
        return name;
    }
    String getDescription(){
        return description;
    }
    void print(){
        System.out.print(" "+getName());
        System.out.println("， "+getDescription());
        System.out.println("-------------------------");

        Iterator<MenuComponent> iterator = menuComponentList.iterator();

        while(iterator.hasNext()){
            MenuComponent menuComponent = iterator.next();
            menuComponent.print();
        }
    }
    void add(MenuComponent menuComponent){
        menuComponentList.add(menuComponent);
    }
    void remove(MenuComponent menuComponent){
        menuComponent.remove(menuComponent);
    }
    MenuComponent getChild(int index){
        return menuComponentList.get(index);
    }
}
