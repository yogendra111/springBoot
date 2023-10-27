package com.ksolves.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "_projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "p_id")
    private Long id;
    @Column(name = "p_name")
    private String name;
    @Column(name = "p_startDate")
    private Date startDate;
//    @ManyToMany(mappedBy = "projects")
//    private List<Employee> employees;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "projects")
    private Set<Employee> employees;
//@ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
//private Set<Employee> employees = new HashSet<>();

}
