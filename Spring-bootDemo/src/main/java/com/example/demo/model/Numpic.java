package com.example.demo.model;

import java.io.Serializable;

public class Numpic implements Serializable {
    private String url;

    private String number;

    private Integer mark;

    public Numpic(String url, String number, Integer mark) {
        this.url = url;
        this.number = number;
        this.mark = mark;
    }

    private static final long serialVersionUID = 1L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
