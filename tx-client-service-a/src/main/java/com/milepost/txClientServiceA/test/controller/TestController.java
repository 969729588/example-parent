package com.milepost.txClientServiceA.test.controller;

import com.milepost.api.util.DataUUIDUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.person.Person;
import com.milepost.txClientServiceA.person.service.PersonService;
import com.milepost.txClientServiceA.test.feignClient.TestBFc;
import com.milepost.txClientServiceA.test.feignClient.TestCFc;
import com.milepost.txClientServiceA.test.service.TestService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 测试分布式事务
     * @param exFlag
     * @return
     */
    @ApiOperation(value = "测试分布式事务")
    @ResponseBody
    @GetMapping("/test1")
    public Response<String> test1(@RequestParam("exFlag") String exFlag){
        Response<String> response = null;
        try {
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
