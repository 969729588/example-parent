package com.milepost.exampleUi.openFeign.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/3/28.
 */
@Component
public class PersonFeignClientFallback implements PersonFeignClient{
    @Override
    public Response<String> testFallbackFactory() {
        Response<String> response = ResponseHelper.createFeignErrorResponse();
        response.setPayload("进入Fallback");
        return response;
    }

    @Override
    public Response<String> testFallback() {
        Response<String> response = ResponseHelper.createFeignErrorResponse();
        response.setPayload("进入Fallback");
        return response;
    }
}
