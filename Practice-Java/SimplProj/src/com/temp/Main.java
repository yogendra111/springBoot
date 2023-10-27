package com.temp;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		int a =10;
		HashMap<Integer, Integer> tempMap = new HashMap<>();
		tempMap.put(2,4);
		tempMap.put(3, 10);
		
		abc(tempMap, a);

		System.out.println("main : " + a);
		System.out.println(tempMap);
		
	}
	
	public static void abc(HashMap<Integer, Integer> tempMap,int a) {
		a=20;
		tempMap.put(3,4);
		tempMap.put(5, 10);
		tempMap.put(3, 12);
		System.out.println("abc : " + a);
		
	}
	
}
