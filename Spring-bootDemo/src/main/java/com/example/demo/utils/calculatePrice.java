package com.example.demo.utils;

import java.text.ParseException;
import java.util.Date;

import com.opslab.util.DateUtil;

public class calculatePrice {

    public static int getPrice(Date deployTime, Date leftTime){
        int parkingTime = DateUtil.subtractHour(leftTime,deployTime);
        float priceperhour;
        int price;
        return 0;
    }

    public static void main(String args[]) throws ParseException {
        Date left,deploy;
        left = new Date();
        deploy = new Date();
        System.out.println(calculatePrice.getPrice(deploy,left));
    }
}
