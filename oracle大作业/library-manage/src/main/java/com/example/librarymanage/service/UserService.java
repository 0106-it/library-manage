package com.example.librarymanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.librarymanage.entity.SysUser;
import com.example.librarymanage.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private SysUserMapper userMapper;

    // 登录验证
    public SysUser login(String username, String password) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, username)
                .eq(SysUser::getPassword, password);
        return userMapper.selectOne(wrapper);
    }

    // 查询所有读者用户（角色=1）
    public List<SysUser> getAllReader() {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getRole, "1");
        return userMapper.selectList(wrapper);
    }
}