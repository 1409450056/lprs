package com.example.demo.service;

import com.example.demo.model.Orderprice;
import com.example.demo.model.Orders;

import java.util.List;

public interface OrdersService {

    void deleteByPrimaryKey(int orderNo);

    void insert(Orders record);

    void insertSelective(Orders record);

    Orders selectByPrimaryKey(int orderNo);

    void updateByPrimaryKeySelective(Orders record);

    void updateByPrimaryKey(Orders record);

    List<Orders> selectAll();

    List<Integer> getAllStatus(String number);

    void finishOrder(Orders record);

    List<Orders> selectFinished();

    void insertPrice(Orderprice record);

    int getOrderNo(String number);
}
