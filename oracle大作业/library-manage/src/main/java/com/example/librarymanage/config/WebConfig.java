package com.example.librarymanage.config;

import com.example.librarymanage.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**")
                // 必须放行：登录页、登录提交接口、静态资源，否则会死循环
                .excludePathPatterns(
                        "/login",
                        "/doLogin",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                );
    }
}