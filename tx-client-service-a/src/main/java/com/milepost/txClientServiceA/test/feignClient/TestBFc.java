package com.milepost.txClientServiceA.test.feignClient;

import com.milepost.api.vo.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Ruifu Hua on 2020/1/8.
 */
@FeignClient("tx-client-service-b")//获取配置文件中的service服务名称
public interface TestBFc {

    @GetMapping("/tx-client-service-b/testLcn/test1")
    Response<String> callB(@RequestParam("exFlag") String exFlag);
}
