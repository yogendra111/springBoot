package com.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import com.testclasses.DemoClass;

public class TestDemoClass {
	
	DemoClass obj=new DemoClass();
	
	@Test  
	public void testSum() {  
	    assertEquals(25,obj.sum(10, 15));
	} 

}
