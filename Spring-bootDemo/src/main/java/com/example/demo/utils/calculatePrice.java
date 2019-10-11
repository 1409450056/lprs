package com.example.demo.utils;

import java.util.Date;

import com.opslab.util.DateUtil;

public class calculatePrice {

    public static int getPrice(Date deployTime, Date leftTime){
        int parkingTime = DateUtil.subtractHour(deployTime,leftTime);
        float priceperhour = 8;
        int price = (int) (priceperhour * parkingTime);
        return price;
    }
}
