package com.milepost.exampleUi.openFeign.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
//支持url，也能从yml中读取配置项，
//指定url时候name(value)属性也必须配置，可以配置成服务名称，也可以配制成空字符串
@FeignClient(contextId = "testOpenFeignFcUrl", name = "${info.app.service.name}", url = "http://192.168.223.1:9981")
public interface TestOpenFeignFcUrl {
    @GetMapping("${info.app.service.prefix}/testOpenFeign/testUrl")
    Response<String> testUrl();
}
