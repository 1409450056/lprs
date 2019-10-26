package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.result.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import com.example.demo.service.*;
import springfox.documentation.spring.web.json.Json;

import java.util.*;


@CrossOrigin
@Api(value = "登陆")
@RestController
public class UserController {
    @Autowired
    private UsersService userService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @PostMapping(value = "api/login")
    public Result login(@RequestBody Users requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username, password, token;
        username = HtmlUtils.htmlEscape(requestUser.getUsername());
        Integer id = userService.findIdByUsername(username);
        Users user = userService.findByid(id);
        if (user!=null) {
            password = user.getPassword();
            if (Objects.equals(password, requestUser.getPassword())) {
                System.out.println("login: "+username + " " + password);
                Map<String, String> data = new HashMap<>();
                if(userService.getRole(id).equals("admin")) {
                    token = "admin-token";
                }else{
                    token = "user-token";
                }
                data.put("token", token);
                return new Result(20000, data, null);
            }
        }
        String message = "账号密码错误";
        Map<String, String> data = new HashMap<>();
        return new Result(60204, data, "账号密码错误");
    }

    @PostMapping(value = "api/register")
    public Result register(@RequestBody Users requestUser) {
        String username = requestUser.getUsername();
        // username = HtmlUtils.htmlEscape(username);
        String password = requestUser.getPassword();
        System.out.println("注册: " + username + " " + password);
        int id = userService.findIdByUsername(username);
        Users user = userService.findByid(id);
        String role = null;
        if(requestUser.getRole().equals("user")){
            role = "user";
        }else{
            role = "admin";
        }

        if (user==null) {
            userService.addUser(new Users(username,password,role));
            return new Result(20001, null, "添加成功");
        }
        return new Result(60240, null, "添加失败，用户已存在");
    }

    @DeleteMapping(value = "api/deleteRole")
    public String deleteUser(@RequestParam("id") int id) {
        JSONObject  jsonObject = new JSONObject();
        Users user = userService.findByid(id);
        if (user!=null) {
            userService.deleteUser(id);
            jsonObject.put("code",20000);
            jsonObject.put("message","用户已删除");
            System.out.println("删除:"+user.getUsername());
            return jsonObject.toString();
        }
        jsonObject.put("code",50000);
        jsonObject.put("message","用户不存在");
        return jsonObject.toString();
    }

    @ResponseBody
    @GetMapping(value = "api/getUsers")
    public String getUsers() {
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Users> users = userService.findAll();
        List<Map<String,String>> list = new ArrayList<>();
        for(Users u : users){
            Map<String, String> data = new HashMap<>();
            data.put("id",u.getId().toString());
            data.put("key",u.getRole());
            data.put("username",u.getUsername());
            data.put("password",u.getPassword());
            list.add(data);
        }
        jsonObject.put("data",list);
        return jsonObject.toString();
    }

    @PutMapping(value = "api/updateUsers")
    public String updateUser(@RequestParam("id") int id, @RequestBody Users requestUser){
        JSONObject  jsonObject = new JSONObject();
        Users user = userService.findByid(id);
        if (user!=null) {
            userService.updateUser(requestUser);
            jsonObject.put("code", 20000);
            jsonObject.put("message","更新成功");
            System.out.println("更新用户:"+requestUser.getUsername());
            return jsonObject.toString();

        }
        jsonObject.put("code",50000);
        jsonObject.put("message","用户不存在");
        return jsonObject.toString();
    }









}

