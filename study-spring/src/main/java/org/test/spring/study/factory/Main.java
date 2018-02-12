package org.test.spring.study.factory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        Class clazz = Class.forName("com.saint.Test");

//        clazz.co
    }
}
