package com.lxy.book.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ResultCodeEnum {
    //成功
    //未登录
    //后端发生异常
    //数据库异常
    //参数异常
    SUCCESS(200,"成功"),
    NOLOGIN(-1,"未登录"),
    FAIL(-2,"内部错误"),
    ;
    @Getter
    private int code;
    @Getter
    private String name;

}
