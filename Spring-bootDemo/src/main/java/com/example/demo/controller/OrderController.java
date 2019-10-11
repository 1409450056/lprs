package com.example.demo.controller;

import com.example.demo.model.Orderprice;
import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;
import com.example.demo.utils.calculatePrice;
import com.opslab.Opslab;
import com.opslab.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@Api(value = "订单管理")
@RestController
public class OrderController {
    @Autowired
    private OrdersService orderService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @PostMapping(value = "api/addOrder")
    public String addOrder(@RequestBody Orders requestOrder) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        String number = requestOrder.getNumber();
        /*Date orderDeploytime = requestOrder.getDeploytime();
        Integer orderStatus = requestOrder.getStatus();*/
        Date orderDeploytime = DateUtil.date(DateUtil.currentDate());
        Integer orderStatus = 0;
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

        Orderprice op = new Orderprice();
        op.setPrice(0);
        op.setOrderno(orderService.getOrderNo(requestOrder.getNumber()));
        orderService.insertPrice(op);

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
            if(o.getOrderprice().getPrice()!=null){
                data.put("price",o.getOrderprice().getPrice().toString());
            }else{
                data.put("price","");
            }
            data.put("status",o.getStatus().toString());
            jsonObject.put("data",data);
            return jsonObject.toString();
        }
        jsonObject.put("code",50000);
        jsonObject.put("message","订单不存在");
        return jsonObject.toString();

    }

    @GetMapping(value = "api/getAllOrders")
    public String getAllOrders(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Orders> orders = orderService.selectAll();
        List<Map<String,String>> orderList = new ArrayList<>();
        for(Orders o : orders){
            Map<String, String> data = new HashMap<>();
            data.put("orderNo",o.getOrderno().toString());
            data.put("deployTime",sdf.format(o.getDeploytime()).toString());
            data.put("number",o.getNumber());
            if(o.getLefttime()!=null) {
                data.put("leftTime", sdf.format(o.getLefttime()).toString());
            }else{
                data.put("leftTime","");
            }
            if(o.getOrderprice().getPrice()!=null){
                data.put("price",o.getOrderprice().getPrice().toString());
            }else{
                data.put("price","");
            }
            data.put("status",o.getStatus().toString());
            orderList.add(data);
        }
        jsonObject.put("data",orderList);
        return jsonObject.toString();
    }


    @GetMapping(value = "api/getFinishedOrders")
    public String getFinishedOrders() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",20000);
        List<Orders> orders = orderService.selectFinished();
        List<Map<String,String>> orderList = new ArrayList<>();
        for(Orders o : orders){
            Map<String, String> data = new HashMap<>();
            data.put("orderNo",o.getOrderno().toString());
            data.put("deployTime",sdf.format(o.getDeploytime()).toString());
            data.put("number",o.getNumber());
            data.put("leftTime", sdf.format(o.getLefttime()).toString());
            data.put("price",o.getOrderprice().getPrice().toString());
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
        jsonObject.put("code",20000);
        jsonObject.put("message","订单已修改");
        System.out.println("update"+number+orderLefttime);
        orderService.updateByPrimaryKey(requestOrder);
        return jsonObject.toString();

    }

    @PostMapping(value = "api/finishOrder")
    public String finishOrder(@RequestBody Orders requestOrder) {
        JSONObject jsonObject = new JSONObject();
        Orders o = orderService.selectByPrimaryKey(requestOrder.getOrderno());

        if(o!=null) {
            if(o.getStatus()==1){
                jsonObject.put("code",50000);
                jsonObject.put("message","提交失败，订单已完成");
                return jsonObject.toString();
            }
            String number = requestOrder.getNumber();
            Date orderLefttime = requestOrder.getLefttime();
            System.out.println("update" + number + orderLefttime);
            requestOrder.setStatus(1);
            int price = calculatePrice.getPrice(o.getDeploytime(),o.getLefttime());
            Orderprice op = new Orderprice();
            op.setOrderno(o.getOrderno());
            op.setPrice(22);
            requestOrder.setOrderprice(op);
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