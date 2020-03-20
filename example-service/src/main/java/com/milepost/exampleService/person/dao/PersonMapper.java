package com.milepost.exampleService.person.dao;

import com.milepost.exampleApi.entity.person.Person;
import com.milepost.exampleApi.entity.person.PersonExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/2/24.
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person, PersonExample> {
}
