package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    public Orders(String number, Date deploytime, Date lefttime, Byte finished) {
        this.number = number;
        this.deploytime = deploytime;
        this.lefttime = lefttime;
        this.finished = finished;
    }
    public Orders(String number, Date deploytime, Byte finished) {
        this.number = number;
        this.deploytime = deploytime;
        this.lefttime = lefttime;
        this.finished = finished;
    }
    private String number;

    private Date deploytime;

    private Date lefttime;

    private Byte finished;

    private static final long serialVersionUID = 1L;

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

    public Byte getFinished() {
        return finished;
    }

    public void setFinished(Byte finished) {
        this.finished = finished;
    }
}
