package com.milepost.exampleService.openFeign.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Ruifu Hua on 2020/3/25.
 */
@RestController
@RequestMapping("/testOpenFeign")
public class TestOpenFeignController {

    private Logger logger = LoggerFactory.getLogger(TestOpenFeignController.class);

    private String CALL_SUCCESS = "调用成功";

    /**
     * 测试Feign的Retryer
     * @return
     */
    @GetMapping("/testFeignTimeOut")
    public Response<String> testFeignTimeOut(@RequestParam("sleep") int sleep){
        Response<String> response = null;
        try {
            System.out.println(Thread.currentThread().getName() + ";" + sleep);
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    /**
     * 测试Feign的Retryer
     * @return
     */
    @GetMapping("/testFeignRetryer")
    public Response<String> testFeignRetryer(@RequestParam("sleep") int sleep){
        Response<String> response = null;
        try {
            System.out.println(Thread.currentThread().getName() + ";" + sleep);
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            System.out.println("--------");
            //Thread.sleep(TimeUnit.SECONDS.toMillis(sleep));
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

    @GetMapping("/testFallbackFactory")
    public Response<String> testFallbackFactory() throws Exception {
        try {
            int i = 1/0;
        }finally {
            System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
        }
        return ResponseHelper.createSuccessResponse(CALL_SUCCESS);
    }

    @GetMapping("/testUrl")
    public Response<String> testUrl(){
        Response<String> response = null;
        try {
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
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
            response = ResponseHelper.createSuccessResponse(CALL_SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

}
