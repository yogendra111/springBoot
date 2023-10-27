package com.test.testcase;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.test.CalculationTest;

public class TestCase {

    @Test
    public void testFindMax(){  
        assertEquals(4,CalculationTest.findMax(new int[]{1,3,4,2}));  
        assertEquals(-1,CalculationTest.findMax(new int[]{-12,-1,-3,-4,-2}));
//        assertEquals(-1,CalculationTest.findMax(new int[]{}));
    }
    
}
