package com.dbu.book.servcie;

import com.dbu.book.model.Book;
import com.dbu.book.model.Borrow;
import com.dbu.book.model.BorrowBo;

import java.util.List;

public interface BorrowService {
    Borrow saveBorrow(Borrow borrow);

    List<BorrowBo> findBorrowBo(int id);


    List<BorrowBo> findBorrowBoByBookType(int userId, String bookTypeId);

    Borrow findBorrowById(String borrowId);

    Borrow update(Borrow borrow);

    List<BorrowBo> findBorrowBo(int id, Borrow borrow);
}
