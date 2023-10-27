package com.ksolves.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "_employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "e_id")
    @JsonIgnore
    Long id;
    @Column(name = "e_name", updatable = false)
    String name;
    @Column(name = "e_designation")
    String designation;
    @Column(name = "e_salary")
    Integer salary;

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "_employee_project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") })
//    List<Project> projects;
    Set<Project> projects;

//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//@JoinTable(
//        name = "_employee_project",
//        joinColumns = @JoinColumn(name = "employee_id"),
//        inverseJoinColumns = @JoinColumn(name = "project_id")
//)
//private Set<Project> projects = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "e_contact")
    Contact contact;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "e_address_id")
    List<Address> addresses;
}
