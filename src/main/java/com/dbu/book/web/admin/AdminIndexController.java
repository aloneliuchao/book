package com.dbu.book.web.admin;

import com.dbu.book.model.User;
import com.dbu.book.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class AdminIndexController extends BaseController {

    @GetMapping("/login")
    public String loginPage(){
        return "/admin/login";
    }


    @GetMapping("/main")
    public String mainPage(Model model){
        if(!vLogin(model)){
            return login;
        }
        return "admin/main";
    }


    /**
     * 用户退出
     * @return
     */
    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/admin/login";
    }


}
