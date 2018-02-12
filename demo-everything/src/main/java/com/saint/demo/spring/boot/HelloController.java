package com.saint.demo.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/saint")
    public String hello(){
        return "hello world";
    }
}

