package com.example.demo.model;

import java.io.Serializable;

public class Users implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private String role;


    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
