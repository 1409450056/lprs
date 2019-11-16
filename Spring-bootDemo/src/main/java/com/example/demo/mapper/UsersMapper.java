package com.example.demo.mapper;

import com.example.demo.model.Users;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    List<Users> selectAll();

    Integer selectidByUsername(String username);

}
