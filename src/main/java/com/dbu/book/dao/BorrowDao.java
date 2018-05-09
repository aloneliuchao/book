package com.dbu.book.dao;

import com.dbu.book.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowDao extends JpaRepository<Borrow,Long> {

    List<Borrow> findBorrowByUserId(int id);


    Borrow findBorrowById(int id);
}
