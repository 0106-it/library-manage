-- 1. 先删最外层的罚款表（依赖借阅表）
DROP TABLE IF EXISTS fine_record;

-- 2. 再删借阅表（依赖图书表和用户表）
DROP TABLE IF EXISTS borrow_record;

-- 3. 再删图书表
DROP TABLE IF EXISTS book_info;

-- 4. 重建图书表
CREATE TABLE book_info (
    book_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '图书ID',
    book_name VARCHAR(100) NOT NULL COMMENT '书名',
    author VARCHAR(50) NOT NULL COMMENT '作者',
    category_id INT DEFAULT 1 COMMENT '分类ID',
    publisher VARCHAR(100) COMMENT '出版社',
    price DECIMAL(10,2) DEFAULT 0 COMMENT '价格',
    stock INT DEFAULT 0 COMMENT '库存',
    location VARCHAR(50) COMMENT '馆藏位置'
);

-- 5. 重建借阅表（无外键，方便调试）
CREATE TABLE borrow_record (
    borrow_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '借阅ID',
    user_id INT NOT NULL COMMENT '借阅用户ID',
    book_id INT NOT NULL COMMENT '图书ID',
    borrow_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '借阅时间',
    return_time DATETIME DEFAULT NULL COMMENT '归还时间',
    due_time DATETIME DEFAULT NULL COMMENT '应还时间',
    status TINYINT DEFAULT 0 COMMENT '状态：0-借阅中，1-已归还'
);

-- 6. 插入测试数据
INSERT INTO book_info(book_name, author, publisher, price, stock, location) VALUES
('Java编程思想', 'Bruce Eckel', '机械工业出版社', 108.00, 10, '科技区A架'),
('MySQL入门到精通', '张三', '电子工业出版社', 59.00, 5, '科技区B架');