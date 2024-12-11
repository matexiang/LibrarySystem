package com.lxy.book.mapper;

import com.lxy.book.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user_info where delete_flag=0 and user_name = #{user_name}")
    UserInfo queryUserByName(String userName);
}
