package com.milepost.txClientServiceC.test.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.milepost.api.util.DataUUIDUtil;
import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleApi.entity.person.PersonExample;
import com.milepost.service.mybatis.service.BaseService;
import com.milepost.txClientServiceC.person.dao.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/3/23.
 */
@Service
public class TestService extends BaseService<Person, PersonExample>{

    @Autowired
    private PersonMapper personMapper;

//    @LcnTransaction
    public int test1(String exFlag) {
        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("cc");

        int i = personMapper.insert(person);

        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }
}
