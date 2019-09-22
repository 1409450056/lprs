package com.example.demo.controller;

import com.example.demo.service.BackgroundService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(value = "后台服务")
@RestController
public class BackGroundService {
    @Autowired
    BackgroundService backgroundService;
}
