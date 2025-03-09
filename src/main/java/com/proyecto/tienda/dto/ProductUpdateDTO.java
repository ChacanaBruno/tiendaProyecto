package com.proyecto.tienda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {
    // atributos que se pueden actualizar:
    private String name;
    private String brand;
    private Double price;
    private Double quantityAvailable;

    public ProductUpdateDTO() {

    }


}

