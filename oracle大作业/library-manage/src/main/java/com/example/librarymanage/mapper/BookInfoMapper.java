package com.example.librarymanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.librarymanage.entity.BookInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookInfoMapper extends BaseMapper<BookInfo> {
}