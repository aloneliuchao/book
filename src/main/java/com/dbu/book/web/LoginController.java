package com.dbu.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @PostMapping("/subForm")
    public String subForm(){

        return "admin/main";
    }


}
