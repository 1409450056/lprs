package com.example.demo.controller;

import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

@CrossOrigin
@Api(value = "订单管理")
@RestController
public class OrderController {
    @Autowired
    private OrdersService orderService;

    @PostMapping(value = "api/addOrder")
    public String addOrder(@RequestBody Orders requestOrder) {
        JSONObject jsonObject = new JSONObject();
        String number = requestOrder.getNumber();
        Date orderDeploytime = requestOrder.getDeploytime();
        Integer orderStatus = requestOrder.getStatus();
        List<Integer> statusList = orderService.getAllStatus(number);
        for(int i : statusList){
            if(i==0){
                jsonObject.put("code",50000);
                jsonObject.put("message","插入失败，车辆已入库");
                return jsonObject.toString();
            }
        }
        System.out.println("insert: "+number+" "+orderDeploytime+ " " +  orderStatus);
        Orders aa=new Orders(null,number,orderDeploytime,null,orderStatus);
        orderService.insert(aa);
        jsonObject.put("code",20000);
        jsonObject.put("message","插入成功");
        return jsonObject.toString();
    }

    @DeleteMapping(value = "api/deleteOrder")
    public String deleteOrder(@RequestParam int orderNo) {
        JSONObject jsonObject = new JSONObject();
        Orders o = orderService.selectByPrimaryKey(orderNo);
        if(o!=null) {
            orderService.deleteByPrimaryKey(orderNo);
            System.out.println("删除订单:"+orderNo);
            jsonObject.put("code",20000);
            jsonObject.put("message","删除成功");
            return jsonObject.toString();
        }
        jsonObject.put("code",50000);
        jsonObject.put("message","删除失败，订单不存在");
        return jsonObject.toString();

    }


    @GetMapping(value = "api/selectOrderBynumber")
    public String selectByOrderNo(@RequestParam int orderNo) {
        JSONObject jsonObject = new JSONObject();
        Orders o = orderService.selectByPrimaryKey(orderNo);
        if(o!=null) {
            jsonObject.put("code",20000);
            Map<String, String> data = new HashMap<>();
            data.put("orderNo",o.getOrderno().toString());
            data.put("deployTime",o.getDeploytime().toString());
            data.put("number",o.getNumber());
            if(o.getLefttime()!=null) {
                data.put("leftTime", o.getLefttime().toString());
            }else{
                data.put("leftTime","");
            }
            data.put("status",o.getStatus().toString());
            jsonObject.put("data",data);
            return jsonObject.toString();
        }
        jsonObject.put("code",50000);
        jsonObject.put("message","订单不存在");
        return jsonObject.toString();

    }

    @GetMapping(value = "api/getOrders")
    public String getOrders() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","20000");
        List<Orders> orders = orderService.selectAll();
        List<Map<String,String>> orderList = new ArrayList<>();
        for(Orders o : orders){
            Map<String, String> data = new HashMap<>();
            data.put("orderNo",o.getOrderno().toString());
            data.put("deployTime",o.getDeploytime().toString());
            data.put("number",o.getNumber());
            if(o.getLefttime()!=null) {
                data.put("leftTime", o.getLefttime().toString());
            }else{
                data.put("leftTime","");
            }
            data.put("status",o.getStatus().toString());
            orderList.add(data);
        }
        jsonObject.put("data",orderList);
        return jsonObject.toString();
    }

    @PostMapping(value = "api/updateOrder")
    public String updateOrder(@RequestBody Orders requestOrder){
        JSONObject jsonObject = new JSONObject();
        String number = requestOrder.getNumber();
        Date orderLefttime = requestOrder.getLefttime();
        System.out.println("update"+number+orderLefttime);
        orderService.updateByPrimaryKey(requestOrder);
        return jsonObject.toString();

    }

    @PostMapping(value = "api/finishOrder")
    public String finishOrder(@RequestBody Orders requestOrder) {
        JSONObject jsonObject = new JSONObject();
        Orders o = orderService.selectByPrimaryKey(requestOrder.getOrderno());
        if(o.getStatus()==1){
            jsonObject.put("code",50000);
            jsonObject.put("message","提交失败，订单已完成");
            return jsonObject.toString();
        }
        if(o!=null) {
            String number = requestOrder.getNumber();
            Date orderLefttime = requestOrder.getLefttime();
            System.out.println("update" + number + orderLefttime);
            requestOrder.setStatus(1);
            orderService.finishOrder(requestOrder);
            jsonObject.put("code",20000);
            jsonObject.put("message","订单完成");
            return jsonObject.toString();
        }
        jsonObject.put("code",50000);
        jsonObject.put("message","订单不存在");
        return jsonObject.toString();

    }
}
