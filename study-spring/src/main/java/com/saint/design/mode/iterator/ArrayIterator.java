package com.saint.design.mode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayIterator implements Iterator<String> {

    private String[] list = new String[]{"test1", "test2", "test3", "test4"};
    private int index = 0;

    @Override
    public boolean hasNext() {
        return index<(list.length-1);
    }

    @Override
    public String next() {
        String value = list[index];
        ++index;
        return value;
    }
}
