package com.dbu.book.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.Contact;
import com.dbu.book.servcie.ContactService;
import com.dbu.book.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/contact")
public class AdminContactController extends BaseController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public String index(Model model){
        if (!vLogin(model)){
            return login;
        }

        List<Contact> contacts = contactService.findAllContact();

        model.addAttribute("contacts",contacts);

        return "admin/contact/list";
    }

    @PostMapping("/delect/{contactId}")
    @ResponseBody
    public JSONObject delect(@PathVariable("contactId") String contactId){
        JSONObject json = new JSONObject();
        Contact contact = contactService.getContactById(contactId);
        if(contact != null){
            contactService.delectContact(contact);
            json.put("info","删除成功");
        }else{
            json.put("info","删除失败");
        }

        return json;
    }

}
