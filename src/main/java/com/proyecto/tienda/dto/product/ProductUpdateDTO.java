package com.proyecto.tienda.dto.product;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {

    private String name;
    private String brand;
    @Positive
    private Double price;
    @Positive
    private Double quantity_available;
}
