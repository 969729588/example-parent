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
        //使用FallbackFactory能获取异常信息，当断路器被触发打开是，会返回“Hystrix circuit short-circuited and is OPEN”。
        return new PersonFeignClient() {
            @Override
            public Response<String> testFallbackFactory() {
                Response<String> response = ResponseHelper.createFeignErrorResponse();
                response.setPayload(cause.getMessage());
                return response;
            }

            @Override
            public Response<String> testFallback() {
                Response<String> response = ResponseHelper.createFeignErrorResponse();
                response.setPayload(cause.getMessage());
                return response;
            }
        };
    }
}