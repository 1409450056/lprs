package com.example.demo.model;

import java.io.Serializable;

public class Numpic implements Serializable {
    private String number;

    private String url;

    public Numpic(String number, String url) {
        this.number = number;
        this.url = url;
    }

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
}
