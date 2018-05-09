package com.dbu.book.web;

import com.dbu.book.model.BookType;
import com.dbu.book.model.User;
import com.dbu.book.servcie.BookTypeService;
import com.dbu.book.servcie.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaseController {

    HttpServletRequest request;
    public HttpSession session;
    public String login = "redirect:/admin/login";
    public User user;

    @Autowired
    private BookTypeService bookTypeService;
    @Autowired
    private UserService userService;


    @ModelAttribute
    public void index(HttpServletRequest request,HttpSession session,Model model){
        this.request = request;
        this.session = session;
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        //user = (User) session.getAttribute("user");

    }

    public boolean vLogin(Model model){
        user = (User) session.getAttribute("user");
        if(user != null){
            user = userService.getUserById(user.getId()+"");
            model.addAttribute("user",user);
            return true;
        }
        return false;
    }

}
