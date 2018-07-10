package spring.core;

import org.springframework.transaction.annotation.Transactional;

public class User {

    public User(){
        System.out.println("---------------------------------user is creating");
    }

    @Transactional
    public void info(){
        System.out.println("info");
    }
}
