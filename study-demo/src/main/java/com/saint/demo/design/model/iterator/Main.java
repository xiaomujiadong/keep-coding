package com.saint.demo.design.model.iterator;

import java.util.Iterator;

public class Main {

    public static void main(String[] args){
        ArrayIterator arrayIterator = new ArrayIterator();
        ArrayListIterator arrayListIterator = new ArrayListIterator();

        printIterator(arrayIterator);
        System.out.println("other iterator");
        printIterator(arrayListIterator);
    }

    public static void printIterator(Iterator<String> iterator){
        while (iterator.hasNext()){
            String iteratorValue = iterator.next();
            System.out.println("iteratorValue: "+iteratorValue);
        }
    }
}
