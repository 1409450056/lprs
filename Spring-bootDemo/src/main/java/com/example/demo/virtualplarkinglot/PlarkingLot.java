package com.example.demo.virtualplarkinglot;

import com.example.demo.model.Parklot;
import com.example.demo.service.ParklotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlarkingLot {



	static boolean []a= new boolean[50];
	static String []b=new String[50];
	static ArrayList<String> list=new ArrayList<String>();
	
	public static void Initax (boolean []a) {
		int i=0;
		for(i=0;i<50;i++) {
			a[i]=false;
		}
	}

	public static void Initax1 (List<Parklot> parklot){
		int i=0;
		for(i=0;i<50;i++) {
			a[i]=true;
		}
		i=0;
		List<Parklot> Nullpark=parklot;
		for(Parklot Nullparks:Nullpark){
			if(Nullparks.getNumber()!=null){
				i=Nullparks.getNo()-1;
				b[i]=Nullparks.getNumber();
				a[i]=true;

			}else{
			i=Nullparks.getNo()-1;
			a[i]=false;
			}
		}
	}
	
	public PlarkingLot(boolean[] a) {
		super();
		PlarkingLot.a = a;
	}
	
	public PlarkingLot() {
		super();
	}

	public static boolean[] getA() {
		return a;
	}

	public void setA(boolean[] a) {
		PlarkingLot.a = a;
	}

	public static ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		PlarkingLot.list = list;
	}

	
	public static String[] getB() {
		return b;
	}

	public static void setB(String[] b) {
		PlarkingLot.b = b;
	}

	

	
	
}
