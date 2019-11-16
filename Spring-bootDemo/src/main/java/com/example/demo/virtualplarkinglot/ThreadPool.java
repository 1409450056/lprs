package com.example.demo.virtualplarkinglot;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@Component
public class ThreadPool implements CommandLineRunner {

	int times=1;


	//@Autowired
	//BackgroundService bgs;

	//@Autowired




	@Override
	public void run(String... args) throws Exception {

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
		// Runnable t4 = new TempThread();
		// Runnable t5 = new TempThread();
		// Runnable t6 = new TempThread();

		for(int i=0;i<3;i++) {
			tpe.execute(t1);
			try {
				// sleep一秒保证3个任务在分别在3个线程上执行
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 3个任务在分别在3个线程上执行
		for(int i=0;i<10;i++) {
			tpe.execute(t1);
			//  tpe.execute(t2);
			//   tpe.execute(t3);
			tpe.execute(t12);
			tpe.execute(t2);
			tpe.execute(t22);
			try {
				// sleep一秒保证3个任务在分别在3个线程上执行
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//  tpe.execute(t22);
			//   tpe.execute(t32);
		}
		// tpe.execute(t4);
		// tpe.execute(t5);
		// tpe.execute(t6);

		// 关闭自定义线程池
		tpe.shutdown();

	}

	}








	class TempThread implements Runnable {



		boolean []a= PlarkingLot.getA();
//		ArrayList<String> list=PlarkingLot.getList();
		String[] num=PlarkingLot.getB();

		    @Override
		    public void run() {

		    	/*
		    	/获取进来的车牌号码
		    	 */
		    	String carnumber=null;
		    	carnumber=RandomCarNumber.generateCarID();
		    	//初始值默认为flase来确认是否可停入停车场
		    	boolean flag=false;
		    	//设置遍历默认值
		    	int i=0;
		    	//生成随机值的数组来保证随机性
		    	int []b=AR.suiji();





		    	for(i=0;i<50;i++) {
		    		if(a[b[i]]==false) {
		    			a[b[i]]=true;
		    			num[b[i]]=carnumber;
			    		i=b[i]+1;

		    		//list.add(carnumber);
		    		flag=true;
		    		break;
		    		}

		    	}



		    	if(flag==true) {
		    		System.out.println("停车成功，" +"停入车位为"+i+"号车位 "  + "停入车辆车牌号："+carnumber);




		    		PlarkingLot.a=a;
		    //		PlarkingLot.list=list;
		    		PlarkingLot.setB(num);
		    	}else {
		   		System.out.println("无车位，停车失败");
		   	 try {
		            // sleep一秒保证3个任务在分别在3个线程上执行
		           Thread.sleep(1000);
		        } catch (InterruptedException e) {
		           e.printStackTrace();
		        }

		    	}



	        // 打印正在执行的缓存线程信息
		     //    System.out.println(Thread.currentThread().getName() + "正在被执行");
		        try {
	            // sleep一秒保证3个任务在分别在3个线程上执行
	           Thread.sleep(30000);
	        } catch (InterruptedException e) {
	           e.printStackTrace();
	        }
	   }

	}


	class TempThread2 implements Runnable{


		boolean []a= PlarkingLot.getA();



		@Override
		public void run() {
			// TODO Auto-generated method stub
		//	ArrayList<String> list=PlarkingLot.getList();
			String[] num=PlarkingLot.getB();

			String carnumber=null;
	    	//carnumber=RandomCarNumber.generateCarID();
	    	boolean flag=false;
	    	int i=0;



	    	int []b=AR.suiji();



	    	while(i<50) {
	    		if(a[b[i]]==true) {
	    			a[b[i]]=false;
	        	//	carnumber=list.get(0);
	    			carnumber=num[b[i]];
	        		i=b[i]+1;
	        		flag=true;
	        		break;
	    		}
	    		i++;
	    	}


	    	if(flag==true) {
	    		System.out.println("车辆离开成功，车辆离开车位为："+i+"号车位  "+"离开车辆车牌号："+carnumber);
	    		PlarkingLot.a=a;
	    	//	if(list!=null) {
	    	//	list.remove(0);}
	    		PlarkingLot.setB(num);
	    	}else {
	    		System.out.println("无车辆，无车辆离开");
	    		 try {
			            // sleep一秒保证3个任务在分别在3个线程上执行
			           Thread.sleep(1000);
			        } catch (InterruptedException e) {
			           e.printStackTrace();
			        }

	    	}





		try {
            // sleep一秒保证3个任务在分别在3个线程上执行
           Thread.sleep(500000);
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
	}

	}



   class TempThread3 implements Runnable{


	@Override
	public void run() {

	}
    }
