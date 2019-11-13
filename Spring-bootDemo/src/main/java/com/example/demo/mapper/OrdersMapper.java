package com.example.demo.mapper;

import com.example.demo.model.Orders;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderno);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderno);

    int updateByPrimaryKeySelective(Orders record);

    int finishOrder(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectAll();

    List<Integer> getAllStatus(String number);

    List<Orders> selectFinished();

    int getOrderNo(String number);

    Orders selectByPrimaryKey1(String number);
}
