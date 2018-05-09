package com.dbu.book.servcie;

import com.dbu.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAllBook();

    Book saveBook(Book book);

    Book getBookById(String bookId);

    void delectBook(Book book);

    Book updateBook(Book book);


    List<Book> queryBookByBookType(String bookTypeId);

    List<Book> queryBook(Book book);

    List<Book> findHotBook();

    List<Book> findNewBook();

    Page<Book> findBook(Pageable pageable);
}
