package com.saint.demo.executor;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> result = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("test inner run");
            }
        }, 12);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
