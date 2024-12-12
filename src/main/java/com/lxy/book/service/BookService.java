package com.lxy.book.service;


import com.lxy.book.enums.StatusEnum;
import com.lxy.book.mapper.BookMapper;
import com.lxy.book.model.BookInfo;
import com.lxy.book.model.PageResult;
import com.lxy.book.model.RequestPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

   /* public List<BookInfo> getList(){
//        BookDao bookDao = new BookDao();
        List<BookInfo> bookInfos = bookDao.mockData();
        for (BookInfo bookInfo: bookInfos){
            if (bookInfo.getStatus()==1){
                bookInfo.setStatusCN("可借阅");
            }else {
                bookInfo.setStatusCN("不可借阅");
            }
        }
        return bookInfos;
    }*/

    public Integer addBook(BookInfo bookInfo) {
      return  bookMapper.insertBook(bookInfo);
    }

    public PageResult<BookInfo> getListByPage(RequestPage requestPage) {
       Integer count =  bookMapper.count();
      //  int offset = (requestPage.getCurrentPage()-1) * requestPage.getPageSize();

        List<BookInfo> bookInfos = bookMapper.getListByPage(requestPage.getOffset(), requestPage.getPageSize());
        for (BookInfo bookInfo: bookInfos){
            bookInfo.setStatusCN(StatusEnum.getNameByCode(bookInfo.getStatus()).getName());

        }
      /*  PageResult<BookInfo> result = new PageResult<>();
        result.setItems(bookInfos);
        result.setCount(count);
        return result;*/
        return new PageResult<>(bookInfos,count,requestPage);
    }

    public BookInfo getBookInfo(Integer bookId) {

       return bookMapper.getBookInfo(bookId);
    }

    public Integer updateBook(BookInfo bookInfo) {
        return bookMapper.updateBook(bookInfo);
    }

    public Integer batchDelete(List<Integer> ids) {
        return bookMapper.batchDelete(ids);
    }
}
