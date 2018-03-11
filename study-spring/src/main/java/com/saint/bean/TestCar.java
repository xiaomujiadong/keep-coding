package com.saint.bean;

import com.saint.annotation.SaintAutowire;
import com.saint.annotation.SaintBean;

/**
 * Created by wdcao on 2017/11/21.
 */
@SaintBean(id="testCar")
public class TestCar {
    @SaintAutowire(id="testBean")
    private TestBean testBean;
    private String name;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String info(){
        String info = "carInfo: name: "+name+", color: "+color;
        System.out.println(info);
        return info;
    }
}
