package com.example.librarymanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.librarymanage.entity.BookInfo;
import com.example.librarymanage.entity.BorrowRecord;
import com.example.librarymanage.mapper.BookInfoMapper;
import com.example.librarymanage.mapper.BorrowRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BookInfoMapper bookInfoMapper;

    // 分页查询所有借阅记录（管理员用）
    public Page<BorrowRecord> getBorrowPage(int pageNum, int pageSize) {
        LambdaQueryWrapper<BorrowRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(BorrowRecord::getBorrowId);
        return borrowRecordMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    // 分页查询指定用户的借阅记录（读者端用）
    public Page<BorrowRecord> getUserBorrowPage(int pageNum, int pageSize, Integer userId) {
        LambdaQueryWrapper<BorrowRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BorrowRecord::getUserId, userId);
        wrapper.orderByDesc(BorrowRecord::getBorrowId);
        return borrowRecordMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    // 借书操作
    @Transactional(rollbackFor = Exception.class)
    public void borrowBook(Integer userId, Integer bookId, int days) {
        // 1. 查询图书，校验库存
        BookInfo book = bookInfoMapper.selectById(bookId);
        if (book == null || book.getStock() <= 0) {
            throw new RuntimeException("图书库存不足，无法借阅");
        }

        // 2. 扣减图书库存
        book.setStock(book.getStock() - 1);
        bookInfoMapper.updateById(book);

        // 3. 新增借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setUserId(userId);
        record.setBookId(bookId);
        record.setBorrowTime(LocalDateTime.now());
        record.setDueTime(LocalDateTime.now().plusDays(days));
        record.setStatus(0);
        borrowRecordMapper.insert(record);
    }

    // 还书操作
    @Transactional(rollbackFor = Exception.class)
    public void returnBook(Integer borrowId) {
        // 1. 查询借阅记录
        BorrowRecord record = borrowRecordMapper.selectById(borrowId);
        if (record == null || record.getStatus() == 1) {
            throw new RuntimeException("借阅记录不存在或已归还");
        }

        // 2. 增加图书库存
        BookInfo book = bookInfoMapper.selectById(record.getBookId());
        if (book != null) {
            book.setStock(book.getStock() + 1);
            bookInfoMapper.updateById(book);
        }

        // 3. 更新借阅记录为已归还
        record.setStatus(1);
        record.setReturnTime(LocalDateTime.now());
        borrowRecordMapper.updateById(record);
    }

    // 根据ID查询借阅记录
    public BorrowRecord getById(Integer id) {
        return borrowRecordMapper.selectById(id);
    }
}