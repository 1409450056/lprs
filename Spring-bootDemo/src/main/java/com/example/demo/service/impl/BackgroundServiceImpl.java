package com.example.demo.service.impl;

import com.example.demo.mapper.OrdersMapper;
import com.example.demo.model.Orders;
import com.example.demo.service.BackgroundService;
import com.example.demo.utils.GenerateNumber;
import com.opslab.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackgroundServiceImpl implements BackgroundService {
    @Autowired
    OrdersMapper ordersmapper;

    @Override
    public void generatorData() {
        GenerateNumber generator = new GenerateNumber();
        String number = generator.generateCarID();
    }

    @Override
    public void dropOrder(String number) {

    }

    @Override
    public void finishOrder(String number) {

    }

    @Override
    public void creatOrder(String number) {
        byte a = 0;
        String currentTime = DateUtil.currentDateTime();
        System.out.print(currentTime);
        try{
        // ordersmapper.insert(new Orders(number,DateUtil.dateTime(currentTime),a));
        System.out.println("插入"+number+currentTime);
        }catch(Exception e){
            System.err.println(e);
        }
    }

    @Override
    public Orders selectOrderBynumber(String number) {
        return null;
    }


}
