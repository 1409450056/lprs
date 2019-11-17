package com.example.demo.service;

import com.example.demo.model.Numpic;

import java.util.List;

public interface NumpicService {
    void bindPic(String number, String url);

    void dropBind();

    String getUrl(String number);

    boolean findDuplicate(String number);

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByNumber(String number);

    String selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);

    List<Numpic> selectByPrimaryKey(String number);

    void insertNumpic(Numpic record);

    void delectNumpic(String number);

    String SelectNumberByUrl(String url);
}
