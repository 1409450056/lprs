package com.example.demo.mapper;

import com.example.demo.model.Parklot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParklotMapper {
    int deleteByPrimaryKey(Integer no);

    int insert(Parklot record);

    int insertSelective(Parklot record);

    Parklot selectByPrimaryKey(Integer no);

    int updateByPrimaryKeySelective(Parklot record);

    int updateByPrimaryKey(Parklot record);
}