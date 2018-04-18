package com.saint.demo.thread;

public class ThreadLocalMain {

    public static void main(String[] args){
        System.out.println("tset");
        SonThreadLocal sonThreadLocal = new SonThreadLocal();
        Thread threadLocalThread = new Thread(new ThreadLocalThread(sonThreadLocal));
        threadLocalThread.start();
    }
}
class ThreadLocalThread implements Runnable{
    private SonThreadLocal sonThreadLocal;
    public ThreadLocalThread(SonThreadLocal sonThreadLocal){
        this.sonThreadLocal = sonThreadLocal;
    }
    @Override
    public void run() {
        ThreadLocal<SonThreadLocal> localThreadLocal = new ThreadLocal<>();
        System.out.println("localThreadLocal: "+localThreadLocal);
        localThreadLocal.set(sonThreadLocal);
        System.out.println("sonThreadLocal: "+sonThreadLocal);
        System.out.println("localThreadLocalGet: "+localThreadLocal.get());
        localThreadLocal.remove();
    }
}
class SonThreadLocal{

}