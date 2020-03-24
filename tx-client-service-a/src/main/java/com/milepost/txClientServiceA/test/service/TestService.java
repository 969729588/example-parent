package com.milepost.txClientServiceA.test.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.milepost.api.util.DataUUIDUtil;
import com.milepost.api.vo.response.Response;
import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleApi.entity.person.PersonExample;
import com.milepost.service.mybatis.service.BaseService;
import com.milepost.txClientServiceA.person.dao.PersonMapper;
import com.milepost.txClientServiceA.test.feignClient.TestBFc;
import com.milepost.txClientServiceA.test.feignClient.TestCFc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/3/23.
 */
@Service
public class TestService extends BaseService<Person, PersonExample>{

    @Autowired
    private TestBFc testBFc;

    @Autowired
    private TestCFc testCFc;

    @Autowired
    private PersonMapper personMapper;

    @LcnTransaction
    public int test1(String exFlag) {
        Response<String> responseB = testBFc.callB(exFlag);
        System.out.println(responseB);
        Response<String> responseC = testCFc.callC(exFlag);
        System.out.println(responseC);

        Person person = new Person();
        person.setId(DataUUIDUtil.randomUUID());
        person.setFirstName("aaa");

        int i = personMapper.insert(person);
        System.out.println(i);

        int a = 1/Integer.valueOf(exFlag);
        System.out.println(a);

        if(responseB.getCode()==0 && responseC.getCode()==0 && i>0){
            return 1;
        }else{
            return 0;
        }
    }
}
