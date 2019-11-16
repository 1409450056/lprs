package com.example.demo.model;

import java.io.Serializable;

public class Orderprice implements Serializable {
    private Integer orderno;

    private Integer price;


    private static final long serialVersionUID = 1L;

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
