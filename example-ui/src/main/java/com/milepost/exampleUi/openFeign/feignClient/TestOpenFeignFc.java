package com.milepost.exampleUi.openFeign.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/3/25.
 */
@FeignClient(contextId = "testOpenFeignFc", name = "${info.app.service.name}")
public interface TestOpenFeignFc {

    @GetMapping("${info.app.service.prefix}/testOpenFeign/testContextId")
    Response<String> testContextId();

    @GetMapping("${info.app.service.prefix}/testOpenFeign/testFeignRetryer")
    Response<String> testFeignRetryer(@RequestParam("sleep") int sleep);

    @GetMapping("${info.app.service.prefix}/testOpenFeign/testFeignTimeOut")
    Response<String> testFeignTimeOut(@RequestParam("sleep") int sleep);
}
