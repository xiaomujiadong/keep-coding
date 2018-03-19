package com.saint.design.mode.command;

/**
 * 这是命令模式的Main函数，命令模式：根据不同的命令，产生不同的行为。
 * 策略模式：不同的策略实现相同的功能。
 */
public class Main {
    public static void main(String[] args){
        Light light = new Light();

        LightOnCommand lightOnCommand = new LightOnCommand(light);

        lightOnCommand.execute();
    }
}
