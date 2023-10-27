package com.ksolves.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
//    @JsonIgnore
    Long id;
    @Column(name = "street")
    String street;
    @Column(name = "city")
    String city;
    @Column(name = "state")
    String state;
    @Column(name = "pincode")
    Integer pincode;
}
