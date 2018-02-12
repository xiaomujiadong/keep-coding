package com.saint.aop.springproxy;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 这是spring的aop方式，解耦合
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations ={ "classpath:spring/applicationContext.xml" })
public class TestSpringAOP {

    @Autowired
    private Math math;

    @Test
    public void test(){

//        math.add(1, 2);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        IClass math = ctx.getBean("iClass", IClass.class);
        int n1 = 100, n2 = 5;
        math.add(n1, n2);

        Class clazz = Class.forName("com.saint.aop.Math");

//        Method method = clazz.getMethod()
    }
}

