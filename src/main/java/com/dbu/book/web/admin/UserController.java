package com.dbu.book.web.admin;


import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.Role;
import com.dbu.book.model.User;
import com.dbu.book.model.Vo.BookPage;
import com.dbu.book.servcie.RoleService;
import com.dbu.book.servcie.UserService;
import com.dbu.book.utils.BookUtils;
import com.dbu.book.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String userList(Model model) {
        if (!vLogin(model)) {
            return login;
        }
        return "admin/user/list";
    }

    @GetMapping("/addUser")
    public String saveUserPage(Model model) {
        if (!vLogin(model)) {
            return login;
        }
        return "admin/user/addUser";
    }


    @PostMapping("/saveUser")
    public String saveUser(User user, Model model) {
        if (user != null) {
            boolean b = userService.findUserByUserName(user);
            if (b) {
                model.addAttribute("info", "用户名已存在");
                return "success";
            }
            List<Role> roles = roleService.findRoleByName("读者");
            if (roles != null) {
                user.setRoleList(roles);
            }
            user = userService.add(user);
            if (user.getId() > 0) {
                model.addAttribute("info", "注册成功");
            } else {
                model.addAttribute("info", "注册失败");
            }
        } else {
            model.addAttribute("info", "注册失败");
        }
        //return "redirect:/register";
        model.addAttribute("url", "/register");
        return "success";
    }


    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        if (user != null) {
            boolean b = userService.findUserByUsernameAndPassword(user);
            if (b) {
                User u = userService.getUser(user);
                model.addAttribute("info", "登录成功");
                session.setAttribute("user", u);
            } else {
                model.addAttribute("info", "用户名密码错误");
            }
        } else {
            model.addAttribute("info", "登录失败");
        }

        return "success";
    }

    @GetMapping("/userPage/{userId}")
    public String userEditPage(@PathVariable("userId") String userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "before/editUser";
    }

    @PostMapping("/adminlogin")
    @ResponseBody
    public JSONObject aadminLogin(User user, HttpSession session) {
        JSONObject json = new JSONObject();
        if (user != null) {
            boolean b = userService.findUserByUsernameAndPassword(user);
            if (b) {
                user = userService.getUser(user);
                session.setAttribute("user", user);
                json.put("info", "登录成功");
            } else {
                json.put("info", "用户名密码错误");
            }
        } else {
            json.put("info", "登录失败");
        }
        return json;
    }

    @PostMapping("/editUser")
    public String editUser(User user) {
        userService.update(user);
        return "";
    }

    @PostMapping("/{userId}")
    @ResponseBody
    public JSONObject findUser(@PathVariable("userId") String userId) {
        JSONObject json = new JSONObject();
        User user = userService.getUserById(userId);
        if (user != null) {
            json.put("user", user);
        }

        return json;
    }


    @PostMapping("/delect/{userId}")
    @ResponseBody
    public JSONObject delectUser(@PathVariable("userId") String userId) {
        JSONObject json = new JSONObject();
        User user = userService.getUserById(userId);
        userService.delect(user);
        json.put("info", "删除成功");
        return json;
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model) {
        if (!vLogin(model)) {
            return login;
        }
        return "admin/user/user";
    }

    @PostMapping("/editPass")
    public String editPass(HttpServletRequest request, Model model) {
        if (!vLogin(model)) {
            return login;
        }
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");
        String userId = request.getParameter("id");
        String info = "";
        User user = userService.getUserById(userId);
        if (user != null) {
            if (!oldPass.equals(user.getPassword())) {
                info = "旧密码错误";
            } else {
                user.setPassword(newPass);
                userService.update(user);
                info = "密码修改成功";
            }

        } else {
            info = "密码修改失败";
        }
        model.addAttribute("info", info);

        return "admin/user/user";
    }

    @PostMapping("/saveAdminUser")
    public String saveAdminUser(User user, Model model, HttpServletRequest request) {
        if (!vLogin(model)) {
            return login;
        }
        if (user != null) {
            boolean b = userService.findUserByUserName(user);
            if (b) {
                model.addAttribute("info", "用户名已存在");
                return "admin/user/addUser";
            }
            String role = request.getParameter("role");
            List<Role> roles = roleService.findRoleByName(role);
            if (roles != null) {
                user.setRoleList(roles);
            }
            user = userService.add(user);
            if (user.getId() > 0) {
                model.addAttribute("info", "添加成功");
            } else {
                model.addAttribute("info", "添加失败");
                return "admin/user/addUser";
            }
        } else {
            model.addAttribute("info", "添加失败");
            return "admin/user/addUser";
        }
        //return "redirect:/register";
        model.addAttribute("url", "/register");
        return "redirect:/admin/user/";
    }


    @PostMapping("/userData")
    @ResponseBody
    public JSONObject userData(@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        JSONObject json = new JSONObject();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<User> pages = userService.findUser(pageable);
        Iterator<User> it = pages.iterator();
        List<User> sizeList = userService.findAllUser();
        BookPage bookPage = new BookPage();
        bookPage.setPcount(sizeList.size());
        bookPage.setPindex(pages.getNumberOfElements());
        bookPage.setPsize(pages.getNumber());
        List<User> userList = BookUtils.copyIterator(it);
        json.put("users",userList);
        json.put("bookPage",bookPage);
        return json;
    }


}
