package org.test.spring;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class Car implements IGodSpring {

    private String name;
    private String color;

    public void info(){
        System.out.println("car");
    }

    public static void main(String[] args){
        HashMap<String, String> map = new HashMap<>();

        map.put(null, "test");
        System.out.println(map.get(null));
    }
}
