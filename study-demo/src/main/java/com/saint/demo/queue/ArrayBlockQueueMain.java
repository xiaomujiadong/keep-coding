package com.saint.demo.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockQueueMain {
    public static void main(String[] args){
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        Object o = arrayBlockingQueue.poll();
        System.out.println(o);
    }
}
