package com.example.librarymanage.controller;

import com.example.librarymanage.entity.SysUser;
import com.example.librarymanage.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // 打开登录页
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 登录表单提交
    @PostMapping("/doLogin")
    public String doLogin(String username, String password, HttpSession session, Model model) {
        // ========== 调试打印：看前端传过来的参数 ==========
        System.out.println("=== 登录请求到达 ===");
        System.out.println("前端传入账号：" + username);
        System.out.println("前端传入密码：" + password);

        SysUser user = userService.login(username, password);

        // ========== 调试打印：看数据库查询结果 ==========
        System.out.println("数据库查询结果：" + user);
        if (user != null) {
            System.out.println("用户角色：" + user.getRole());
        }

        if (user == null) {
            model.addAttribute("msg", "账号或密码错误");
            return "login";
        }

        // 登录成功，存入会话
        session.setAttribute("loginUser", user);

        // 按角色跳转首页
        if ("0".equals(user.getRole())) {
            System.out.println("跳转管理员页面");
            return "redirect:/admin/book/list";
        } else {
            System.out.println("跳转读者页面");
            return "redirect:/reader/book";
        }
    }

    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}