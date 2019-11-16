package com.example.demo.mapper;

import com.example.demo.model.Numpic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "NumpicDao")
public interface NumpicMapper {
    int deleteByPrimaryKey(String number);

    int insert(Numpic record);

    int insertSelective(Numpic record);

    Numpic selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Numpic record);

    int updateByPrimaryKey(Numpic record);

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByPrimaryKey(Numpic record);

    String selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);
}
