package com.saint.demo.design.model.chain;

/**
 * Created by wdcao on 2018/4/25.
 */
public class FileLogger extends AbstractLogger {
    public FileLogger(int level){
        this.level = level;
    }

    @Override
    public void write(String message) {
        System.out.println("File Console::Logger: " + message);
    }
}
