package com.example.demo.mapper;

import com.example.demo.model.Numpic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value="NumpicDAO")
public interface NumpicMapper {
    int deleteByPrimaryKey(String number);

    int insert(Numpic record);

    int insertSelective(Numpic record);

    Numpic selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Numpic record);

    int updateByPrimaryKey(Numpic record);
}
