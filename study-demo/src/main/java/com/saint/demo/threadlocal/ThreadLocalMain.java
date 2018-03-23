package com.saint.demo.threadlocal;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wdcao on 2018/3/23.
 */
public class ThreadLocalMain {
    private static final ThreadLocal<String> name = ThreadLocal.withInitial(()->{
        System.out.println("init");
        return null;
    });

    private static String name2;

    public static void main(String[] args) throws InterruptedException {
        int paraller = 100;

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch downLatch = new CountDownLatch(paraller);

        for(int i=0; i<paraller; i++){
            new Thread(()->{
                try{
                    countDownLatch.await();
                    name.set(Thread.currentThread()+" hello");
                    name2 = Thread.currentThread()+" hello";

                    System.out.println(Thread.currentThread() + " " + name.get());;
                    System.out.println(Thread.currentThread() + " " + name2);
                }catch (Exception ex){
                    ex.printStackTrace();
                }finally {
                    name.remove();
                }
            }).start();
        }

        countDownLatch.countDown();
        downLatch.await();
    }
}
