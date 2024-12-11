package com.lxy.book.mapper;

import com.lxy.book.model.BookInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("insert into book_info(book_name,author,count,price,publish,status)" +
            "values (#{bookName},#{author},#{count},#{price},#{publish},#{status})")
    Integer insertBook(BookInfo bookInfo);

    @Select("select count(1) from book_info where status !=0")
    Integer count();

    @Select("select * from book_info where status !=0 order by id desc limit #{offest},#{limit}")
    List<BookInfo> getListByPage(Integer offest, Integer limit);

    @Select("select * from book_info where id = #{bookId}")
    BookInfo getBookInfo(Integer bookInfo);


    Integer updateBook(BookInfo bookInfo);


    Integer batchDelete(List<Integer> ids);
}
