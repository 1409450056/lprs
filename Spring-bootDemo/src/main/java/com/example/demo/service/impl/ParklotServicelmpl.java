package com.example.demo.service.impl;

import com.example.demo.mapper.ParklotMapper;
import com.example.demo.model.Parklot;
import com.example.demo.service.ParklotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParklotServicelmpl implements ParklotService {


    @Autowired
    private ParklotMapper parklotMapper;

    @Override
    public int deleteByPrimaryKey(Integer no) {
        return parklotMapper.deleteByPrimaryKey(no);
    }

    @Override
    public int insert(Parklot record) {
        return parklotMapper.insert(record);
    }

    @Override
    public int insertSelective(Parklot record) {
        return parklotMapper.insertSelective(record);
    }

    @Override
    public Parklot selectByPrimaryKey(Integer no) {
        return parklotMapper.selectByPrimaryKey(no);
    }

    @Override
    public int updateByPrimaryKeySelective(Parklot record) {
        return parklotMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Parklot record) {
        return parklotMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Parklot> selectAllNo() {
        return parklotMapper.selectAllNo();
    }

    @Override
    public List<Parklot> selectAll() {
        return parklotMapper.selectAll();
    }
}
