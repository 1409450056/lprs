package com.example.demo.mapper;

import com.example.demo.model.Numpic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NumpicMapper {
    int deleteByPrimaryKey(String number);

    int insert(Numpic record);

    int insertSelective(Numpic record);

    List<Numpic> selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Numpic record);

    int updateByPrimaryKey(Numpic record);
<<<<<<< HEAD

    List<Numpic> selectByMark(int mark);

    List<Numpic> selectAllByMarkOrder();

    int updateMarkByPrimaryKey(Numpic record);
    
    List<Numpic> selectUrlByPrimaryKey(String number);

    int selectMarkByPrimaryKey(String number);

    String SelectNumberByUrl(String url);
=======
>>>>>>> c4b027e1892ca64f087f6007a68489ff32e6df5d
}