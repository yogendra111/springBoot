package com.demoMapStruct.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String descr;
    private int quantity;
    private long price;
//    private String itemID;

//    private List<Item> items;
}
