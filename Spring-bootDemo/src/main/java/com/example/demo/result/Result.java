package com.example.demo.result;

import java.util.List;
import java.util.Map;

public class Result {
    //响应码
    private int code;
    private Map<String,String> data;
    private String message;
    public  Map<String,String> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData( Map<String,String> data) {
        this.data = data;
    }

    public Result(int code, Map<String,String> data,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public Result(int code, Map<String,String> data){
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
