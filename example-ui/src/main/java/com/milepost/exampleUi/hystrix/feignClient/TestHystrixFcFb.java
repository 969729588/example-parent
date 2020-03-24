package com.milepost.exampleUi.hystrix.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/3/21.
 */
@Component
public class TestHystrixFcFb implements TestHystrixFc{

    @Override
    public Response<String> test1(int param) {
        Response<String> response = ResponseHelper.createSuccessResponse("TestHystrixFcFb.test1_fb，参数=" + param);
        return response;
    }

    @Override
    public Response<String> test2(int param) {
        Response<String> response = ResponseHelper.createSuccessResponse("TestHystrixFcFb.test2_fb，参数=" + param);
        return response;
    }
}
