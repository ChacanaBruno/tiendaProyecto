package com.proyecto.tienda.model;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Si la BD maneja la generación automática
    @Column(name = "code_product")
    private Long code_product;
    private String name;
    private String brand;
    private Double price;
    private Double quantity_available;

    public Product() {
    }

    public Product(String name, String brand, Double price, Double quantity_available) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity_available = quantity_available;
    }

    // al recibir una edicion/update, esta puede ser parcial o total, si es parcial, algunos campos
    // de la solicitud quedaran null, por ende, se verifica para no perder valores
    //al menos por ahora
    public void updateFromDTO(ProductUpdateDTO dto) {
        if (dto.getName() != null) {
            this.name = dto.getName();
        }
        if (dto.getBrand() != null) {
            this.brand = dto.getBrand();
        }
        if (dto.getPrice() != null) {
            this.price = dto.getPrice();
        }
        if (dto.getQuantityAvailable() != null) {
            this.quantity_available = dto.getQuantityAvailable();
        }
    }
}
