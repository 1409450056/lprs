package com.example.demo.model;

import org.springframework.core.OrderComparator;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    private Integer orderno;

    public Orderprice getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Orderprice orderprice) {
        this.orderprice = orderprice;
    }

    public Orders(Integer orderno, String number, Date deploytime, Date lefttime, Integer status) {
        this.orderno = orderno;
        this.number = number;
        this.deploytime = deploytime;
        this.lefttime = lefttime;
        this.status = status;
    }

    private String number;

    private Date deploytime;

    private Date lefttime;

    private Integer status;

    private Orderprice orderprice;

    private static final long serialVersionUID = 1L;

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDeploytime() {
        return deploytime;
    }

    public void setDeploytime(Date deploytime) {
        this.deploytime = deploytime;
    }

    public Date getLefttime() {
        return lefttime;
    }

    public void setLefttime(Date lefttime) {
        this.lefttime = lefttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
