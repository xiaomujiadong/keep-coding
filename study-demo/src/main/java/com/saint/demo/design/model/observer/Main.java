package com.saint.demo.design.model.observer;

/**
 * 观察者模式（发布-订阅模式）
 * 使用场景： 根据一个通知，会有多个操作
 *
 * 例如：
 *     下班这个行为，
 *     A：因为事情多，需要加班，所以他这个时候是不做操作
 *     B：饿了，需要吃饭，所以这个时候，他去 吃饭了
 *     C：约了妹子吃饭，所以下班了
 *
 *  传统做法是：调用下班方法 xiaBan(){
 *      a.jiaBan();
 *      b.chiFan();
 *      c.xiaBan();
 *  }
 *  如果逻辑不变，这样实现没有问题，假设这个时候来了个同事D，D下班了，去了喝水
 *  这个时候就需要改xiaBan方法了，违反了软件设计模式： 对修改关闭，对扩展打开
 *
 *  如果使用观察者模式：
 *      主题：下班
 *      观察者A、B、C,3个类都实现了额一个接口Observer,他们是观察者，观察主题，单有下班主题是，就会在由主题去触发
 *      各个观察者对象的xiaBan方法。
 *
 *      假设来了一个D，D在下班时喝水，那么久可以直接新增一个类就好了，之前的逻辑也不需要修改
 *
 *  优点：
 *      下班这个动作跟各个同事解耦合，即：观察者和被观察者耦合了，接口耦合
 *      支持广播通讯，即：与每一个注册的观察者进行通讯
 *  缺点：
 *     时效不能保证
 *     各个被观察者之间有循环依赖，会触发循环调用，导致系统崩溃
 *
 *   应用场景：
 *      对一个对象状态的更新，需要其他对象同步更新，而且其他对象的数量动态可变。
 *      对象仅需要将自己的更新通知给其他对象而不需要知道其他对象的细节。
 */
public class Main {
    public static void main(String[] args){
        Observer observer1 = new ColleagueA("小明");
        Observer observer2 = new ColleagueB("老王");
        Observer observer3 = new ColleagueC("小黑");

        Subject concreteSubject = new ConcreteSubject();

        concreteSubject.attach(observer1);
        concreteSubject.attach(observer2);
        concreteSubject.attach(observer3);

        concreteSubject.notify("弄啥勒");

    }
}
