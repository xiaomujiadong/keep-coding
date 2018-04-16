package com.saint.demo.aqs;

import java.sql.Timestamp;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 有3个人A、B、C，准备吃饭，需要洗完手，拿好碗筷才能吃饭，
 *
 * A B都拿好了，这个时候C才慢悠悠的去洗手、拿碗筷，虽然A很饿，但是还是得等C洗完手、拿好碗筷后
 *
 * 才能吃饭，虽然A很想揍C。 都吃晚饭了，这个时候需要收拾碗筷，A、B、C需要猜拳，决定谁去收拾，
 *
 * 收拾就是指CyclicBarrier去执行barrierAction，
 */
public class CyclicBarrierMain {
    public static void main(String[] args){
        int count = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new BarrierActionThread());
        for(int i=0; i<count; i++){
            new CyclicBarrierThrad(cyclicBarrier).start();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(new Timestamp(System.currentTimeMillis())+" 主线程完成");

//        for(int i=0; i<count; i++){
//            new CyclicBarrierThrad(cyclicBarrier).start();
//        }
//
//        try {
//            cyclicBarrier.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
    }
}

class CyclicBarrierThrad extends Thread{
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierThrad(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run(){
        System.out.println(new Timestamp(System.currentTimeMillis())+" 子线程"+Thread.currentThread().getName()+"正在写入数据。。。");
        try{
            Thread.sleep(5 * 1000);
            System.out.println(new Timestamp(System.currentTimeMillis())+" 子线程"+Thread.currentThread().getName()+"写入数据完成");
            cyclicBarrier.await();
            System.out.println(new Timestamp(System.currentTimeMillis())+" 完成咯。。。"+Thread.currentThread().getName());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

class BarrierActionThread extends Thread{
    @Override
    public void run(){
        System.out.println("栅栏打破了。。。。。。。。。。。。。。");
    }
}