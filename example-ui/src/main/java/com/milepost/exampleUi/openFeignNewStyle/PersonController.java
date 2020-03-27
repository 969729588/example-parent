package com.milepost.exampleUi.openFeignNewStyle;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/3/26.
 *
 * 由于我们使用多了项目的context-path属性，导致这种新方式不太好用，
 * 如果没有context-path属性，则只需要在PersonService写请求映射注解，
 * Controller和FeignClient不需要写请求映射注解，那样还是很方便的。
 *
 * SpringBoot其实不推荐使用context-path的，他推荐使用端口来区分，
 * 如果没有context-path，那么前端也不需要后端的实例元数据了，
 * 有context-path的好处就是看着比较方便，不用根据端口来区分应用。
 */
@RestController
@RequestMapping("/newStyle/person")
public class PersonController implements PersonService{

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonFc personFc;

    @Override
    @GetMapping("/{id}")
    public Response<Person> getPerson(String id) {
        Response<Person> response = null;
        try {
            response = personFc.getPerson(id);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
