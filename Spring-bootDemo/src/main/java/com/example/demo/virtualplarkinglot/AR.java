package com.example.demo.virtualplarkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AR {
	//static int[] result1=suiji();
	
	  public static int[] suiji() {
	        int k = 50;
	        int n = 50;
	        List<Integer> numbers = new ArrayList<>();
	        for(int i = 0; i < n; i ++)
	            numbers.add(i + 1);
	        
	        int[] result = new int[k];
	        for(int i = 0; i < k; i ++){
	            int r = generateRandomIndex(n - i);
	  //          System.out.println( numbers.get(r));
	            
	            result[i] = numbers.get(r)-1;
	           
	            numbers.remove(r);
	        }

	   //     Arrays.sort(result);

			return result;
	    }


	public static int[] suiji2() {
		int k = 400;
		int n = 400;
		List<Integer> numbers = new ArrayList<>();
		for(int i = 0; i < n; i ++)
			numbers.add(i + 1);

		int[] result = new int[k];
		for(int i = 0; i < k; i ++){
			int r = generateRandomIndex(n - i);
			//          System.out.println( numbers.get(r));

			result[i] = numbers.get(r)-1;

			numbers.remove(r);
		}

		//     Arrays.sort(result);

		return result;
	}




	    private static int generateRandomIndex(int n){
	        Random random = new Random();
	        //return an int that between 0 and n, 0 included, n not included
	        return random.nextInt(n);
	    }

	
	
}
