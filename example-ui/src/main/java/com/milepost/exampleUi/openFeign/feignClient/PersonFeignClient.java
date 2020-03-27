package com.milepost.exampleUi.openFeign.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
@FeignClient(contextId = "personFeignClient",
        name = "${info.app.service.name}",
        fallbackFactory = PersonFeignClientFallbackFactory.class)
public interface PersonFeignClient {

    @GetMapping("${info.app.service.prefix}/testOpenFeign/testFallbackFactory")
    Response<String> testFallbackFactory();

}
