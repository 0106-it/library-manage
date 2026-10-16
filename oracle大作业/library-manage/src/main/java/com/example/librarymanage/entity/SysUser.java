package com.example.librarymanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String role;
    private String realName;
    private String phone;
    private String status;
    private LocalDateTime createTime;
}