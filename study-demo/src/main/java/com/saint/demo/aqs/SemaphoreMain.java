package com.saint.demo.aqs;

import java.sql.Timestamp;
import java.util.concurrent.Semaphore;

public class SemaphoreMain {
    public static void main(String[] args){
        int count = 5;
        Semaphore semaphore = new Semaphore(2);
        for(int i=0; i<count; i++){
            new SemaphoreThread(semaphore).start();
        }
        System.out.println(new Timestamp(System.currentTimeMillis())+" 主线程完成了");
    }
}
class SemaphoreThread extends Thread{
    private Semaphore semaphore;

    public SemaphoreThread(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        try {
            System.out.println(new Timestamp(System.currentTimeMillis())+" 你给不给我资源，不给就抢了。。");
            semaphore.acquire();
            System.out.println(new Timestamp(System.currentTimeMillis())+" 获取资源成功，开始啦...");
            Thread.sleep(6 * 1000);
            System.out.println(new Timestamp(System.currentTimeMillis())+" 用完了给你了.");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}