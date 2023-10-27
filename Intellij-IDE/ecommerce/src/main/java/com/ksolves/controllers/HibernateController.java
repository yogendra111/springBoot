package com.ksolves.controllers;

import com.ksolves.entities.Employee;
import com.ksolves.services.HibernateService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hibernate")
public class HibernateController {

    @Autowired
    HibernateService hibernateService;

    @GetMapping("/employee")
    ResponseEntity<Employee> getEmployee(@RequestParam Long id){
        Employee emp = hibernateService.get(id);
        return ResponseEntity.ok().body(emp);
    }
    @GetMapping("/employee/all")
    ResponseEntity<List<Employee>> getAllEmployee(){
        return ResponseEntity.ok().body(hibernateService.getAll());
    }
    @PostMapping("/employee")
    ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
        Long empId = hibernateService.add(employee);
        return ResponseEntity.ok().body("Added new Employee with Id: " + empId);
    }
    @DeleteMapping("/employee")
    ResponseEntity<String> deleteEmployee(@RequestParam Long id){
        hibernateService.delete(id);
        return ResponseEntity.ok().body("Deleted Employee with Id: " + id);
    }
    @PatchMapping("/employee")
    ResponseEntity<Employee> updateEmployee(@RequestParam Long id){
        return ResponseEntity.ok().body(hibernateService.update(id));
    }


}
