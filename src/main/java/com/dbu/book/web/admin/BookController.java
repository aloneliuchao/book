package com.dbu.book.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.dbu.book.model.Book;
import com.dbu.book.model.BookType;
import com.dbu.book.model.Vo.BookPage;
import com.dbu.book.servcie.BookService;
import com.dbu.book.servcie.BookTypeService;
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

import java.util.Iterator;
import java.util.List;

/**
 * 图书管理控制类
 */
@Controller
@RequestMapping("/admin/book")
public class BookController extends BaseController {

    /**
     * 图书服务类接口
     */
    @Autowired
    private BookService bookService;
    /**
     * 图书分类服务
     */
    @Autowired
    private BookTypeService bookTypeService;


    /**
     * 图书列表页面（book/list）
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        if(!vLogin(model)){
            return login;
        }
        return "admin/book/list";
    }


    /**
     * 跳转到图书添加页面
     * @param model
     * @return
     */
    @GetMapping("/saveBookPage")
    public String saveBookPage(Model model){
        if(!vLogin(model)){
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        return "admin/book/addBook";
    }

    /**
     * 保存图书
     * @param book
     * @param model
     * @return
     */
    @PostMapping("/saveBook")
    public String saveBook(Book book,Model model){
        if(!vLogin(model)){
            return login;
        }
        if(book != null){
            book = bookService.saveBook(book);
        }
        return "redirect:/admin/book/";
    }

    /**
     * 删除图书
     * @param bookId
     * @return
     */
    @PostMapping("/delect/{bookId}")
    @ResponseBody
    public JSONObject delect(@PathVariable("bookId") String bookId){
        JSONObject json = new JSONObject();
        Book book = bookService.getBookById(bookId);
        if(book != null){
            bookService.delectBook(book);
            json.put("info","删除成功");
        }else{
            json.put("info","删除失败");
        }

        return json;
    }

    /**
     * 跳转到编辑图书页面
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/editBookPage/{bookId}")
    public String editBookPage(@PathVariable("bookId") String bookId,Model model){
        if(!vLogin(model)){
            return login;
        }
        List<BookType> bookTypes = bookTypeService.findAllBookType();
        model.addAttribute("bookType", bookTypeService.sendData(bookTypes));
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book",book);
        return "admin/book/editBook";
    }


    /**
     * 保存编辑图书
     * @param book
     * @return
     */
    @PostMapping("/editBook")
    public String editBook(Book book){
        if(book != null){
            book = bookService.updateBook(book);
        }
        return "redirect:/admin/book/";
    }

    /**
     * 图书查询
     * @param book
     * @param model
     * @return
     */
    @PostMapping("/queryBook")
    public String queryBook(Book book,Model model){
        if(!vLogin(model)){
            return login;
        }
        List<Book> books = bookService.findAllBook();
        model.addAttribute("books",books);
        return "admin/book/list";
    }

    /**
     * 图书查看
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/lookBook/{bookId}")
    public String lookBook(@PathVariable("bookId") String bookId,Model model){
        if(!vLogin(model)){
            return login;
        }
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book",book);
        return "admin/book/book";
    }


    /**
     * 设置为热门图书
     * @param bookId
     * @return
     */
    @PostMapping("/hot/{bookId}")
    @ResponseBody
    public JSONObject hot(@PathVariable("bookId") String bookId){
        JSONObject json = new JSONObject();
        Book book = bookService.getBookById(bookId);
        if(book != null){
            book.setHot("hot");
            book = bookService.updateBook(book);
            json.put("info","设置成功");
        }else{
            json.put("info","设置失败");
        }

        return json;
    }

    @PostMapping("/newBook/{bookId}")
    @ResponseBody
    public JSONObject newBook(@PathVariable("bookId") String bookId){
        JSONObject json = new JSONObject();
        Book book = bookService.getBookById(bookId);
        if(book != null){
            book.setNewBook("新书");
            book = bookService.updateBook(book);
            json.put("info","设置成功");
        }else{
            json.put("info","设置失败");
        }

        return json;
    }


    @PostMapping("/bookData")
    @ResponseBody
    public JSONObject bookData(@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size){
        JSONObject json = new JSONObject();
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<Book> pages = bookService.findBook(pageable);
        Iterator<Book> it = pages.iterator();
        List<Book> sizeList = bookService.findAllBook();
        BookPage bookPage = new BookPage();
        bookPage.setPcount(sizeList.size());
        bookPage.setPindex(pages.getNumberOfElements());
        bookPage.setPsize(pages.getNumber());
        List<Book> bookList = BookUtils.copyIterator(it);
        json.put("book",bookList);
        json.put("bookPage",bookPage);
        return json;
    }




}
