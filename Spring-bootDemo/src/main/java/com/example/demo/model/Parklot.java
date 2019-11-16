package com.example.demo.model;

import java.io.Serializable;

public class Parklot implements Serializable {
    private Integer no;

    private String number;

    private static final long serialVersionUID = 1L;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Parklot(Integer no, String number) {
        this.no = no;
        this.number = number;
    }
}