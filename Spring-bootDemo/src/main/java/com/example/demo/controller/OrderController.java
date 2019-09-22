package com.example.demo.controller;

import com.example.demo.mapper.OrdersMapper;
import com.example.demo.model.Orders;
import com.example.demo.service.OrdersService;
import io.swagger.annotations.Api;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Api(value = "订单管理")
@RestController
public class OrderController {

    /**
     *
     *
     * */

    @Autowired
    private OrdersService orderService;



    @PostMapping(value = "api/insert")
    public Orders insert(@RequestBody Orders requestOrder) {
        String orderNumber = requestOrder.getNumber();
        orderNumber = HtmlUtils.htmlEscape(orderNumber);
        Date orderDeploytime = requestOrder.getDeploytime();
  //      orderDeploytime = HtmlUtils.htmlEscape(orderDeploytime);
        Date orderLefttime = requestOrder.getLefttime();
    //    orderLefttime = HtmlUtils.htmlEscape(orderLefttime);
        Byte orderFinished = requestOrder.getFinished();
    //    orderFinished = HtmlUtils.htmlEscape(orderFinished);
        System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);
        Orders aa=new Orders(orderNumber,orderDeploytime,orderLefttime,orderFinished);

        orderService.insert(aa);

        return null;

     //   String password = requestUser.getPassword();
     //   System.out.println("register: " + username + " " + password);
     //   Users user = userService.findByUserName(username);
     //   if (user==null) {
     //       userService.addUser(new Users(username,password,"admin"));
     //       return new Result(20001, null, "注册成功");
      //  }
        //return new Result(60240, null, "注册失败，用户已存在");
    }

    @PostMapping(value = "api/deleteByPrimaryKey")
    public Orders deleteByPrimaryKey(@RequestParam String number) {



        orderService.deleteByPrimaryKey(number);
        return null;

    }



    @PostMapping(value = "api/insertSelective")
    public Orders insertSelective(@RequestBody Orders requestOrder) {
        String orderNumber = requestOrder.getNumber();
        orderNumber = HtmlUtils.htmlEscape(orderNumber);
        Date orderDeploytime = requestOrder.getDeploytime();
        //      orderDeploytime = HtmlUtils.htmlEscape(orderDeploytime);
        Date orderLefttime = requestOrder.getLefttime();
        //    orderLefttime = HtmlUtils.htmlEscape(orderLefttime);
        Byte orderFinished = requestOrder.getFinished();
        //    orderFinished = HtmlUtils.htmlEscape(orderFinished);
        System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);

        orderService.insertSelective(requestOrder);
        return null;

    }

    @PostMapping(value = "api/selectByPrimaryKey")
    public Orders selectByPrimaryKey(@RequestParam String number) {

     //   System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);
        orderService.selectByPrimaryKey(number);

        return null;

    }

    @PostMapping(value = "api/updateByPrimaryKeySelective")
    public Orders updateByPrimaryKeySelective(@RequestBody Orders requestOrder) {
        String orderNumber = requestOrder.getNumber();
        orderNumber = HtmlUtils.htmlEscape(orderNumber);
        Date orderDeploytime = requestOrder.getDeploytime();
        //      orderDeploytime = HtmlUtils.htmlEscape(orderDeploytime);
        Date orderLefttime = requestOrder.getLefttime();
        //    orderLefttime = HtmlUtils.htmlEscape(orderLefttime);
        Byte orderFinished = requestOrder.getFinished();
        //    orderFinished = HtmlUtils.htmlEscape(orderFinished);
        System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);

        orderService.updateByPrimaryKeySelective(requestOrder);
        return null;

    }


    @PostMapping(value = "api/selectAll")
    public List<Orders> selectAll() {



        List<Orders> aa=orderService.selectAll();

   //     System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);

        return aa;

    }

    @PostMapping(value = "api/updateByPrimaryKey")
    public Orders updateByPrimaryKey(@RequestBody Orders requestOrder) {
        String orderNumber = requestOrder.getNumber();
        orderNumber = HtmlUtils.htmlEscape(orderNumber);
        Date orderDeploytime = requestOrder.getDeploytime();
        //      orderDeploytime = HtmlUtils.htmlEscape(orderDeploytime);
        Date orderLefttime = requestOrder.getLefttime();
        //    orderLefttime = HtmlUtils.htmlEscape(orderLefttime);
        Byte orderFinished = requestOrder.getFinished();
        //    orderFinished = HtmlUtils.htmlEscape(orderFinished);
        System.out.println("insert: " + orderNumber + " " + orderDeploytime+ " " + orderLefttime+ " " + orderFinished);

        orderService.updateByPrimaryKey(requestOrder);

        return null;

    }
}
