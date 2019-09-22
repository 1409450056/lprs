package com.example.demo.service.impl;

import com.example.demo.mapper.OrdersMapper;
import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServicelmpl implements OrdersService {


    @Autowired
    private OrdersMapper orderMapper;

    @Override
    public void deleteByPrimaryKey(int orderNo) {
         orderMapper.deleteByPrimaryKey(orderNo);
    }

    @Override
    public void insert(Orders record) {
         orderMapper.insert(record);
    }

    @Override
    public void insertSelective(Orders record) {
         orderMapper.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(int orderNo) {
        return orderMapper.selectByPrimaryKey(orderNo);
    }

    @Override
    public void updateByPrimaryKeySelective(Orders record) {
         orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public void updateByPrimaryKey(Orders record) {
         orderMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Orders> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Integer> getAllStatus(String number) {
        return orderMapper.getAllStatus(number);
    }

    @Override
    public void finishOrder(Orders record) {
        orderMapper.finishOrder(record);
    }


}