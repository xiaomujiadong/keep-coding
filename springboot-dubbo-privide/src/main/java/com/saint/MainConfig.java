package com.saint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by wdcao on 2018/3/6.
 */
@SpringBootApplication
public class MainConfig {
    public static void main(String[] args){
        SpringApplication.run(MainConfig.class, args);

        try{
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
