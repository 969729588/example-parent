package com.milepost.exampleUi.test.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.student.Student;
import com.milepost.exampleUi.test.feignClient.TestFc;
import com.milepost.exampleUi.test.service.TestService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.security.Principal;

/**
 * Created by Ruifu Hua on 2019/12/24.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    //如果配置文件中没有这个属性，则启动会报错，
//    @Value("${test.aaa}")
//    private String aaa;
//    @Value("${eureka.instance.instance-id}")
//    private String instanceId;

    @Resource
    private Environment environment;

    @Autowired
    private TestFc testFc;

    /**
     * 测试在请求中传入token，将token传入Fc中
     * http://192.168.223.1:9990/authentication-ui/test/testManualToken?param=123
     * @param param
     * @return
     */
    @ResponseBody
    @GetMapping("/testManualToken")
    public Response<String> testManualToken(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param){
        System.out.println(param);
        Response<String> response = testFc.testManualToken(/*token,*/ param);
        return response;
    }

    /**
     * 测试获取当前用户信息
     * http://192.168.223.1:9990/authentication-ui/test/testGetPrincipal
     * @param oAuth2Authentication
     * @param principal
     * @param authentication
     * @return
     */
    @GetMapping("/testGetPrincipal")
    public OAuth2Authentication testGetPrincipal(OAuth2Authentication oAuth2Authentication, Principal principal,
                                             Authentication authentication){

        logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        logger.info(oAuth2Authentication.toString());
        logger.info("principal.toString()"+principal.toString());
        logger.info("principal.getName()"+principal.getName());
        logger.info("authentication:"+authentication.getAuthorities().toString());
        return oAuth2Authentication;
    }

    /**
     * 测试分布式事务
     * http://192.168.223.1:9990/authentication-ui/test/testDistTransaction?param=123
     *
     * 测试时候需要给feignclient设置超时时间，否则超时后会自动重试，影响测试
     * feign:
        client:
            config:
                default:
                    readTimeout: 600000
                    connectTimeout: 600000
     * @param param
     * @return
     */
    @ResponseBody
    @GetMapping("/testDistTransaction")
    public String testDistTransaction(@RequestParam("param") String param){
        System.out.println("收到参数：" + param);
        return testFc.testDistTransaction(param);
    }

    @ResponseBody
    @PostMapping("/testFileupload")
    public Response<String> testFileupload(Student record,
                                  @RequestParam(value="name", required=true) String name,
                                  @ApiParam(name = "file", value = "上传文件", required = true)
                                  @RequestParam(value="file", required=true) MultipartFile multipartFile) {
        Response<String> response = null;
        try{
            System.out.println(record);
            System.out.println(name);
            //调用service
            response = testFc.testFileupload(name, multipartFile);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
