package com.example.demo.controller;

import cn.hutool.core.util.IdUtil;
import com.example.demo.annotation.AnonymousAccess;
import com.example.demo.model.AuthUser;
import com.example.demo.model.ImgResult;
import com.example.demo.model.Users;
import com.example.demo.result.Result;
import com.example.demo.utils.EncryptUtils;
import com.example.demo.utils.Exception.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import com.example.demo.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@CrossOrigin
@Api(value = "登陆")
@RestController
public class UserController {
    private String codeKey;
    private final RedisService redisService;
    @Autowired
    private UsersService userService;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    public UserController(RedisService redisService ) {
        this.redisService = redisService;
    }

    @PostMapping(value = "api/login")
    @AnonymousAccess
    public Result login(@Validated @RequestBody AuthUser authorizationUser) throws Exception {
        // 对 html 标签进行转义，防止 XSS 攻击
        String code = redisService.getCodeVal(authorizationUser.getUuid());
        redisService.delete(authorizationUser.getUuid());
        if (StringUtils.isBlank(code)) {
            return new Result(60204, null, "验证码已过期");
        }
        if (StringUtils.isBlank(authorizationUser.getCode()) || !authorizationUser.getCode().equalsIgnoreCase(code)) {
            return new Result(60204, null, "验证码错误");

        }
        String username, password, token;
        username = HtmlUtils.htmlEscape(authorizationUser.getUsername());
        Integer id = userService.findIdByUsername(username);
        Users user = userService.findByid(id);
        if (user!=null) {
            password = EncryptUtils.desEncrypt(authorizationUser.getPassword());
            if (Objects.equals(password, user.getPassword())) {
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

        return new Result(60204, null, "账号密码错误");
    }

    @ApiOperation("获取验证码")
    @AnonymousAccess
    @GetMapping(value = "api/code")
    public String getCode(){
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果：5
        String result = captcha.text();
        String uuid = codeKey + IdUtil.simpleUUID();
        redisService.saveCode(uuid,result);
        //return new ImgResult(captcha.toBase64(),uuid);
          Map<String, String> data = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();

        data.put("img",captcha.toBase64());
        data.put("uuid",uuid);
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        jsonObject.put("data",data);
        return jsonObject.toString();
    }

    @PostMapping(value = "api/register")
    public Result register(@RequestBody Users requestUser) throws Exception {
        String username = requestUser.getUsername();
        // username = HtmlUtils.htmlEscape(username);
        String password = EncryptUtils.desEncrypt(requestUser.getPassword());
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
    public String getUsers() throws Exception {
        JSONObject  jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Users> users = userService.findAll();
        List<Map<String,String>> list = new ArrayList<>();
        for(Users u : users){
            Map<String, String> data = new HashMap<>();
            data.put("id",u.getId().toString());
            data.put("key",u.getRole());
            data.put("username",u.getUsername());
            data.put("password",EncryptUtils.desDecrypt(u.getPassword()));
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

