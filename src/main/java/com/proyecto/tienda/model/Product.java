package com.proyecto.tienda.model;

import com.proyecto.tienda.dto.product.ProductUpdateDTO;
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
    public void updateFromDTO(ProductUpdateDTO product) {
        if (product.getName() != null) {
            this.name = product.getName();
        }
        if (product.getBrand() != null) {
            this.brand = product.getBrand();
        }
        if (product.getPrice() != null) {
            this.price = product.getPrice();
        }
        if (product.getQuantity_available()!= null) {
            this.quantity_available = product.getQuantity_available();
        }
    }
}
