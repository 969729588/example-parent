package com.milepost.exampleSingleBoot.user.service;

import com.milepost.api.util.MD5Util;
import com.milepost.exampleSingleBoot.user.dao.UserMapper;
import com.milepost.exampleSingleBoot.user.entity.User;
import com.milepost.exampleSingleBoot.user.entity.UserExample;
import com.milepost.singleBoot.config.auth.UserService;
import com.milepost.singleBoot.mybatis.service.BaseService;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/1/19.
 */
@Service
public class UserServiceImpl extends BaseService<User, UserExample> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userMapper.selectByUsername(username);
        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) throws Exception {
        UserExample example = new UserExample();
        String md5Password = MD5Util.string2MD5(password);
        example.or().andUsernameEqualTo(username).andPasswordEqualTo(md5Password);
        User user = this.selectOneByExample(example);
        return user;
    }
}
