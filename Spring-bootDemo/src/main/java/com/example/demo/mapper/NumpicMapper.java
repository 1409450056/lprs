package com.example.demo.mapper;

import com.example.demo.model.Numpic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value="NumpicDAO")

public interface NumpicMapper {
    int deleteByPrimaryKey(String number);

    int insert(Numpic record);

    int insertSelective(Numpic record);

    List<Numpic> selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Numpic record);

    int updateByPrimaryKey(Numpic record);

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByPrimaryKey(String number);

    List<Numpic> selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);

    String SelectNumberByUrl(String url);

    String selectUrlBynumber(String number);

    int selectMarkBynumber(String number);
}
