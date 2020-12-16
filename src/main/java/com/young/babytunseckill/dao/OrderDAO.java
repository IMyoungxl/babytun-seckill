package com.young.babytunseckill.dao;

import com.young.babytunseckill.entity.Order;

public interface OrderDAO {
    Integer insert(Order order);
    Order selectById(Integer oid);
    Order selectByOrderNo(String orderNo);
}
