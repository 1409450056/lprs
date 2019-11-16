package com.example.demo.virtualplarkinglot;

import com.example.demo.controller.OCRController;
import com.example.demo.controller.ParklotController;
import com.example.demo.model.Numpic;
import com.example.demo.model.Orderprice;
import com.example.demo.model.Orders;
import com.example.demo.model.Parklot;
import com.example.demo.service.NumpicService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ParklotService;
import com.example.demo.utils.calculatePrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.demo.virtualplarkinglot.PlarkingLot.*;

@Component
public class ThreadPool implements CommandLineRunner {

	int times=1;


	/*
	* 订单号
	* */
	int t1=1;



	int t2=0;
	//@Autowired
	//BackgroundService bgs;

	@Autowired
	NumpicService ncs;


	@Autowired
	ParklotService PlS;


	@Autowired
	private OrdersService orderService;


	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


	@Override
	public void run(String... args) throws Exception {

		BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
		// ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 50, 50, TimeUnit.MILLISECONDS, bq);


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		//    PlarkingLot.Initax1(ParklotServicelmpl.selectAllNo());
		/*file格式转MultipartFile格式
		 * */
	//	String adress=fengzhuang.fengzhuangaddress(times);
		/*
		 *需要给定第二个参数，作为本地地址存放下载文件存放的文件夹
		 * */
		//	File file1=null;
		//	file1=DownloadUrl.saveUrlAs(adress,"C:\\Users\\A\\11111","GET",times);;


		String heard="C://Users//A//11111/";
		String last=".jpg";

/*
		for(int i=351;i<=400;i++){
			String urladress=null;
			String urlnumber=null;
			String localadress=null;

			urladress=fengzhuang.fengzhuangaddress(i);
			localadress=heard+i+last;

			File filetest=new File(localadress);
			FileInputStream fileInputStream1=new FileInputStream(filetest);
			MultipartFile multipartFile1=new MockMultipartFile("copy"+filetest.getName(),filetest.getName(), APPLICATION_OCTET_STREAM.toString(),fileInputStream1);
			String numberrecord=null;
			numberrecord=OCRController.getPlate(multipartFile1);

			if(numberrecord!=null) {
				urlnumber = numberrecord;

			}
			Numpic numpic2=new Numpic(urlnumber,urladress,0);


			ncs.insertNumpic(numpic2);

  try {
	            // sleep一秒保证3个任务在分别在3个线程上执行
	           Thread.sleep(1000);
	        } catch (InterruptedException e) {
	           e.printStackTrace();
	        }


		}

*/




		//List<Parklot> aa= ParklotController.getNullpark();
		List<Parklot> aa= PlS.selectAll();
		Initax1(aa);

	//	Parklot aaa=aa.get(0);
	//	System.out.println(aaa.getNo().toString());





		//	jsonObject=OCRController.getCarInfo(multipartFile);
		//	jsonObject = jsonObject.getJSONObject("words_result");
		//	String number = jsonObject.getString("number");
		//	System.out.println(number);

		//	JSONObject jo =JSONObject.



		// 创建3个任务
		Runnable t1 = new TempThread(PlS,ncs,orderService);
		Runnable t12 = new TempThread2(PlS,ncs,orderService);
		Runnable t2 = new TempThread(PlS,ncs,orderService);
		Runnable t22 = new TempThread2(PlS,ncs,orderService);
		Runnable t3 = new TempThread(PlS,ncs,orderService);
		Runnable t32 = new TempThread2(PlS,ncs,orderService);
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
				Thread.sleep(30000);
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







    @Component
	class TempThread implements Runnable {

		private ParklotService PlS;
		private NumpicService ncs;
		private OrdersService orderService;



		public TempThread(ParklotService plS, NumpicService ncs, OrdersService orderService) {
			PlS = plS;
			this.ncs = ncs;
			this.orderService = orderService;
		}

		boolean []a= getA();
//		ArrayList<String> list=PlarkingLot.getList();
		String[] num= getB();

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


				int []b2=AR.suiji2();
				String urladress=null;
				urladress=fengzhuang.fengzhuangaddress(b2[0]);
		    //	System.out.println(urladress);
				String getnumber=ncs.SelectNumberByUrl(urladress);

				int bianjishiyong=1;
				boolean flagss=true;
				while(flagss){
					if(getnumber==null){
						urladress=fengzhuang.fengzhuangaddress(b2[bianjishiyong]);
						getnumber=ncs.SelectNumberByUrl(urladress);
						i++;
					}else{
						flagss=false;
					}
				}



				carnumber=getnumber;
			//	System.out.println(getnumber);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date =new Date();
				System.out.println(date);

				Orders requestOrder=new Orders(0,carnumber,date,date,0);



		    	for(i=0;i<50;i++) {
		    		if(a[b[i]]==false) {

						boolean flags=true;

						String number = requestOrder.getNumber();
        /*Date orderDeploytime = requestOrder.getDeploytime();
        Integer orderStatus = requestOrder.getStatus();*/
						Date orderDeploytime = null;
						orderDeploytime=requestOrder.getDeploytime();

						Integer orderStatus = 0;
						List<Integer> statusList = orderService.getAllStatus(number);
						for(int iii : statusList){
							if(iii==0){

								flags=false;
								//					jsonObject.put("code",50000);
								//					jsonObject.put("message","插入失败，车辆已入库");

							}
						}
						if(flags==true) {
						//	System.out.println("insert: " + number + " " + orderDeploytime + " " + orderStatus);
							Orders aa = new Orders(null, number, orderDeploytime, null, orderStatus);
							orderService.insert(aa);

							Orderprice op = new Orderprice();
							op.setPrice(0);
							op.setOrderno(orderService.getOrderNo(requestOrder.getNumber()));
							orderService.insertPrice(op);

							//			jsonObject.put("code",20000);
							//			jsonObject.put("message","插入成功");

							System.out.println("添加订单成功"+"  "+"订单车牌号为："+number);

							a[b[i]] = true;
							num[b[i]] = carnumber;
							i = b[i] + 1;
							//list.add(carnumber);
							flag = true;
							break;

						}


		    		}

		    	}



		    	if(flag==true) {

		    		Parklot requestparklot=new Parklot(i,carnumber);
					PlS.updateByPrimaryKey(requestparklot);
		    		System.out.println("停车成功，" +"停入车位为"+i+"号车位 "  + "停入车辆车牌号："+carnumber);




		    		PlarkingLot.a=a;
		    //		PlarkingLot.list=list;
		    		setB(num);
		    	}else {
		   		System.out.println("车辆已存在，停车失败");
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
	           Thread.sleep(50000);
	        } catch (InterruptedException e) {
	           e.printStackTrace();
	        }
	   }

	}


	class TempThread2 implements Runnable{

		private ParklotService PlS;
		private NumpicService ncs;
		private OrdersService orderService;

		public TempThread2(ParklotService plS, NumpicService ncs, OrdersService orderService) {
			PlS = plS;
			this.ncs = ncs;
			this.orderService = orderService;
		}

		boolean []a= getA();

		String[] num= getB();





		@Override
		public void run() {
			// TODO Auto-generated method stub
		//	ArrayList<String> list=PlarkingLot.getList();
		//	String[] num= getB();

			String carnumber=null;
	    	//carnumber=RandomCarNumber.generateCarID();
	    	boolean flag=false;
	    	int i=0;


	    	int []b=AR.suiji();


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date =new Date();
			System.out.println(date);

			Orders requestOrder=new Orders(0,carnumber,date,date,0);



	    	while(i<50) {
	    		if(a[b[i]]==true) {


					carnumber=num[b[i]];
					Orders getvalue=orderService.selectByNumber(carnumber);
					Integer orderNo = getvalue.getOrderno();
					Orders o = orderService.selectByPrimaryKey(orderNo);
					// Orders o = orderService.selectByPrimaryKey(orderNo);
					if(o!=null) {
						if(o.getStatus()==1){
							System.out.println("提交失败，订单已完成");
						//	jsonObject.put("code",50000);
						//	jsonObject.put("message","提交失败，订单已完成");

						}else {
							String number = requestOrder.getNumber();
							Date orderLefttime = new Date();
							System.out.println("update" + number + orderLefttime);
							requestOrder.setStatus(1);
							int price = calculatePrice.getPrice(o.getDeploytime(), orderLefttime);
							Orderprice op = new Orderprice();
							op.setOrderno(o.getOrderno());
							op.setPrice(price);
							requestOrder.setOrderprice(op);
							requestOrder.setOrderno(orderNo);
							requestOrder.setLefttime(orderLefttime);
							orderService.finishOrder(requestOrder);
						//	jsonObject.put("code", 20000);
						//	jsonObject.put("message", "订单完成");
							System.out.println("订单完成"+"   "+"完成订单编号为:"+orderNo);
						}
					}else {
						//jsonObject.put("code", 50000);
						//jsonObject.put("message", "订单不存在");
						System.out.println("订单不存在");
					}





	    			a[b[i]]=false;
	        	//	carnumber=list.get(0);
	    			carnumber=num[b[i]];
					num[b[i]]=null;

	        		i=b[i]+1;
	        		flag=true;
	        		break;
	    		}
	    		i++;
	    	}


	    	if(flag==true) {

				Parklot requestparklot=new Parklot(i,null);
				PlS.updateByPrimaryKey(requestparklot);
	    		System.out.println("车辆离开成功，车辆离开车位为："+i+"号车位  "+"离开车辆车牌号："+carnumber);
	    		PlarkingLot.a=a;
	    	//	if(list!=null) {
	    	//	list.remove(0);}
	    		setB(num);
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
           Thread.sleep(50000);
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
