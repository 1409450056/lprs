package com.example.demo.service;

import com.example.demo.model.Orders;

import java.text.ParseException;

public interface BackgroundService {
    void generatorData();

    void dropOrder(String number);

    void finishOrder(String number);

    void creatOrder(String number);

    Orders selectOrderBynumber(String number);


}
