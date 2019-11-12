package com.example.demo;

import com.example.demo.service.BackgroundService;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoStart implements CommandLineRunner {



    @Autowired
    OrdersService oss;

    @Override
    public void run(String... args) throws Exception {
        generateNumberThread gT = new generateNumberThread();
        CarLeftThread ct = new CarLeftThread();
       // gT.start();
       // ct.start();
List sb = oss.selectAll();
System.out.println(sb);
    }

    class generateNumberThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i < 100) {

            }
        }
    }

    class CarLeftThread extends Thread {
        @Override
        public void run() {
            int i = 100;
            while (i < 200) {

            }
        }
    }
}
