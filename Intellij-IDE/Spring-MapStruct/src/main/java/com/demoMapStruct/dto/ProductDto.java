package com.demoMapStruct.dto;

import com.demoMapStruct.model.Item;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private int quantity;
    private long price;
//    private String itemID;

//    private List<Item> itemsList;
}
