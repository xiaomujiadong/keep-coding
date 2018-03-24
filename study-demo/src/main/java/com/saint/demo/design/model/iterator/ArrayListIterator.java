package com.saint.demo.design.model.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListIterator implements Iterator<String> {

    private List<String> list = new ArrayList<>();
    private int index;

    public ArrayListIterator(){
        list.add("arrayIterator1");
        list.add("arrayIterator2");
        list.add("arrayIterator3");
        list.add("arrayIterator4");
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index<(list.size()-1);
    }

    @Override
    public String next() {
        String value = list.get(index);
        ++index;
        return value;
    }
}
