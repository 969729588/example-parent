package com.milepost.exampleService.person.service;

import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleApi.entity.person.PersonExample;
import com.milepost.exampleService.person.dao.PersonMapper;
import com.milepost.service.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@Service
@Transactional
public class PersonService extends BaseService<Person, PersonExample>{


    @Autowired
    private PersonMapper personMapper;

    /**
     * 只需要一个注解，标注在service的方法上即可实现分布式事务
     */

}
