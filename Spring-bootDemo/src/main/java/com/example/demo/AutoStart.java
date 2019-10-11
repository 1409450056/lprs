package com.example.demo;

import com.example.demo.service.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutoStart implements CommandLineRunner {



    @Autowired
    BackgroundService bgs;

    @Override
    public void run(String... args) throws Exception {
        generateNumberThread gT = new generateNumberThread();
        CarLeftThread ct = new CarLeftThread();
       // gT.start();
       // ct.start();
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
