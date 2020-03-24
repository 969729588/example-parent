package com.milepost.exampleUi.hystrix.testThreadLocal;

/**
 * Created by Ruifu Hua on 2020/3/21.
 * 演示ThreadLocal在同一个线程中能保存住数据
 */
public class CustomThreadLocal1 {
    //ThreadLocal能保存住同一个线程中的数据，当新开线城时就拿不到数据了
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal1.threadLocal.set("猿天地");
                new Service1().call();
            }
        }).start();
    }
}
class Service1 {
    public void call() {
        System.out.println("Service:" + Thread.currentThread().getName());
        System.out.println("Service:" + CustomThreadLocal1.threadLocal.get());
        new Dao1().call();
    }
}
class Dao1 {
    public void call() {
        System.out.println("==========================");
        System.out.println("Dao:" + Thread.currentThread().getName());
        System.out.println("Dao:" + CustomThreadLocal1.threadLocal.get());
    }
}
