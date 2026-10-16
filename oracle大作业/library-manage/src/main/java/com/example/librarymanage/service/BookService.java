package com.example.librarymanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BookInfo;
import com.example.librarymanage.mapper.BookInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    // 分页查询
    public Page<BookInfo> getBookPage(int pageNum, int pageSize, String keyword) {
        LambdaQueryWrapper<BookInfo> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.like(BookInfo::getBookName, keyword);
        }
        wrapper.orderByDesc(BookInfo::getBookId);
        return bookInfoMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    // 查询所有图书
    public List<BookInfo> getAllBook() {
        return bookInfoMapper.selectList(null);
    }

    // 新增或修改 - 修复：分别调用insert和update，避免saveOrUpdate的坑
    public void saveOrUpdate(BookInfo book) {
        if (book.getBookId() == null) {
            // 新增：主键为空，执行插入
            bookInfoMapper.insert(book);
        } else {
            // 修改：主键有值，执行更新
            bookInfoMapper.updateById(book);
        }
    }

    // 根据ID查询
    public BookInfo getById(Integer id) {
        return bookInfoMapper.selectById(id);
    }

    // 删除
    public void deleteById(Integer id) {
        bookInfoMapper.deleteById(id);
    }
}