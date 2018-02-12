package com.saint.aop.springproxy;

import org.springframework.stereotype.Service;

@Service
public class Math implements IClass{

    public int add(int a, int b){
        int r = a + b;
        System.out.println("a + b = "+r);
        return r;
    }

    public int sub(int a, int b){
        int r = a - b;
        System.out.println("a - b = "+r);
        return r;
    }

    public int mut(int a, int b){
        int r = a * b;
        System.out.println("a * b = "+r);
        return r;
    }

    public int div(int a, int b){
        int r = a / b;
        System.out.println("a / b = "+r);
        return r;
    }
}
