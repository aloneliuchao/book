package com.dbu.book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * 图书借阅信息
 */
@Entity
public class Borrow {
    @Id
    @GeneratedValue
    private int id;
    private int bookId;
    private String borrwDate;
    private String repaidDate;
    private int userId;
    private String price;
    private String stats;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrwDate() {
        return borrwDate;
    }

    public void setBorrwDate(String borrwDate) {
        this.borrwDate = borrwDate;
    }

    public String getRepaidDate() {
        return repaidDate;
    }

    public void setRepaidDate(String repaidDate) {
        this.repaidDate = repaidDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }
}
