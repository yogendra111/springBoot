package com.mockitoStubbing;

import java.util.Arrays;  
import java.util.List;

import com.mockitoStubbing.services.ToDoService;  
  
public class ToDoServiceStub implements ToDoService{  
  
    public List<String> getTodos(String user) {  
      
    return Arrays.asList(" Use Core Java ", " Use Spring Core ", " Use Hibernate ", " Use Spring MVC ");  
    }  
 }  