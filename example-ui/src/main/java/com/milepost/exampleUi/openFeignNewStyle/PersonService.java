package com.milepost.exampleUi.openFeignNewStyle;

import com.milepost.api.vo.response.Response;
import com.milepost.exampleApi.entity.person.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
public interface PersonService {

    @GetMapping("${info.app.service.prefix}/newStyle/person/{id}")
    Response<Person> getPerson(@PathVariable("id") String id);
}