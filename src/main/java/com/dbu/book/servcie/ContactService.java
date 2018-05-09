package com.dbu.book.servcie;

import com.dbu.book.model.Contact;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);

    List<Contact> findAllContact();

    void delectContact(Contact contact);

    Contact getContactById(String contactId);
}
