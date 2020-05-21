package com.milepost.exampleSingleBoot.user.dao;

import com.milepost.exampleSingleBoot.user.entity.User;
import com.milepost.exampleSingleBoot.user.entity.UserExample;
import com.milepost.singleBoot.mybatis.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Ruifu Hua on 2020/1/20.
 */
@Mapper
public interface UserMapper extends BaseMapper<User, UserExample> {

    User selectByUsername(String username);
}
