package com.milepost.exampleUi.hystrix.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/3/21.
 */
@FeignClient(contextId = "testHystrixFc", name = "${info.app.service.name}"/*, fallback = TestHystrixFcFb.class*/)//获取配置文件中的service服务名称
public interface TestHystrixFc {

    @GetMapping("${info.app.service.prefix}/testHystrix/test1")
    Response<String> test1(@RequestParam("param") int param);

    @GetMapping("${info.app.service.prefix}/testHystrix/test2")
    Response<String> test2(@RequestParam("param") int param);
}
