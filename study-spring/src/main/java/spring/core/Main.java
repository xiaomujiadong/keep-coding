package spring.core;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args){
        AbstractXmlApplicationContext context = new ClassPathXmlApplicationContext("spring\\applicationContextSpring.xml");
        User user = (User)context.getBean("user");

        user.info();

        System.out.println("testaaaaaa");
    }
}
