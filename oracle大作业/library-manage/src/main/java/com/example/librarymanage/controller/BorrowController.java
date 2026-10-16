package com.example.librarymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BookInfo;
import com.example.librarymanage.entity.BorrowRecord;
import com.example.librarymanage.entity.SysUser;
import com.example.librarymanage.service.BookService;
import com.example.librarymanage.service.BorrowService;
import com.example.librarymanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    // 借阅记录列表 - 修复pageSize默认值为10
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       Model model) {
        Page<BorrowRecord> page = borrowService.getBorrowPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "admin/book/borrowList";
    }

    // 跳转新增借阅页
    @GetMapping("/add")
    public String addPage(Model model) {
        List<SysUser> userList = userService.getAllReader();
        List<BookInfo> bookList = bookService.getAllBook();
        model.addAttribute("userList", userList);
        model.addAttribute("bookList", bookList);
        return "admin/book/borrowAdd";
    }

    // 提交借书
    @PostMapping("/save")
    public String save(Integer userId, Integer bookId, Integer days) {
        borrowService.borrowBook(userId, bookId, days);
        return "redirect:/admin/borrow/list";
    }

    // 还书操作
    @GetMapping("/return")
    public String returnBook(Integer id) {
        borrowService.returnBook(id);
        return "redirect:/admin/borrow/list";
    }
}