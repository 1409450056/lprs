package com.example.demo.mapper;

import com.example.demo.model.Orderprice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderpriceMapper {
    int deleteByPrimaryKey(Integer orderno);

    int insert(Orderprice record);

    int insertSelective(Orderprice record);

    Orderprice selectByPrimaryKey(Integer orderno);

    int updateByPrimaryKeySelective(Orderprice record);

    int updateByPrimaryKey(Orderprice record);
}