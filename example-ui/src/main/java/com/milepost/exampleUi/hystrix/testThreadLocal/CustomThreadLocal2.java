package com.milepost.exampleUi.hystrix.testThreadLocal;

/**
 * Created by Ruifu Hua on 2020/3/21.
 * 演示ThreadLocal在不同线程中不能保存住数据，将ThreadLocal改成InheritableThreadLocal后能保存住数据
 */
public class CustomThreadLocal2 {
    //static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CustomThreadLocal2.threadLocal.set("猿天地");
                new Service2().call();
            }
        }).start();
    }
}
class Service2 {
    public void call() {
        System.out.println("Service:" + Thread.currentThread().getName());
        System.out.println("Service:" + CustomThreadLocal2.threadLocal.get());
        //new Dao().call();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Dao2().call();
            }
        }).start();
    }
}
class Dao2 {
    public void call() {
        System.out.println("==========================");
        System.out.println("Dao:" + Thread.currentThread().getName());
        System.out.println("Dao:" + CustomThreadLocal2.threadLocal.get());//这里获取不到数据
    }
}