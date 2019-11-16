package com.example.demo.park;

import java.util.ArrayList;

public class PlarkingLot {
	static boolean []a= new boolean[50];
	static String []b=new String[50];
	static ArrayList<String> list=new ArrayList<String>();

	public static void Initax (boolean []a) {
		int i=0;
		for(i=0;i<5;i++) {
			a[i]=false;
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
