package com.example.librarymanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("book_info")
public class BookInfo {
    @TableId(type = IdType.AUTO)
    private Integer bookId;
    private String bookName;
    private String author;
    private Integer categoryId;
    private BigDecimal price;
    private Integer stock;
    private String publisher;
    private String location;
}