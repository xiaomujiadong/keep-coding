package com.saint.demo.thread;

import java.util.Random;

public class ThreadLocalMain {
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();



    public static void main(String[] args){
        System.out.println("tset");
        for (int i = 0; i < 2; i++) {
            new Thread(new LocalThread(i, x)).start();
        }

    }
}
class LocalThread implements Runnable{
    private int id;
    private ThreadLocal<Integer> threadLocal;
    public LocalThread(int id, ThreadLocal threadLocal){
        this.id = id;
        this.threadLocal = threadLocal;
    }
    @Override
    public void run() {
        int data = new Random().nextInt();
        System.out.println(Thread.currentThread().getName()
                + " has put data :" + data);
        threadLocal.set(data);

        new SonThreadLocal(threadLocal).get();

        if(id==0){
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        new B(threadLocal).get();
    }
}
class SonThreadLocal{
    private ThreadLocal<Integer> threadLocal;

    public SonThreadLocal(ThreadLocal threadLocal){
        this.threadLocal = threadLocal;
    }

    public void get() {
        int data = threadLocal.get();
        System.out.println("A from " + Thread.currentThread().getName()
                + " get data :" + data);
    }

}

/**
 * 获取数据
 *
 * @author cary
 * @date 2015-8-24-下午6:05:44
 * @version 1.0.0
 */
 class B {
    private ThreadLocal<Integer> threadLocal;

    public B(ThreadLocal threadLocal){
        this.threadLocal = threadLocal;
    }
    public void get() {
        int data = threadLocal.get();
        System.out.println("B from " + Thread.currentThread().getName()
                + " get data :" + data);

    }
}