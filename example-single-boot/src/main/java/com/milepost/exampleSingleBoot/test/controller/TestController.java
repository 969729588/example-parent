package com.milepost.exampleSingleBoot.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by Ruifu Hua on 2020-05-20.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @ResponseBody
    @GetMapping("/test1")
    public String test1(@RequestParam("param1") String param1){
        return "接收到参数" + param1;
    }

    @ResponseBody
    @GetMapping("/test2")
    public String test2(@RequestParam("param2") String param2){
        return "接收到参数" + param2;
    }

    @ResponseBody
    @GetMapping("/test3")
    public String test3(@RequestParam("param3") String param3, Principal principal){
        System.out.println(principal.getName());
        return "接收到参数" + param3;
    }
}
