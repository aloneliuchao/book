package com.dbu.book.web.admin;


import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.BookType;
import com.dbu.book.servcie.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/bookType")
public class BookTypeController extends BaseAdminController {

    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping("/")
    public String index(Model model) {
        if (!vLogin(model)) {
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        return "admin/type/list";
    }


    @PostMapping("/addBookType")
    public String saveBooType(BookType bookTypeMo,Model model) {
        if(!vLogin(model)){
            return login;
        }
        if (bookTypeMo != null) {
            bookTypeMo = bookTypeService.saveBookType(bookTypeMo);
            if (bookTypeMo.getId() > 0) {
                model.addAttribute("info", "添加成功");
            } else {
                model.addAttribute("info", "添加失败");
            }
        } else {
            model.addAttribute("info", "添加失败");
        }
        return  "redirect:/admin/bookType/";
    }

    @PostMapping("/updateBookType")
    @ResponseBody
    public JSONObject updateBookType(BookType bookType){
        JSONObject json = new JSONObject();
        if (bookType != null){
            BookType b = bookTypeService.findBookType(bookType.getId()+"");
            if(b != null) {
                b.setName(bookType.getName());
                b = bookTypeService.updateBookType(b);
                json.put("info","success");
            }else{
                json.put("info","error");
            }
        }else{
            json.put("info","error");
        }


        return json;
    }

    @PostMapping("/delectBookType")
    @ResponseBody
    public JSONObject delectBookType(BookType bookType){
        JSONObject json = new JSONObject();
        if (bookType != null){
            BookType b = bookTypeService.findBookType(bookType.getId()+"");
            if(b != null) {
                bookTypeService.delectBookType(b);
                json.put("info","success");
            }else{
                json.put("info","error");
            }
        }else{
            json.put("info","error");
        }


        return json;
    }


}
