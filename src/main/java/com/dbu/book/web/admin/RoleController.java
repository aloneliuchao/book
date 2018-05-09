package com.dbu.book.web.admin;

import com.dbu.book.model.Role;
import com.dbu.book.servcie.RoleService;
import com.dbu.book.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String roleList(Model model){
        if(!vLogin(model)){
            return login;
        }
        List<Role> roleList = roleService.findAllRole();
        model.addAttribute("roles",roleList);
        return "admin/role/list";
    }

}
