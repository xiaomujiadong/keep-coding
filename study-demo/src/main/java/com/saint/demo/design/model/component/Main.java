package com.saint.demo.design.model.component;

public class Main {

    public static void main(String[] args){
        MenuComponent menuComponent1 = new Menu("第一份菜单", "早餐菜单");
        MenuComponent menuComponent11 = new MenuItem("包子", "属于早餐", true, "1.5");
        MenuComponent menuComponent12 = new MenuItem("豆浆", "属于早餐", true, "1.5");
        menuComponent1.add(menuComponent11);
        menuComponent1.add(menuComponent12);

        MenuComponent menuComponent2 = new Menu("第二份菜单", "午餐菜单");
        MenuComponent menuComponent21 = new MenuItem("面条", "属于午餐", true, "10");
        MenuComponent menuComponent22 = new MenuItem("鸡肉", "属于午餐", true, "15");
        menuComponent2.add(menuComponent21);
        menuComponent2.add(menuComponent22);

        MenuComponent menuComponent3 = new Menu("第三份菜单", "晚餐菜单");
        MenuComponent menuComponent31 = new MenuItem("鱼", "属于晚餐", true, "25");
        MenuComponent menuComponent32 = new MenuItem("米饭", "属于晚餐", true, "1.5");
        menuComponent3.add(menuComponent31);
        menuComponent3.add(menuComponent32);

        MenuComponent allMenu = new Menu("所有的菜单", "组合一起的菜单");

        allMenu.add(menuComponent1);
        allMenu.add(menuComponent2);
        allMenu.add(menuComponent3);

        Waitress waitress = new Waitress(allMenu);

        waitress.print();
    }
}
