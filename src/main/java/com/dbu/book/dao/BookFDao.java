package com.dbu.book.dao;

import com.dbu.book.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookFDao extends CrudRepository<Book, Long> {
    List<Book> findAll(Specification<Book> specification);
}
