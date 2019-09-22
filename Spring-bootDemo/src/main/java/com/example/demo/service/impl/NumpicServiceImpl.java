package com.example.demo.service.impl;

import com.example.demo.mapper.NumpicMapper;
import com.example.demo.model.Numpic;
import com.example.demo.service.NumpicService;
import org.springframework.beans.factory.annotation.Autowired;

public class NumpicImpl implements NumpicService {
    @Autowired
    private NumpicMapper numpicMapper;

    @Override
    public boolean bindPic(String number, String URL) {
        if(numpicMapper.selectByPrimaryKey(number)==null){
            numpicMapper.insert(new Numpic(number,URL));
            return true;
        }
        return false;
    }

    @Override
    public void dropBind() {

    }

    @Override
    public String getUrl(String number) {
        return null;
    }
}
