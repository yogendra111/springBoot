package com.mockitoMocking.services;

import java.util.ArrayList;  
import java.util.List;  
  
// ToDoBusiness is a SUT (system under test)   
// ToDoService is a Dependency (as ToDoBusiness is dependent on it)  
  
public class ToDoBusiness {  
  
    private ToDoService doService;  
  
    public ToDoBusiness(ToDoService doService) {  
        this.doService = doService;  
    }  
      
    public List<String> getTodosforHibernate() {  
          
        List<String> hibernatelist = new ArrayList<String>();  
        List<String> Combinedlist = doService.getTodos();  
          
        for(String todo: Combinedlist) {  
            if(todo.contains("Hibernate")) {  
                hibernatelist.add(todo);  
            }  
        }  
          
        return hibernatelist;  
        }  
 }  