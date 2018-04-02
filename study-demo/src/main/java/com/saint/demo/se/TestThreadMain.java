package com.saint.demo.se;

/**
 * 本类主要为了测试Thread.join()方法的作用，假设主线程为A，A里面有一个子线程B，并且A中调用了子线程
 * 的join()，此时A会等B执行完成后，才会继续执行。换句话说，就是在将B中的代码放在调用join的后面执行了
 */
public class TestThreadMain {
    public static  void main(String[] args){
        System.out.println("主线程开始");
        long start = System.currentTimeMillis();
        ThreadA threadA = new ThreadA();

        threadA.start();
        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束, 耗时： "+(System.currentTimeMillis()-start));
    }
}

class ThreadA extends Thread{
    public void run(){
        System.out.println("进入线程，线程sleep 5s");
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕");
    }
}
