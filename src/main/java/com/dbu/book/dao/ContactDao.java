package com.dbu.book.dao;

import com.dbu.book.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 留言持久层
 */
public interface ContactDao extends JpaRepository<Contact,Long> {

    Contact findById(int contactId);
}
