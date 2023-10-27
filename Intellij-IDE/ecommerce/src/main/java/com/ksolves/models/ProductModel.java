package com.ksolves.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min=5, message = "Name should be atleast 5 characters")
    private String name;

    @Min(20)
    @NotNull(message = "Price is mandatory")
    private Double price;

    @NotBlank(message = "Brand is mandatory.")
    private String brand;
}
