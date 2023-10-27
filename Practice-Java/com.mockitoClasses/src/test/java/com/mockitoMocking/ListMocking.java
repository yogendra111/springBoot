package com.mockitoMocking;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;  
  
import java.util.List;  
import org.junit.Test;  
  
public class ListMocking {  
  
    @Test  
    public void testList_ReturnsSingle_value() {  
  
        List mocklist = mock(List.class);  
//                           when(mocklist.size()).thenReturn(1);  
                           when(mocklist.size()).thenReturn(1).thenReturn(2).thenReturn(3);  ; 
  
        assertEquals(1, mocklist.size());
        assertEquals(3, mocklist.size());  
        assertEquals(2, mocklist.size()); 
          
                          System.out.println( mocklist.size());  
        System.out.println(mocklist);  
    }  
 }  