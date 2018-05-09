package com.dbu.book.dao;

import com.dbu.book.model.Book;
import com.dbu.book.model.BorrowBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {


    Book findBookById(int i);


//    List<Book> findBooksByBookTypeId(String bookTypeId);


    @Query(value = "SELECT * from book t where t.hot = 'hot' ", nativeQuery = true)
    List<Book> findHotBook();

    @Query(value = "SELECT * from book t where t.new_book = '新书' ", nativeQuery = true)
    List<Book> findNewBook();

    Page<Book> findAll(Pageable pageable);
}
