package com.milepost.txClientServiceC.test.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.txClientServiceC.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@Controller
@RequestMapping("/testLcn")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @ResponseBody
    @RequestMapping("/test1")
    public Response<String> test1(@RequestParam("exFlag") String exFlag, Principal principal){
        Response<String> response = null;
        try {
            System.out.println(principal);
            System.out.println(principal.getName());
            System.out.println("收到参数：" + exFlag);

            int i = testService.test1(exFlag);
            if(i > 0){
                response = ResponseHelper.createSuccessResponse("操作成功");
            }else{
                response = ResponseHelper.createSuccessResponse("操作失败");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }

}
