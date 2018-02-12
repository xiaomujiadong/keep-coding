package com.saint.aop.staticproxy;

/**这是静态代理的实现方式，耦合厉害
 * Created by wdcao on 2017/11/28.
 */
public class Main {

    public static void main(String[] args){
        ISubject subject = new SubjectProxy();

        subject.doSomething();
    }
}
