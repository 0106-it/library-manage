package com.example.librarymanage.interceptor;

import com.example.librarymanage.entity.SysUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("loginUser");

        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        // 管理员路径校验
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") && !"0".equals(user.getRole())) {
            response.sendRedirect("/reader/book");
            return false;
        }
        return true;
    }
}