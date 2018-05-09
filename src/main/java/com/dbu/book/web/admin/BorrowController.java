package com.dbu.book.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.*;
import com.dbu.book.servcie.BookService;
import com.dbu.book.servcie.BookTypeService;
import com.dbu.book.servcie.BorrowService;
import com.dbu.book.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/borrow")
public class BorrowController extends BaseController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/")
    public String index(Model model){
        if(!vLogin(model)){
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        List<Book> books = bookService.findAllBook();

        model.addAttribute("books",books);

        return "admin/borrow/list";
    }

    @GetMapping("/borrowQueryBookByBookType/{bookTypeId}")
    public String queryBook(Model model, @PathVariable("bookTypeId") String bookTypeId){
        if(!vLogin(model)) {
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        List<Book> books = bookService.queryBookByBookType(bookTypeId);
        model.addAttribute("books",books);
        model.addAttribute("bookTypeId",bookTypeId);
        return "admin/borrow/list";
    }

    @PostMapping("/borrowQueryBook")
    public String borrowQueryBook(Book book,Model model){
        if(!vLogin(model)) {
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        List<Book> books = bookService.queryBook(book);
        model.addAttribute("bookTypeId",null);
        model.addAttribute("books",books);
        return "admin/borrow/list";
    }


    /**
     * 图书借阅
     * @param bookId
     * @return
     */
    @PostMapping("/borrowBook")
    @ResponseBody
    public JSONObject borrowBook(String bookId){
        JSONObject json = new JSONObject();
        Book book = bookService.getBookById(bookId);
        if("借出".equals(book.getState())){
            json.put("info","已出借");
        }else {
            Borrow borrow = new Borrow();
            borrow.setBookId(book.getId());
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            borrow.setBorrwDate(dateFormater.format(date));
            borrow.setUserId(((User) session.getAttribute("user")).getId());
            borrow.setStats("借入");
            borrow = borrowService.saveBorrow(borrow);
            book.setState("借出");
            book = bookService.updateBook(book);
            if (book.getState().equals("借出") && borrow.getId() > 0) {
                json.put("info", "借阅成功");
            } else {
                json.put("info", "借阅失败");
            }
        }
        return json;
    }

    /**
     * 图书归还
     * @return
     */
    @PostMapping("/repaidBook/{borrowId}")
    @ResponseBody
    public JSONObject repaidBook(@PathVariable("borrowId") String borrowId){
        JSONObject json = new JSONObject();
        Borrow borrow = borrowService.findBorrowById(borrowId);
        borrow.setStats("归还");
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        borrow.setRepaidDate(dateFormater.format(date));
        borrow.setPrice((this.calculationata(borrow.getBorrwDate(),borrow.getRepaidDate()) * 0.5)+"");
        borrow = borrowService.update(borrow);
        Book book = bookService.getBookById(borrow.getBookId()+"");
        book.setState("空闲");
        bookService.updateBook(book);
        json.put("info","归还成功");
        return json;
    }


    private long calculationata(String borrowDate,String nowDate){
        long  day = 0;
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            day=(myFormatter.parse(nowDate).getTime()-myFormatter.parse(borrowDate).getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
            day = 0L;
        }
        return day;
    }


    @GetMapping("/query")
    public String query(Model model){
        if(!vLogin(model)) {
            return login;
        }

        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        User user = (User)session.getAttribute("user");
        List<BorrowBo> borrows = borrowService.findBorrowBo(user.getId());
        model.addAttribute("borrows",borrows);
        return "admin/borrow/query";
    }

    @GetMapping("/calculation")
    public String calculation(Model model){
        if(!vLogin(model)) {
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        User user = (User)session.getAttribute("user");
        List<BorrowBo> borrows = borrowService.findBorrowBo(user.getId());
        model.addAttribute("borrows",borrows);
        return "admin/borrow/calculation";
    }

}
