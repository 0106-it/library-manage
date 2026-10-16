package com.example.librarymanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.librarymanage.mapper")
public class LibraryManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryManageApplication.class, args);
    }
}