package com.dbu.book.web;

import com.dbu.book.model.Book;
import com.dbu.book.servcie.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 首页加载
 */
@Controller
public class IndexController extends BaseController  {


    @Autowired
    private BookService bookService;

    /**
     *  跳转的首页，加载首页信息
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model){
        List<Book> hotBooks = bookService.findHotBook();
        List<Book> newBooks = bookService.findNewBook();
        model.addAttribute("hot",hotBooks);
        model.addAttribute("newBook",newBooks);
        return "index";
    }


    /**
     * 跳转的到图书页面,加载图书信息
     * @return
     */
    @GetMapping("/book")
    public String book(Model model){
        List<Book> bookList = bookService.findAllBook();
        model.addAttribute("books",bookList);
        return "before/category";
    }

    /**
     * 查询图书详细信息
     * @return
     */
    @GetMapping("/book/{bookId}")
    public String findBook(@PathVariable("bookId") String bookId,Model model){
            Book book = bookService.getBookById(bookId);
            model.addAttribute("book",book);
        return "before/details";
    }



    @GetMapping("/myaccount")
    public String woLogin(Model model){
        if(vLogin(model)){
            model.addAttribute("user",user);
            return "before/user";
        }
        return "before/myaccount";
    }


    @GetMapping("/register")
    public String register(){
        return "before/register";
    }

    @GetMapping("/contact")
    public String contact(){
        return "before/contact";
    }


    @GetMapping("/about")
    public String about(){
        return "before/about";
    }


    /**
     * 用户退出
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(){
        session.setAttribute("user",null);
        return "redirect:/myaccount";
    }




}
