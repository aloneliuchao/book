package com.dbu.book.servcie.impl;

import com.dbu.book.dao.ContactDao;
import com.dbu.book.model.Contact;
import com.dbu.book.servcie.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;


    @Override
    public Contact save(Contact contact) {
        if(contact != null){
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            contact.setDate(dateFormater.format(date));
        }
        return contactDao.save(contact);
    }

    @Override
    public List<Contact> findAllContact() {
        return contactDao.findAll();
    }

    @Override
    public void delectContact(Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    public Contact getContactById(String contactId) {
        return contactDao.findById(Integer.parseInt(contactId));
    }
}
