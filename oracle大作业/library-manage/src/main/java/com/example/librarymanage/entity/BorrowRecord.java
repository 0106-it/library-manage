package com.example.librarymanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("borrow_record")
public class BorrowRecord {
    @TableId(type = IdType.AUTO)
    private Integer borrowId;
    private Integer userId;
    private Integer bookId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private LocalDateTime dueTime;
    private Integer status;
}