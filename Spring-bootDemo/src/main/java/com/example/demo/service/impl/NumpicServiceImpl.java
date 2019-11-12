package com.example.demo.service.impl;

import com.example.demo.mapper.NumpicMapper;
import com.example.demo.model.Numpic;
import com.example.demo.service.NumpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumpicServiceImpl implements NumpicService {
    @Autowired
    private NumpicMapper numpicMapper;

    @Override
    public void bindPic(String number, String URL) {
            numpicMapper.insert(new Numpic(number, URL,0));
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

    @Override
    public List<Numpic> selectByMark(int mark) {
        return numpicMapper.selectByMark(mark);
    }

    @Override
    public List<Numpic> selectAllByMarkOrder() {
        return numpicMapper.selectAllByMarkOrder();
    }

    @Override
    public int updateMarkByNumber(Numpic record) {
        return numpicMapper.updateMarkByPrimaryKey(record);
    }

    @Override
    public String selectUrlByPrimaryKey(String number) {
        return numpicMapper.selectUrlByPrimaryKey(number);
    }

    @Override
    public int selectMarkByPrimaryKey(String number) {
        return numpicMapper.selectMarkByPrimaryKey(number);
    }


    @Override
    public Numpic selectByPrimaryKey(String number) {
        return numpicMapper.selectByPrimaryKey(number);
    }

    @Override
    public void insertNumpic(Numpic record) {
        numpicMapper.insert(record);
    }

    @Override
    public void delectNumpic(String number) {
        numpicMapper.deleteByPrimaryKey(number);
    }


}
