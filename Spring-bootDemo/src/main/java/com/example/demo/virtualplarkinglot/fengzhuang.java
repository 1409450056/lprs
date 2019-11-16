package com.example.demo.virtualplarkinglot;

public class fengzhuang {
    public static String fengzhuangaddress(int no) {
        String tou="http://192.144.152.148:997/car";
        String wei=".jpg";
        String result=null;
        result=tou+no+wei;
        System.out.println(result);
        return result;
    }

}
