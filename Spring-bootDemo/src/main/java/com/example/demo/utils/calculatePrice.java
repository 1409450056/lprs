package com.example.demo.utils;

import java.text.ParseException;
import java.util.Date;

import com.opslab.util.DateUtil;

public class calculatePrice {

    public static int getPrice(Date deployTime, Date leftTime){
        int parkingTime = DateUtil.subtractMinute(deployTime,leftTime);
        float priceperhour = 8;
        int price = (int) (priceperhour * parkingTime);
        return price;
    }

    public static void main(String args[]) throws ParseException {
        Date left,deploy;
        left = new Date();
        deploy = new Date();
        System.out.println(calculatePrice.getPrice(deploy,left));
    }
}
