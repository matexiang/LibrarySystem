package com.lxy.book.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;




    @Test
    void queryUserByName() {
        System.out.println(userMapper.queryUserByName("zhangsan"));
    }
}