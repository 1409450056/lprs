package com.example.demo.service;

import com.example.demo.model.Numpic;

public interface NumpicService {
    void bindPic(String number,String url);

    void dropBind();

    String getUrl(String number);

    boolean findDuplicate(String number);
<<<<<<< HEAD

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByNumber(Numpic record);

    String selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);

    List<Numpic> selectByPrimaryKey(String number);

    void insertNumpic(Numpic record);

    void delectNumpic(String number);

    String SelectNumberByUrl(String url);
=======
>>>>>>> c4b027e1892ca64f087f6007a68489ff32e6df5d
}
