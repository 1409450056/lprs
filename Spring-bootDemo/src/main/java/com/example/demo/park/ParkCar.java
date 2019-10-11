package com.example.demo.park;

public class ParkCar implements Runnable {
    Car car;
    Park park;

    public ParkCar(Car car, Park park) {
        this.car = car;
        this.park = park;
    }

    @Override
    public void run() {
        while (true) {
            int i = 0;
            synchronized (park) {
                {
                    if (car.getPosition() == 0) {
                        for (i = 0; i < park.park.length; i++) {
                            if (park.park[i]) {
                                continue;
                            } else {
                                System.out.println("车 " + car.getNumber() + "成功停在" + (i + 1) + "号停车位上");
                                park.park[i] = true;
                                car.setPosition(i);
                                break;
                            }
                        }
                        if (i == 3) {
                            System.out.println("停车场车位已满，车 " + car.getNumber() + "停车失败");
                        }
                    }


                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

                synchronized (park) {
                    if (car.getPosition() != 0) {
                        park.park[car.getPosition()] = false;
                        car.setPosition(0);
                        System.out.println("车 " + car.getNumber() + "已离开停车场");

                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }

            }
        }
    }

    public static class Test2 {
        public static void main(String []args)
        {
            Park p=new Park();
            Car a=new Car("111111");
            Car b=new Car("222222");
            Car c=new Car("333333");
            Car d=new Car("444444");
            Car e=new Car("555555");
            new Thread (new ParkCar(a,p)).start();
            new Thread (new ParkCar(c,p)).start();
            new Thread (new ParkCar(d,p)).start();
            new Thread (new ParkCar(e,p)).start();
            new Thread (new ParkCar(b,p)).start();

        }

    }

}
