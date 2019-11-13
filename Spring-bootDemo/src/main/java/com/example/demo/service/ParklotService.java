package com.example.demo.service;

import com.example.demo.model.Parklot;

import java.util.List;

public interface ParklotService  {
    int deleteByPrimaryKey(Integer no);

    int insert(Parklot record);

    int insertSelective(Parklot record);

    Parklot selectByPrimaryKey(Integer no);

    int updateByPrimaryKeySelective(Parklot record);

    int updateByPrimaryKey(Parklot record);

    List<Parklot> selectAllNo();


}
