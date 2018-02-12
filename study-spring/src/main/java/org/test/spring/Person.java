package org.test.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("person")
public class Person implements IGodSpring {
//
//    @Autowired
//    private Car car;
    private String name;

    public void info(){
        System.out.println("person");
    }
}
