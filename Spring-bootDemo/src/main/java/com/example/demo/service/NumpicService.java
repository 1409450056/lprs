package com.example.demo.service;

import com.example.demo.model.Numpic;

public interface NumpicService {
    void bindPic(String number,String url);

    void dropBind();

    String getUrl(String number);

    boolean findDuplicate(String number);
}
