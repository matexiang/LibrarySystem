package com.lxy.book.controller;

import com.lxy.book.enums.StatusEnum;
import com.lxy.book.model.*;
import com.lxy.book.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /*@RequestMapping("/getList")
    public List<BookInfo> getList(){
//        BookService bookService = new BookService();
        List<BookInfo> bookInfos = bookService.getList();
        return bookInfos;
    }*/
    @RequestMapping("/getListByPage")
    public Result<PageResult<BookInfo>> getListByPage(RequestPage requestPage, HttpSession session){


        log.info("getListByPage 接收参数: {}",requestPage);
     /*   UserInfo userInfo = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        if(userInfo==null){
            //用户未登录
             return Result.nologin();}*/

       PageResult<BookInfo> result =  bookService.getListByPage(requestPage);

        return Result.success(result);

    }

    @RequestMapping("/addBook")
    public Boolean addBook(BookInfo bookInfo){
        log.info("BookController.addBook 接收参数: {}", bookInfo);


        if(!StringUtils.hasLength(bookInfo.getBookName()) ||
        !StringUtils.hasLength(bookInfo.getAuthor())
        || bookInfo.getCount()==null
        || bookInfo.getPrice()==null
        || !StringUtils.hasLength(bookInfo.getPublish())){
            log.warn("参数不正确");
            return false;
        }
      Integer result =  bookService.addBook(bookInfo);
        if(result==0){
            log.error("添加图书失败");
            return false;
        }
        log.info("添加图书成功");
        return true;

    }
    //根据id获取图书信息
    @RequestMapping("/getBookInfo")
    public BookInfo getBookInfo(Integer bookId){
        log.info("getBookInfo,bookId: {}", bookId);
        BookInfo bookInfo = bookService.getBookInfo(bookId);
        //看接口约定,当图书信息不存在时,返回null,还是返回空对象
     /*   if(bookInfo!=null && bookInfo.getId()<0){
            return null;
        }*/
        return bookInfo;
    }

    //更新图书
    @RequestMapping("/updateBook")
    public Boolean updateBook(BookInfo bookInfo){
        log.info("updateBook,bookInfo:{}",bookInfo);
        if(bookInfo.getId()==null){
            log.warn("Id不能为空");
            return false;
        }
        if(bookInfo.getBookName()!= null && "".equals(bookInfo.getBookName().trim())){
            log.warn("图书名称不能为空");
            return false;
        }
        try{
            Integer result = bookService.updateBook(bookInfo);
            if(result>0){
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("updateBook error, e:{}",e);

            return false;
        }


    }

    @RequestMapping("/deleteBook")
    public Boolean deleteBook(Integer bookId){
        log.info("deleteBook,id:" + bookId);
        try{
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(bookId);
            bookInfo.setStatus(StatusEnum.DELETED.getCode());
            Integer result = bookService.updateBook(bookInfo);

            if(result > 0){
                return true;
            }

            return false;

        }catch (Exception e){
            log.error("updateBook error,e:{}",e);
            return false;

        }
    }


    @RequestMapping("/batchDelete")
    public Boolean bathDelete(@RequestParam List<Integer> ids){
        log.info("batchDelete ids:{}",ids);
        if(ids==null || ids.size()==0){
            return true;
        }

        try{
            Integer result = bookService.batchDelete(ids);
            if(result>0){
                return true;
            }
            return false;
        }catch (Exception e) {
            log.error("batchDelete error:", e);
            return false;
        }

    }

}
