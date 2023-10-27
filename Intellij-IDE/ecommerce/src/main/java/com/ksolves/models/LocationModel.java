package com.ksolves.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {
    Long id;
    @NotBlank
    String street;
    @NotBlank
    String city;
    @NotBlank
    String state;
    @NotNull
    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid Pincode")
    Integer pincode;
}
