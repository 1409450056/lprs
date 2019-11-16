package com.example.demo.model;

import java.io.Serializable;

public class Numpic implements Serializable {
<<<<<<< HEAD
    private String url;

    private String number;
=======
    private String number;

    private String url;

    public Numpic(String number, String url) {
        this.number = number;
        this.url = url;
    }

    private static final long serialVersionUID = 1L;
>>>>>>> c4b027e1892ca64f087f6007a68489ff32e6df5d

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
<<<<<<< HEAD

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
=======
>>>>>>> c4b027e1892ca64f087f6007a68489ff32e6df5d
}
