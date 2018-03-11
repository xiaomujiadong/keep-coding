package com.saint.service.impl;

import com.saint.entity.User;
import com.saint.service.TestService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String name) {
        System.out.println("provider invoder sayHello");
        return "provider: "+new Timestamp(System.currentTimeMillis()).toString() + name + " say hello";
    }

    @Override
    public User findUser() {
        User user = new User();
        user.setId(1001);
        user.setName("scott");
        user.setPassword("tiger");
        user.setAge(20);
        user.setSex(0);
        return user;
    }
}
