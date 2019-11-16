package com.example.demo.controller;


import com.example.demo.model.Numpic;
import com.example.demo.model.Orderprice;
import com.example.demo.model.Orders;
import com.example.demo.service.NumpicService;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.calculatePrice;
import com.opslab.Opslab;
import com.opslab.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@Api(value = "车辆标记")
@RestController
public class NumpicController {
    @Autowired
    NumpicService numpic;

    /*
    * 按标记查找
    * */
    @ApiOperation(value="通过mark查找")
    @GetMapping(value = "api/selectByMark")
    public String selectByMark(@RequestParam int mark) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Numpic> ns=numpic.selectByMark(mark);
       // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();
        for(Numpic n : ns){
            Map<String, String> data = new HashMap<>();
            data.put("number",n.getNumber().toString());
            data.put("url",n.getUrl().toString());
            data.put("mark",n.getMark().toString());


            NumpicList.add(data);
        }
        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }

    /*
    * 按标记降序查找
    * */
    @ApiOperation(value="按标记降序查找")
    @GetMapping(value = "api/selectAllByMarkOrder")
    public String selectAllByMarkOrder() throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Numpic> ns=numpic.selectAllByMarkOrder();
        // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();
        for(Numpic n : ns){
            Map<String, String> data = new HashMap<>();
            data.put("number",n.getNumber().toString());
            data.put("url",n.getUrl().toString());
            data.put("mark",n.getMark().toString());
            NumpicList.add(data);
        }
        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }

    /*
    * 按车牌号修改标记值
    * */
    @ApiOperation(value="按车牌号修改标记值")
    @PostMapping(value = "api/updateAllByUrl")
    public String updateAllByUrl(@RequestBody  Numpic requestNupic) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        numpic.updateMarkByNumber(requestNupic);
       // System.out.println("i========= " + a);
        jsonObject.put("code",20000);
        jsonObject.put("message","车辆信息已修改");


          /*  jsonObject.put("code",50000);
            jsonObject.put("message","订单未能修改或修改次数超过一");
        */
        return jsonObject.toString();
    }


    @ApiOperation(value="通过number查找")
    @GetMapping(value = "api/selectByNumber")
    public String selectByNumber(@RequestParam String number) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Numpic> nss=numpic.selectByPrimaryKey(number);
        // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();
        for(Numpic ns : nss) {
            Map<String, String> data = new HashMap<>();
            data.put("number", ns.getNumber() + "");
            data.put("url", ns.getUrl().toString());
            data.put("mark", ns.getMark().toString());
            NumpicList.add(data);
        }
        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }

    @ApiOperation(value="按number查找url")
    @GetMapping(value = "api/selectUrlByNumber")
    public String selectUrlByNumber(@RequestParam String number) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        String ns=numpic.selectUrlByPrimaryKey(number);
        // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
      //  data.put("number",ns.getNumber().toString());
        data.put("url",ns.toString());
       // data.put("mark",ns.getMark().toString());
        NumpicList.add(data);

        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }


    @ApiOperation(value="按url查找mark")
    @GetMapping(value = "api/selectMarkByUrl")
    public String selectMarkByUrl(@RequestParam String url) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        int ns=numpic.selectMarkByPrimaryKey(url);
        // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
      //  data.put("number",ns.getNumber().toString());
     //   data.put("url",ns.getUrl().toString());
        data.put("mark",ns+"");
        NumpicList.add(data);

        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }

    @ApiOperation(value="增加车辆信息")
    @PostMapping(value = "api/addNumpic")
    public String addNumpic(@RequestBody Numpic requestNumpic) throws ParseException {
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("code",20000);
        jsonObject.put("message","插入成功");
        numpic.insertNumpic(requestNumpic);

        return jsonObject.toString();
    }

    @ApiOperation(value="删除车辆信息")
    @DeleteMapping(value = "api/deleteNumpic")
    public String deleteNumpic(@RequestParam String number) {
        JSONObject jsonObject = new JSONObject();
        List<Numpic> ns = numpic.selectByPrimaryKey(number);

        for(Numpic o : ns) {
            if (o != null) {
                numpic.delectNumpic(number);
                System.out.println("删除车辆车号:" + number);
                jsonObject.put("code", 20000);
                jsonObject.put("message", "删除成功");

            } else {
                jsonObject.put("code", 50000);
                jsonObject.put("message", "删除失败，车辆不存在");
            }
        }
        return jsonObject.toString();

    }

    @ApiOperation(value="按url查找number")
    @GetMapping(value = "api/selectNumberByUrl")
    public String selectNumberByUrl(@RequestParam String url) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        String ns=numpic.SelectNumberByUrl(url);
        // Orders o = orderService.selectByPrimaryKey(orderNo);
        List<Map<String,String>> NumpicList = new ArrayList<>();

        Map<String, String> data = new HashMap<>();
        //  data.put("number",ns.getNumber().toString());
        //   data.put("url",ns.getUrl().toString());
        data.put("Number",ns+"");
        NumpicList.add(data);

        jsonObject.put("data",NumpicList);
        return jsonObject.toString();
    }


}



