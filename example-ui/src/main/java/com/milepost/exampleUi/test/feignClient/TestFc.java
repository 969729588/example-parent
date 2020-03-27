package com.milepost.exampleUi.test.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.exampleApi.entity.student.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient(contextId = "testFc", value = "${info.app.service.name}")//获取配置文件中的service服务名称
public interface TestFc {

    @GetMapping("${info.app.service.prefix}/test/testManualToken")
    Response<String> testManualToken(/*@RequestHeader(value = "Authorization") String token,*/ @RequestParam("param") String param);

    /**
     * 使用@RequestPart传送文件
     * @param name
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "${info.app.service.prefix}/test/testFileupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<String> testFileupload(@RequestParam(value="name", required=true) String name, @RequestPart(value = "file") MultipartFile multipartFile);
}
