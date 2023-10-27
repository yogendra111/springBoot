package com.ksolves.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "_contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_id")
    @JsonIgnore
    Long id;
    @Column(name = "c_phone")
    String phoneNo;
    @Column(name = "c_email")
    String email;
}
