package com.milepost.exampleService.classes.dao;

import com.milepost.exampleApi.entity.classes.Classes;
import com.milepost.exampleApi.entity.classes.ClassesExample;
import com.milepost.service.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2018-11-28.
 */
@Mapper
public interface ClassesMapper extends BaseMapper<Classes, ClassesExample> {

}
