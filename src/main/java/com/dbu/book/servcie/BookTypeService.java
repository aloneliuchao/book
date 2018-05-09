package com.dbu.book.servcie;



import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.BookType;

import java.util.List;

public interface BookTypeService {
    List<BookType> findAllBookType();

    BookType saveBookType(BookType bookType);

    BookType findBookType(String id);

    BookType updateBookType(BookType b);

    void delectBookType(BookType b);

    JSONObject sendData(List<BookType> bookTypes);
}
