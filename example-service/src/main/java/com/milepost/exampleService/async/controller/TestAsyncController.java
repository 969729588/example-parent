package com.milepost.exampleService.async.controller;

import com.milepost.core.lock.ThreadPoolTaskExecutorConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2020/3/12.
 */
@Controller
@RequestMapping("/testAsync")
public class TestAsyncController {

    @GetMapping("/test1")
    @Async
    @ResponseBody
    public void test1(@RequestParam("param") int param){
        System.out.println("当前线程名称：" + Thread.currentThread().getName());
        //打印线程池信息
        ThreadPoolTaskExecutorConfig.printThreadPoolInfo();

        System.out.println("--------");
        System.out.println(1/param);
        System.out.println("--------");
    }
}
