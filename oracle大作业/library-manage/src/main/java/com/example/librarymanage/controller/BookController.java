package com.example.librarymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BookInfo;
import com.example.librarymanage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 图书列表
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        Page<BookInfo> page = bookService.getBookPage(pageNum, pageSize, keyword);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "admin/book/list";
    }

    // 新增/编辑跳转页 - 修复：必须是@GetMapping，id参数允许为空
    @GetMapping("/add")
    public String addPage(@RequestParam(required = false) Integer id, Model model) {
        BookInfo book = id != null ? bookService.getById(id) : new BookInfo();
        model.addAttribute("book", book);
        return "admin/book/add";
    }

    // 保存图书
    @PostMapping("/save")
    public String save(BookInfo book) {
        bookService.saveOrUpdate(book);
        return "redirect:/admin/book/list";
    }

    // 删除图书
    @GetMapping("/delete")
    public String delete(Integer id) {
        bookService.deleteById(id);
        return "redirect:/admin/book/list";
    }
}