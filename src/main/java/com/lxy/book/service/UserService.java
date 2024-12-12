package com.lxy.book.service;

import com.lxy.book.mapper.UserMapper;
import com.lxy.book.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

 @Autowired
 private UserMapper userMapper;
    public UserInfo queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);

    }
}
