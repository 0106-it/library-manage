package com.example.librarymanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.librarymanage.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}