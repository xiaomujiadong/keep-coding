package com.saint;

import com.alibaba.dubbo.config.annotation.Reference;
import com.saint.entity.User;
import com.saint.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Reference(version = "1.0.0")
    private TestService testService;

    @RequestMapping("hello")
    public String hello() {
        return testService.sayHello("Hello springboot and dubbo!");
    }

    @RequestMapping("user")
    public User user() {
        return testService.findUser();
    }
}
