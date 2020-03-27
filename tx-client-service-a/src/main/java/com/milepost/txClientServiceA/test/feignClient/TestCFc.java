package com.milepost.txClientServiceA.test.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient(contextId = "testCFc", name = "tx-client-service-c")//获取配置文件中的service服务名称
public interface TestCFc {

    @GetMapping("/tx-client-service-c/testLcn/test1")
    Response<String> callC(@RequestParam("exFlag") String exFlag);
}
