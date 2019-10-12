package com.example.demo.utils;

import java.util.Date;

public class calculatePrice {

    public static int subtractHour(Date date1, Date date2) {
        long cha = (date2.getTime() - date1.getTime()) / 1000L;
        return (int)cha / 3600;
    }

    public static int getPrice(Date deployTime, Date leftTime){
        int parkingTime = calculatePrice.subtractHour(deployTime,leftTime);
        float priceperhour = 8;
        int price = (int) (priceperhour * parkingTime);
        return price;
    }
}
