package com.milepost.exampleUi.hystrix.testThreadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ruifu Hua on 2020/3/21.
 * 演示：InheritableThreadLocal类可以完成父线程到子线程的值传递。
 * 但对于使用线程池等会缓存线程的组件的情况，线程由线程池事先创建好，并且线程是缓存起来反复使用的；
 * 这时父子线程关系的InheritableThreadLocal值传递已经没有意义，应用需要的实际上是把 任务提交给线程池时的ThreadLocal值传递到任务执行时。
 *
 * 由此引出了阿里的 transmittable-thread-local 库，将ThreadLocal改成TransmittableThreadLocal，ExecutorService用 TtlExecutors.getTtlExecutorService方法包装一下就可以了。
 */
public class CustomThreadLocal3 {
    //static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    //static ExecutorService pool = Executors.newFixedThreadPool(2);

    static TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();
    static ExecutorService pool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) {
        for(int i=0;i<10;i++) {
            int j = i;
            pool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    CustomThreadLocal3.threadLocal.set("猿天地"+j);
                    new Service3().call();
                }
            }));
        }
    }
}
class Service3 {
    public void call() {
        CustomThreadLocal3.pool.execute(new Runnable() {
            @Override
            public void run() {
                new Dao3().call();
            }
        });
    }
}
class Dao3 {
    public void call() {
        System.out.println("Dao:" + CustomThreadLocal3.threadLocal.get());//此处打印的不是     Dao:猿天地0——Dao:猿天地9，
    }
}