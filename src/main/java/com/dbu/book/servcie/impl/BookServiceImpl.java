package com.dbu.book.servcie.impl;

import com.dbu.book.dao.BookDao;
import com.dbu.book.dao.BookFDao;
import com.dbu.book.dao.BookTypeDao;
import com.dbu.book.model.Book;
import com.dbu.book.model.BookType;
import com.dbu.book.servcie.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookFDao bookFDao;

    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public List<Book> findAllBook() {
        List<Book> books = bookDao.findAll();
        for (Book b : books) {
//            BookType bt = bookTypeDao.findById(Integer.parseInt(null));
//            if(bt != null) {
//                //b.setBookTypeId(bt.getName());
//            }
        }
        return books;
    }

    @Override
    public Book saveBook(Book book) {
        if (book != null) {
            book.setState("空闲");
        }
        return bookDao.save(book);
    }

    @Override
    public Book getBookById(String bookId) {
        return bookDao.findBookById(Integer.parseInt(bookId));
    }

    @Override
    public void delectBook(Book book) {
        bookDao.delete(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookDao.saveAndFlush(book);
    }

    @Override
    public List<Book> queryBookByBookType(String bookTypeId) {
        return null;
    }

    @Override
    public List<Book> queryBook(Book book) {
        List<Book> books = null;
        if (book != null) {
            books = bookFDao.findAll(new Specification<Book>() {
                @Override
                public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    Path<String> namePath = root.get("name");
                    Path<String> authorPath = root.get("author");
                    Path<String> typePath = root.get("bookTypeId");
                    Path<String> statePath = root.get("state");
                    List<Predicate> predicate = new ArrayList<>();
                    /**
                     * 连接查询条件, 不定参数，可以连接0..N个查询条件
                     */
                    Predicate namePredicate = null;
                    Predicate authorPredicate = null;
                    Predicate typePredicate = null;
                    Predicate statePredicate = null;
                    if (book.getName() != null) {
                        namePredicate = criteriaBuilder.like(namePath, "%"+book.getName()+"%");
                        predicate.add(namePredicate);
                    }
                    if (book.getAuthor() != null) {
                        authorPredicate = criteriaBuilder.like(authorPath, "%"+book.getAuthor()+"%");
                        predicate.add(authorPredicate);
                    }
//                    if (book.getBookTypeId() != null) {
//                        typePredicate = criteriaBuilder.like(typePath, book.getBookTypeId());
//                        predicate.add(typePredicate);
//                    }
                    if (book.getState() != null) {
                        statePredicate = criteriaBuilder.like(statePath, book.getState());
                        predicate.add(statePredicate);

                    }
                    Predicate[] pre = new Predicate[predicate.size()];

                    criteriaQuery.where(predicate.toArray(pre));

                    return null;
                }
            });
        }
        return books;
    }

    @Override
    public List<Book> findHotBook() {
        return bookDao.findHotBook();
    }

    @Override
    public List<Book> findNewBook() {
        return bookDao.findNewBook();
    }

    @Override
    public Page<Book> findBook(Pageable pageable) {
        return bookDao.findAll(pageable);
    }


}
