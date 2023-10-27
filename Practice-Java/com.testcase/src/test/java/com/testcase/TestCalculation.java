package com.testcase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.testclasses.Calculation;

public class TestCalculation {
	  
    @BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
        System.out.println("before class");  
    }  
    @Before  
    public void setUp() throws Exception {  
        System.out.println("before");  
    } 
    @Test  
    public void testFindMax(){  
    	System.out.println("Test case find max");
        assertEquals(4,Calculation.findMax(new int[]{1,3,4,2}));  
        assertEquals(-1,Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));  
    }
    @Test  
    public void testCube(){  
        System.out.println("Test case cube");  
        assertEquals(27,Calculation.cube(3));  
    }
    @After  
    public void tearDown() throws Exception {  
        System.out.println("after");  
    }
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("after class");  
    } 

}