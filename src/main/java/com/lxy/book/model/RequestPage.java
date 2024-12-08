package com.lxy.book.model;

import lombok.Data;

@Data
public class RequestPage {
    private Integer currentPage=1;
    private Integer pageSize=10;

    public Integer getOffset(){
        return (currentPage-1)*pageSize;
    }
}
