package com.saint.demo.design.model.share;


import java.util.Hashtable;

/**
 * Created by wdcao on 2018/4/3.
 */
public class IgoChessmanFactory {
    private static IgoChessmanFactory instance = new IgoChessmanFactory();
    private static Hashtable hashTable;

    private IgoChessmanFactory(){
        hashTable = new Hashtable();
        IgoChessman black = new BlackIgoChessman();
        IgoChessman white = new WhiteIgoChessman();
        hashTable.put("b", black);
        hashTable.put("w", white);
    }

    public static IgoChessmanFactory getInstance(){
        return instance;
    }

    public IgoChessman getIgoChessman(String color){
        return (IgoChessman)hashTable.get(color);
    }
}
