package com.dbu.book.web.admin;

import com.dbu.book.model.*;
import com.dbu.book.servcie.BookService;
import com.dbu.book.servcie.BookTypeService;
import com.dbu.book.servcie.BorrowService;
import com.dbu.book.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/myBorrow")
public class MyBorrowController extends BaseController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private BorrowService borrowService;


    @PostMapping("/queryMyBorrow")
    public String queryMyBorrow(Borrow borrow, Model model, HttpServletRequest request){
        if(!vLogin(model)){
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        User user = (User)session.getAttribute("user");
        List<BorrowBo> borrows = borrowService.findBorrowBo(user.getId(),borrow);
        model.addAttribute("borrows",borrows);
        return "admin/borrow/query";
    }


    @GetMapping("/queryBorrowByBookType/{bookTypeId}")
    public String queryBook(Model model, @PathVariable("bookTypeId") String bookTypeId){
        if(!vLogin(model)) {
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        User user = (User)session.getAttribute("user");
        List<BorrowBo> borrows = borrowService.findBorrowBoByBookType(user.getId(),bookTypeId);
        model.addAttribute("borrows",borrows);
        model.addAttribute("bookTypeId",bookTypeId);
        return "admin/borrow/query";
    }


}
