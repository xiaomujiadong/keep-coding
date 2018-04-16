package com.saint.demo.aqs;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

/**
 * 所谓的闭锁，其实就是一个保安，领导跟保安说必须满3个人你才能开门，当来了第一个人
 *
 * 签到了，保安心理会想还差2个人，当第二个人来，又签到一个，保安心理想，还差一个人，当最后
 *
 * 一个签到了，保安就知道了，人齐了，可以开门放行了。
 */
public class CountDownLatchMain {
    public static void main(String[] args){
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        CountDownLatch startLatch = new CountDownLatch(count);

        for(int i=0; i<count; i++){
            new Thread(new CountDownLatchThread(countDownLatch, startLatch)).start();
            startLatch.countDown();
        }
        try {
            System.out.println(new Timestamp(System.currentTimeMillis())+" 等待3个子线程执行完毕...");
            countDownLatch.await();
            System.out.println(new Timestamp(System.currentTimeMillis())+" 3个子线程已经执行完毕");
            System.out.println(new Timestamp(System.currentTimeMillis())+" 继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class CountDownLatchThread implements Runnable {
    private CountDownLatch countDownLatch;
    private CountDownLatch start;

    public CountDownLatchThread(CountDownLatch countDownLatch, CountDownLatch start){
        this.countDownLatch = countDownLatch;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            start.await();
            System.out.println(new Timestamp(System.currentTimeMillis())+" 子线程"+Thread.currentThread().getName()+"正在执行");

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Timestamp(System.currentTimeMillis())+" 子线程"+Thread.currentThread().getName()+"执行完毕");
        countDownLatch.countDown();
    }
}
