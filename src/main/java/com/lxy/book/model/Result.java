package com.lxy.book.model;

import com.lxy.book.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T>{
    private ResultCodeEnum code;
    private String errMsg;
    private T data;

    public static <T> Result<T> nologin(){
        //用户未登录
        Result result = new Result<>();
        result.setCode(ResultCodeEnum.NOLOGIN);
        result.setErrMsg("用户未登录");
        result.setData(null);
        return result;
    }
    public static <T> Result<T> success(T data){
        //用户未登录
        Result result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }


}
