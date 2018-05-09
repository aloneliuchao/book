package com.dbu.book.model;


public class BorrowBo extends Borrow {
    private Book book;
    private Borrow borrow;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }
}
