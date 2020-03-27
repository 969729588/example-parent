package com.milepost.exampleUi.openFeign.feignClient;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
@Component
public class PersonFeignClientFallbackFactory implements FallbackFactory<PersonFeignClient> {
    @Override
    public PersonFeignClient create(Throwable cause) {
        return new PersonFeignClient() {
            @Override
            public Response<String> testFallbackFactory() {
                Response<String> response = ResponseHelper.createFeignErrorResponse();
                response.setPayload(cause.getMessage());
                return response;
            }
        };
    }
}