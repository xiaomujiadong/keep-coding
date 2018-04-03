package com.saint.demo.design.model.share;

/**
 * Created by wdcao on 2018/4/3.
 */
public abstract class IgoChessman {
    abstract String getColor();

    public void display(Position position){
        System.out.println("棋子的颜色是: "+getColor()+", 棋子位置，x: "+position.getX()+", Y: "+position.getY());
    }
}
