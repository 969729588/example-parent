package com.milepost.exampleUi.openFeign.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleUi.hystrix.controller.TestHystrixController;
import com.milepost.exampleUi.openFeign.feignClient.PersonFeignClient;
import com.milepost.exampleUi.openFeign.feignClient.TestOpenFeignFc;
import com.milepost.exampleUi.openFeign.feignClient.TestOpenFeignFcUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/3/25.
 */
@RestController
@RequestMapping("/testOpenFeign")
public class TestOpenFeignController {

    private Logger logger = LoggerFactory.getLogger(TestOpenFeignController.class);

    @Autowired
    private TestOpenFeignFc testOpenFeignFc;

    @Autowired
    private TestOpenFeignFcUrl testOpenFeignFcUrl;

    @Autowired
    private PersonFeignClient personFeignClient;

    @GetMapping("/testFeignTimeOut")
    public Response<String> testFeignTimeOut(@RequestParam("sleep") int sleep){
        Response<String> response = null;
        long start = System.currentTimeMillis();
        long end1 = 0;
        long end2 = 0;
        try {

            response = testOpenFeignFc.testFeignTimeOut(sleep);
            end1 = System.currentTimeMillis();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
            end2 = System.currentTimeMillis();
        }
        System.out.println("花费时间：" + (end1-start));
        System.out.println("花费时间：" + (end2-start));
        return response;
    }


    /**
     * 测试Feign的Retryer，
     * SpringCloud默认关闭了Feign的重试，因为他与ribbon冲突
     * @return
     */
    @GetMapping("/testFeignRetryer")
    public Response<String> testFeignRetryer(@RequestParam("sleep") int sleep){
        Response<String> response = null;
        try {
            response = testOpenFeignFc.testFeignRetryer(sleep);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * FallbackFactory不太适用，因为UI的catch中能捕获到FeignClient的异常信息。
     * 需要开启hystrix.enabled=true
     * @return
     */
    @GetMapping("/testFallbackFactory")
    public Response<String> testFallbackFactory(){
        Response<String> response = null;
        try {
            response = personFeignClient.testFallbackFactory();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        System.out.println("---");
        return response;
    }

    @GetMapping("/testUrl")
    public Response<String> testUrl(){
        Response<String> response = null;
        try {
            response = testOpenFeignFcUrl.testUrl();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @GetMapping("/testContextId")
    public Response<String> testContextId(){
        Response<String> response = null;
        try {
            response = testOpenFeignFc.testContextId();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
