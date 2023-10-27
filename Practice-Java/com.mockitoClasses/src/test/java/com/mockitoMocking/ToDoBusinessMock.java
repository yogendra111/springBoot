package com.mockitoMocking;

import static org.junit.Assert.assertEquals;  
import static org.mockito.Mockito.mock;  
import static org.mockito.Mockito.when;  
  
import java.util.Arrays;  
import java.util.List;  
import org.junit.Test;

import com.mockitoMocking.services.ToDoBusiness;
import com.mockitoMocking.services.ToDoService;  
  
public class ToDoBusinessMock {  
  
    @Test  
    public void testusing_Mocks() {  
          
        ToDoService doService = mock(ToDoService.class);  
           
        List<String> combinedlist = Arrays.asList();  
        when(doService.getTodos()).thenReturn(combinedlist);  
          
        ToDoBusiness business = new ToDoBusiness(doService);  
      
        List<String> alltd = business.getTodosforHibernate();   
          
        System.out.println(alltd);  
        assertEquals(0, alltd.size());  
    }  
 }  