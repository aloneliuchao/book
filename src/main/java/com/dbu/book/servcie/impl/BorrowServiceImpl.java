package com.dbu.book.servcie.impl;

import com.dbu.book.dao.BookDao;
import com.dbu.book.dao.BorrowDao;
import com.dbu.book.model.Book;
import com.dbu.book.model.Borrow;
import com.dbu.book.model.BorrowBo;
import com.dbu.book.servcie.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Borrow saveBorrow(Borrow borrow) {
        return borrowDao.save(borrow);
    }

    @Override
    public List<BorrowBo> findBorrowBo(int id) {
        List<BorrowBo> borrowBos = new ArrayList<>();
        List<Borrow> borrows = borrowDao.findBorrowByUserId(id);
        if(borrows !=null && borrows.size()>0){
            for (Borrow b : borrows) {
                BorrowBo borrowBo = new BorrowBo();
                Book book = bookDao.findBookById(b.getBookId());
                borrowBo.setBook(book);
                borrowBo.setBorrow(b);
                borrowBos.add(borrowBo);
            }
        }
        return borrowBos;
    }



    @Override
    public List<BorrowBo> findBorrowBoByBookType(int userId, String bookTypeId) {
        List<BorrowBo> borrowBos = new ArrayList<>();
        List<Borrow> borrows = borrowDao.findBorrowByUserId(userId);
        if(borrows !=null && borrows.size()>0){
            for (Borrow b : borrows) {
                BorrowBo borrowBo = new BorrowBo();
                Book book = bookDao.findBookById(b.getBookId());
                if (bookTypeId.equals(0)) {
                    borrowBo.setBook(book);
                    borrowBo.setBorrow(b);
                    borrowBos.add(borrowBo);
                }
            }
        }
        return borrowBos;
    }

    @Override
    public Borrow findBorrowById(String borrowId) {
        return borrowDao.findBorrowById(Integer.parseInt(borrowId));
    }

    @Override
    public Borrow update(Borrow borrow) {
        return borrowDao.saveAndFlush(borrow);
    }

    @Override
    public List<BorrowBo> findBorrowBo(int id, Borrow borrow) {
        List<Borrow> borrows = borrowDao.findBorrowByUserId(id);
        List<BorrowBo> borrowBos = new ArrayList<>();
        for (Borrow b:borrows) {
            BorrowBo borrowBo = new BorrowBo();
            borrowBo.setBorrow(b);
            Book book = bookDao.findBookById(b.getBookId());
            borrowBo.setBook(book);
            borrowBos.add(borrowBo);
        }
        return borrowBos;
    }
}
