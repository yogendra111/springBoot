package com.ksolves.models;

import com.ksolves.entities.Location;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class StoreModel {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min=4, message = "Name should be atleast 4 characters")
    private String name;

    @NotNull(message = "Location is mandatory")
    private LocationModel location;
}
