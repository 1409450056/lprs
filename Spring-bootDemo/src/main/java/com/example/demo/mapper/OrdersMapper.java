package com.example.demo.mapper;

import com.example.demo.model.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="OrderDAO")

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

    Orders selectByNumber(String number);

}
