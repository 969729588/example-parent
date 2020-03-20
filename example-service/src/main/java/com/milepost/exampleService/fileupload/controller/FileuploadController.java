package com.milepost.exampleService.fileupload.controller;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.student.Student;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by Ruifu Hua on 2020/3/20.
 */
@RestController
@RequestMapping("/fileupload")
public class FileuploadController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/test1")
    public Response<String> test1(Student record,
                                        @RequestParam(value="name", required=true) String name,
                                        @ApiParam(name = "file", value = "上传文件", required = true)
                                        @RequestParam(value="file", required=true) MultipartFile multipartFile) throws IOException {
        Response<String> response = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try{
            System.out.println(record);
            System.out.println(name);
            String riginalFilename = multipartFile.getOriginalFilename();
            inputStream = multipartFile.getInputStream();
            outputStream = new FileOutputStream(new File("F:\\testFile\\" +  riginalFilename.replace(".", "_"+ name +".")));
            IOUtils.copy(inputStream, outputStream);
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
