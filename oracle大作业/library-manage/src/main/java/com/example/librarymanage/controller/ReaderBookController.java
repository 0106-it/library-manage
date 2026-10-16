package com.example.librarymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BookInfo;
import com.example.librarymanage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reader")
public class ReaderBookController {

    @Autowired
    private BookService bookService;

    // 读者端图书查询
    @GetMapping("/book")
    public String bookList(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           @RequestParam(required = false) String keyword,
                           Model model) {
        Page<BookInfo> page = bookService.getBookPage(pageNum, pageSize, keyword);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "reader/book";
    }
}