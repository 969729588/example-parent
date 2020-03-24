package com.milepost.txClientServiceB.person.service;

import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleApi.entity.person.PersonExample;
import com.milepost.service.mybatis.service.BaseService;
import com.milepost.txClientServiceB.person.dao.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/1/16.
 */
@Service
public class PersonService extends BaseService<Person, PersonExample>{

    @Autowired
    private PersonMapper personMapper;

    public void test1(Person person) {
        personMapper.insert(person);
    }

}
