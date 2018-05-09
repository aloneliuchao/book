package com.dbu.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;
    /**
     * 图书名称
     */
    private String name;
//    /**
//     * 图书类别id（关联图书类别表id）
//     */
//    private String bookTypeId;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String press;
    /**
     * 出版时间
     */
    private String pressDate;
    /**
     * 图书简介
     */
    private String synopsis;
    /***
     * 图书详细描述
     */
    private String description;
    /**
     * 图书状态（默认为空闲）
     *
     * （状态：空闲|借出）
     */
    private String state;
    /**
     * 图书价格
     */
    private String price;
    /**
     * 图书照片
     */
    private String image;
    /**
     * 是否热门图书
     */
    private String hot;

    /**
     * 是否新书
     */
    private String newBook;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPressDate() {
        return pressDate;
    }

    public void setPressDate(String pressDate) {
        this.pressDate = pressDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getNewBook() {
        return newBook;
    }

    public void setNewBook(String newBook) {
        this.newBook = newBook;
    }
}

