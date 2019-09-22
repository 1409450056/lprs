package com.example.demo.mapper;

import com.example.demo.model.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(String number);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);
}