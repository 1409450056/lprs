package com.example.demo.controller;

import com.example.demo.mapper.ParklotMapper;
import com.example.demo.model.Parklot;
import com.example.demo.service.ParklotService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(value = "停车场")
@RestController
public class ParklotController {

    private static ParklotMapper parklot1;
    @Autowired
    ParklotService parklot;


    @ApiOperation(value = "获取所有空的车位信息")
    @GetMapping(value = "api/getNullpark")
    public String getNullpark() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        List<Parklot> Nullpark = parklot.selectAllNo();
        List<Map<String, String>> ParklotList = new ArrayList<>();
        for (Parklot Nullparks : Nullpark) {
            Map<String, String> data = new HashMap<>();
            data.put("no", Nullparks.getNo().toString());
            if ((Nullparks.getNumber())!=null) {
                data.put("number", Nullparks.getNumber().toString());

            }
            ParklotList.add(data);
        }
        jsonObject.put("data", ParklotList);
        return jsonObject.toString();

    }


    public static List<Integer> getNullpark2() {
        List<Parklot> Nullpark = parklot1.selectAllNo();
        List<Integer> ParklotList = new ArrayList<>();
        for (Parklot Nullparks : Nullpark) {
            ParklotList.add(Nullparks.getNo());
            System.out.println(Nullparks.getNo());
        }

        return ParklotList;
    }


    @ApiOperation(value = "更新车位信息")
    @PostMapping(value = "api/updateParklot")
    public String updateParklot(@RequestBody Parklot requestOrder) {
        JSONObject jsonObject = new JSONObject();
        String number = requestOrder.getNumber();
        jsonObject.put("message", "车库信息已修改");
        parklot.updateByPrimaryKey(requestOrder);
        return jsonObject.toString();

    }

    @ApiOperation(value = "获取所有车位的信息")
    @GetMapping(value = "api/getAllpark")
    public String getAllpark() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        List<Parklot> Nullpark = parklot.selectAll();
        List<Map<String, String>> ParklotList = new ArrayList<>();
        for (Parklot Nullparks : Nullpark) {
            Map<String, String> data = new HashMap<>();
            data.put("no", Nullparks.getNo().toString());
            if ((Nullparks.getNumber())!=null) {
                data.put("number", Nullparks.getNumber().toString());

            }
            ParklotList.add(data);

        }

        jsonObject.put("data", ParklotList);
        return jsonObject.toString();

    }


}
