package com.ksolves.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "a_id")
    @JsonIgnore
    Long id;
    @Column(name = "a_street")
    String street;
    @Column(name = "a_city")
    String city;
    @Column(name = "a_state")
    String state;
    @Column(name = "a_pincode")
    Integer pincode;
}
