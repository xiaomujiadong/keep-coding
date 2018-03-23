package alitest;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请编写一个Java类，启动N个线程，各线程依次打印数字0到N-1,共打印M遍，要求打印的字符有序，M，N由终端输入，其中M、N的范围在 2～9 之间
 * 例：
 * 输入: 5 8
 * 输出：
 * 0123456701234567012345670123456701234567
 */
public class ThreadPrintMain {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        //总共输出遍数
        int count = 0;
        for(;;){
            try{
                System.out.print("请输入打印的遍数：");
                count = Integer.valueOf(console.next());
                if(2 <= count && count <= 9){
                    break;
                }else{
                    System.out.print("值在2~9之间");
                }
            }catch (Exception ex){
                System.out.print("输入的值必须是数字");
            }
        }

        //线程数
        int threadCount = 0;
        for(;;){
            try{
                System.out.print("请输入启动的线程数：");
                threadCount = Integer.valueOf(console.next());
                if(2 <= threadCount && threadCount <= 9){
                    break;
                }else {
                    System.out.print("值在2~9之间");
                }
            }catch (Exception ex){
                System.out.print("输入的值必须是数字");
            }
        }

        //作为所有线程的锁，确保一次只能有一个线程进行打印，同时作为所有线程一个计数器，记录当前线程打印的值
        AtomicInteger lockObj = new AtomicInteger(-1);
        for(int i=0; i<threadCount; i++){
            new Thread(new ThreadPrint("name"+i, lockObj,count, threadCount)).start();
        }
    }
}
