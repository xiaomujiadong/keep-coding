package com.saint.demo.design.model.chain;

/**
 * Created by wdcao on 2018/4/25.
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    public void write(String message) {
        System.out.println("Console Console::Logger: " + message);
    }
}
