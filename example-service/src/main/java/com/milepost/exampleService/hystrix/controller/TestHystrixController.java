package com.milepost.exampleService.hystrix.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Ruifu Hua on 2020/3/21.
 */
@Controller
@RequestMapping("/testHystrix")
public class TestHystrixController {

    private Logger logger = LoggerFactory.getLogger(TestHystrixController.class);


    /****************测试@HystrixCommand-开始****************/
    /**
     * 使用@HystrixCommand的步骤：
     *  1、注解需要引入
     *  <dependency>
     *       <groupId>org.springframework.cloud</groupId>
     *       <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
     *  </dependency>
     *  然后启动报错，因为guava版本冲突，还需要在引入
     *  <dependency>
     *       <groupId>com.google.guava</groupId>
     *       <artifactId>guava</artifactId>
     *       <version>20.0</version>
     *  </dependency>
     *
     *  2、在启动类上增加@EnableHystrix注解。
     *
     */

    /**
     * HystrixCommand当方法出现没有被捕获的异常时，执行fallbackMethod，
     * 每一个方法都需要提供一个fallbackMethod，会造成方法暴增，
     * 而且UI类服务和Service类服务的Controller中都是有try-catch的，
     * 所以不推荐使用HystrixCommand
     * @param param
     * @return
     */
    //@HystrixCommand(fallbackMethod = "test1_fb")
    @ResponseBody
    @GetMapping("/test1")
    public Response<String> test1(@RequestParam("param") int param){
        Response<String> response = null;
        try {
            int i = 100/param;
            response = ResponseHelper.createSuccessResponse("调用成功，返回结果=" + i);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * 永远不会进入才方法
     * @param param
     * @return
     */
    public Response<String> test1_fb(int param){
        Response<String> response = ResponseHelper.createSuccessResponse("test1_fb，参数=" + param);
        return response;
    }

    //@HystrixCommand(fallbackMethod = "test2_fb")
    @ResponseBody
    @GetMapping("/test2")
    public Response<String> test2(@RequestParam("param") int param){
        int i = 100/param;
        Response<String> response = ResponseHelper.createSuccessResponse("调用成功，返回结果=" + i);
        return response;
    }

    /**
     * 当请求test2传入0时，会进入此方法。
     * @param param
     * @return
     */
    public Response<String> test2_fb(int param){
        Response<String> response = ResponseHelper.createSuccessResponse("test1_fb，参数=" + param);
        return response;
    }
    /****************测试@HystrixCommand-开始****************/
}
