package com.example.demo.mapper;

import com.example.demo.model.Orderprice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="OrderpriceDao")
public interface OrderpriceMapper {
    int deleteByPrimaryKey(Integer orderno);

    int insert(Orderprice record);

    int insertSelective(Orderprice record);

    Orderprice selectByPrimaryKey(Integer orderno);

    int updateByPrimaryKeySelective(Orderprice record);

    int updateByPrimaryKey(Orderprice record);
}
