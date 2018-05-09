package com.dbu.book.dao;

import com.dbu.book.model.BookType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTypeDao extends JpaRepository<BookType,Long> {

    BookType findById(int aLong);
}
