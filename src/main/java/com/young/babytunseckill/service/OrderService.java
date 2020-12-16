package com.young.babytunseckill.service;

import com.young.babytunseckill.dao.OrderDAO;
import com.young.babytunseckill.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    OrderDAO orderDAO;

    public Order findByOrderNo(String orderNo){
        return orderDAO.selectByOrderNo(orderNo);
    }


}

