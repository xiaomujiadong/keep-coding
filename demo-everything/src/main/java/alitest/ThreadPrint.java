package alitest;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPrint implements Runnable {
    //线程名字
    private String threadName;

    private int count;

    private int maxNumber;

    private AtomicInteger lockObj;

    public ThreadPrint(String threadName, AtomicInteger lockObj, int count, int maxNumber){
        this.threadName = threadName;
        this.count = count;
        this.maxNumber = maxNumber;
        this.lockObj = lockObj;
    }

    @Override
    public void run() {
        synchronized (lockObj){
            for(int i=1; ; i++){
                //通知所有正在等待的线程
                lockObj.notifyAll();

                if(lockObj.get() < (maxNumber - 1)){
                    lockObj.addAndGet(1);
                }else{
                    lockObj.set(0);
                }
//                System.out.print("threadName: "+Thread.currentThread().getName()+", value ");
                System.out.print(lockObj.get());
//                System.out.println();
                try {
                    if(i<count){
                        //设置超时时间，防止无限等待
                        lockObj.wait(1);
                    }else{
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
