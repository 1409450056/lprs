package com.example.demo.model;

import java.io.Serializable;

public class Numpic implements Serializable {
    /**
     * number
     */
    private String number;

    /**
     * url
     */
    private String url;

    /**
     * mark
     */
    private Integer mark=0;

    private static final long serialVersionUID = 1L;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Numpic(String number, String url, Integer mark) {
        this.number = number;
        this.url = url;
        this.mark = mark;
    }
}