package com.dbu.book.web;

import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.Contact;
import com.dbu.book.servcie.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     * 保存留言
     * @param contact
     * @return
     */
    @PostMapping("/saveContact")
    @ResponseBody
    public JSONObject saveContact(Contact contact){
        JSONObject json = new JSONObject();
        if(contact != null){
            contact = contactService.save(contact);
            if(contact.getId() > 0){
                json.put("info","留言成功");
            }else{
                json.put("info","留言失败");
            }
        }else{
            json.put("info","留言失败");
        }

        return json;
    }


}
