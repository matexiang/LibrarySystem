package com.lxy.book.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    private Integer id;
    private String password;
    private String userName;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;

}
