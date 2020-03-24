package com.milepost.exampleUi.hystrix.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleUi.hystrix.feignClient.TestHystrixFc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestHystrixFc testHystrixFc;

    @ResponseBody
    @GetMapping("/test1")
    public Response<String> test1(@RequestParam("param") int param){
        Response<String> response = null;
        try {
            response = testHystrixFc.test1(param);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @ResponseBody
    @GetMapping("/test2")
    public Response<String> test2(@RequestParam("param") int param){
        Response<String> response = null;
        try {
            response = testHystrixFc.test2(param);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }


}
