package com.example.demo.mapper;

import com.example.demo.model.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="OrdersDao")

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
<<<<<<< HEAD

    Orders selectByNumber(String number);
=======
>>>>>>> c4b027e1892ca64f087f6007a68489ff32e6df5d
}
