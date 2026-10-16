package com.example.librarymanage.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BorrowRecord;
import com.example.librarymanage.entity.SysUser;
import com.example.librarymanage.service.BorrowService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reader")
public class ReaderBorrowController {

    @Autowired
    private BorrowService borrowService;

    // 我的借阅记录
    @GetMapping("/borrow")
    public String myBorrow(@RequestParam(defaultValue = "1") int pageNum,
                           @RequestParam(defaultValue = "10") int pageSize,
                           HttpSession session,
                           Model model) {
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        Page<BorrowRecord> page = borrowService.getUserBorrowPage(pageNum, pageSize, loginUser.getUserId());
        model.addAttribute("page", page);
        return "reader/borrow";
    }
}