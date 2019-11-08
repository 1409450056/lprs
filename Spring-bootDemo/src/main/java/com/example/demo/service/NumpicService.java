package com.example.demo.service;

import com.example.demo.model.Numpic;

import java.util.List;

public interface NumpicService {
    void bindPic(String number,String url);

    void dropBind();

    String getUrl(String number);

    boolean findDuplicate(String number);

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByPrimaryKey(Numpic record);

    String selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);

    Numpic selectByPrimaryKey(String number);

}
