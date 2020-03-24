package com.milepost.exampleService.test.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.student.Student;
import com.milepost.exampleService.person.service.PersonService;
import com.milepost.exampleService.student.service.StudentService;
import com.milepost.exampleService.test.feignClient.TestFc;
import com.milepost.service.config.dynamicDs.DataSourceContextHolder;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestFc testFc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PersonService personService;

    /**
     * 测试动态数据源，
     * @param dsKey 传入one、two。
     * @return
     */
    @ResponseBody
    @GetMapping("/testDynamicDs")
    public String testDynamicDs(@RequestParam("dsKey") String dsKey){

        DataSourceContextHolder.setDataSource(dsKey);
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("id", "11");
        //paramsMap.put("name", "1");
        paramsMap.put("score", 11f);
        Student student = studentService.testMapUnderscoreToCamelCase(paramsMap);
        studentService.testMapUnderscoreToCamelCase(paramsMap);

        return student.getName();
    }

    /**
     * 测试手动传图token
     * @param param
     * @param principal
     * @return
     */
    @ResponseBody
    @GetMapping("/testManualToken")
    public Response<String> testManualToken(@RequestParam("param") String param, Principal principal){
        Response<String> response = null;
        try {
            System.out.println(principal);
            System.out.println(principal.getName());
            System.out.println("收到参数：" + param);
            //testFc.test3(param);
            int i = 1/0;
            response = ResponseHelper.createSuccessResponse("收到参数：" + param);
        }catch (Exception e){
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }


    @ResponseBody
    @PostMapping("/testFileupload")
    public Response<String> testFileupload(@RequestParam(value="name", required=true) String name,
                                           @ApiParam(name = "file", value = "上传文件", required = true)
                                           @RequestParam(value="file", required=true) MultipartFile multipartFile) throws IOException {
        Response<String> response = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            System.out.println(name);
            String riginalFilename = multipartFile.getOriginalFilename();
            inputStream = multipartFile.getInputStream();
            outputStream = new FileOutputStream(new File("F:\\testFile\\fileUpload\\" +  riginalFilename.replace(".", "_.")));
            IOUtils.copy(inputStream, outputStream);

            //调用service
            response = ResponseHelper.createSuccessResponse("上传成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(outputStream);
        }
        return response;
    }
}
