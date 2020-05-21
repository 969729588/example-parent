package com.milepost.exampleSingleBoot.user.service;

import com.milepost.exampleSingleBoot.user.dao.UserMapper;
import com.milepost.singleBoot.config.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Ruifu Hua on 2020/1/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userMapper.selectByUsername(username);
        return user;
    }
}
