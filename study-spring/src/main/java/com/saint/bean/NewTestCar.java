package com.saint.bean;

import com.saint.annotation.SaintBean;

@SaintBean(id="newTestCar")
public class NewTestCar implements ICar {

    @Override
    public String toString(){
        String desc = "the car is newTestCar";
        System.out.println(desc);
        return desc;
    }
}
