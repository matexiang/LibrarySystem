package com.lxy.book.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    private List<T> items;
    private int count;
    private RequestPage requestPage;
/*

    private Integer status;//后端业务处理状态
    private String errMsg;
*/


}
