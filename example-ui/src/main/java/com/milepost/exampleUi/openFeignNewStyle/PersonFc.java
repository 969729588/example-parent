package com.milepost.exampleUi.openFeignNewStyle;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
@FeignClient(contextId = "personFc", name = "${info.app.service.name}")
public interface PersonFc extends PersonService{

}
