package com.example.demo.park;

import com.example.demo.utils.GenerateNumber;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {


    public static void main(String[] args) {


        // 创建数组型缓冲等待队列
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 50, 50, TimeUnit.MILLISECONDS, bq);


        // 创建3个任务
        Runnable t1 = new TempThread();
        Runnable t12 = new TempThread2();
        Runnable t2 = new TempThread();
        Runnable t22 = new TempThread2();
        Runnable t3 = new TempThread();
        Runnable t32 = new TempThread2();
        for (int i = 0; i < 3; i++) {
            tpe.execute(t1);
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 3个任务在分别在3个线程上执行
        for (int i = 0; i < 10; i++) {
            tpe.execute(t1);
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tpe.execute(t12);
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tpe.execute(t2);
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tpe.execute(t22);
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 关闭自定义线程池
        tpe.shutdown();

    }
}


class TempThread implements Runnable {

    boolean[] a = PlarkingLot.getA();
    String[] num = PlarkingLot.getB();

    @Override
    public void run() {
        String carnumber = null;
        carnumber = GenerateNumber.generateCarID();
        boolean flag = false;
        int i = 0;
        int[] b = AR.suiji();
        for (i = 0; i < 50; i++) {
            if (a[b[i]] == false) {
                a[b[i]] = true;
                num[b[i]] = carnumber;
                i = b[i] + 1;
                flag = true;
                break;
            }

        }


        if (flag == true) {
            System.out.println("停车成功，" + "停入车位为" + i + "号车位 " + "停入车辆车牌号：" + carnumber);
            PlarkingLot.a = a;
            //		PlarkingLot.list=list;
            PlarkingLot.setB(num);
        } else {
            System.out.println("无车位，停车失败");
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        // 打印正在执行的缓存线程信息
        try {
            // sleep一秒保证3个任务在分别在3个线程上执行
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class TempThread2 implements Runnable {


    boolean[] a = PlarkingLot.getA();


    @Override
    public void run() {
        // TODO Auto-generated method stub
        //	ArrayList<String> list=PlarkingLot.getList();
        String[] num = PlarkingLot.getB();

        String carnumber = null;
        //carnumber=RandomCarNumber.generateCarID();
        boolean flag = false;
        int i = 0;


        int[] b = AR.suiji();


        while (true) {
            if (a[b[i]] == true) {
                a[b[i]] = false;
                //	carnumber=list.get(0);
                carnumber = num[b[i]];
                i = b[i] + 1;
                flag = true;
                break;
            }
            i++;
        }

        if (flag == true) {
            System.out.println("车辆离开成功，车辆离开车位为：" + i + "号车位  " + "离开车辆车牌号：" + carnumber);
            PlarkingLot.a = a;
            //	if(list!=null) {
            //	list.remove(0);}
            PlarkingLot.setB(num);
        } else {
            System.out.println("无车辆，无车辆离开");
            try {
                // sleep一秒保证3个任务在分别在3个线程上执行
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            // sleep一秒保证3个任务在分别在3个线程上执行
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


