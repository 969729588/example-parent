package com.milepost.exampleService.openFeignNewStyle;

import com.milepost.api.vo.response.Response;
import com.milepost.api.vo.response.ResponseHelper;
import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleService.person.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ruifu Hua on 2020/3/26.
 */
@RestController("newStylePersonController")
@RequestMapping("/newStyle/person")
public class PersonController{

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    Response<Person> getPerson(@PathVariable("id") String id) {
        Response<Person> response = null;
        try {
            Person person = personService.selectByPrimaryKey(id);
            response = ResponseHelper.createSuccessResponse(person);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            response = ResponseHelper.createExceptionResponse(e);
        }
        return response;
    }
}
