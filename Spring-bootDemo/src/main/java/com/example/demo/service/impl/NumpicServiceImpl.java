package com.example.demo.service.impl;

import com.example.demo.mapper.NumpicMapper;
import com.example.demo.model.Numpic;
import com.example.demo.service.NumpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumpicServiceImpl implements NumpicService {
    @Autowired
    private NumpicMapper numpicMapper;

    @Override
    public void bindPic(String number, String URL) {
            numpicMapper.insert(new Numpic(number, URL));
            System.out.println("插入数据" + number);
    }

    @Override
    public void dropBind() {

    }

    @Override
    public String getUrl(String number) {
        return numpicMapper.selectByPrimaryKey(number).getUrl();
    }

    @Override
    public boolean findDuplicate(String number) {
        if(numpicMapper.selectByPrimaryKey(number)==null){
            return true;
        }
        return false;
    }
}
