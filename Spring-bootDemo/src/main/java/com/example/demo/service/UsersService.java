package com.example.demo.service;
import com.example.demo.model.Users;
import java.util.List;
public interface UsersService {
    Users findByid(int id);

    List<Users> findAll();

    void addUser(Users record);

    void deleteUser(int id);

    void updateUser(Users record);

    String getRole(int id);

    Integer findIdByUsername(String username);
}

