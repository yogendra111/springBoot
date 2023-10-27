package com.temp;

class temp{
	int i;
	public temp(int i){
		this.i = i;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	@Override
	public String toString() {
		return "val " + i;
	}
}
public class Cloning {

	public static void main(String[] args) {
		temp t1 = new temp(8);
		temp t2 = t1;
		
		System.out.println(t1 + " + " + t2);
		
		t2.setI(12);

		System.out.println(t1 + " + " + t2);
		
	}
	
}
