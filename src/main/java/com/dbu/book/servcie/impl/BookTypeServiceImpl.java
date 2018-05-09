package com.dbu.book.servcie.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dbu.book.dao.BookTypeDao;
import com.dbu.book.model.BookType;
import com.dbu.book.servcie.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public List<BookType> findAllBookType() {
        return bookTypeDao.findAll();
    }

    @Override
    public BookType saveBookType(BookType bookType) {
        return bookTypeDao.save(bookType);
    }

    @Override
    public BookType findBookType(String id) {
        return bookTypeDao.findById(Integer.parseInt(id));
    }

    @Override
    public BookType updateBookType(BookType b) {
        return bookTypeDao.saveAndFlush(b);
    }

    @Override
    public void delectBookType(BookType b) {
         bookTypeDao.delete(b);
    }


    public JSONObject sendData(List<BookType> bookTypes) {
        JSONObject root = new JSONObject();
        JSONArray rootArray = new JSONArray();
        for (BookType bt : bookTypes) {
            if (bt.getpId() == 0) {
                rootArray.add(bt);
            }
            if(bt.getpId() != 0) {
                List<BookType> bookTypesChildre = this.findChildren(bookTypes, bt.getpId());
                for (int i = 0; i < rootArray.size(); i++) {
                    JSONObject obj = rootArray.getJSONObject(i);
                    if (obj.getInteger("id") == bt.getpId()) {
                        obj.put("children", bookTypesChildre);
                        rootArray.set(i,obj);
                    }
                }
            }

        }

        root.put("data", rootArray);
        return root;
    }

    private List<BookType> findChildren(List<BookType> bookTypes, int pid) {
        List<BookType> bookTypesChildre = new ArrayList<>();
        for (BookType bt : bookTypes) {
            if (pid == bt.getpId()) {
                bookTypesChildre.add(bt);
            }
        }
        return bookTypesChildre;
    }
}
