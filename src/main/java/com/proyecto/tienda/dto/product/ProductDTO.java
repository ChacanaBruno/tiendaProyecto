package com.proyecto.tienda.dto.product;

import com.proyecto.tienda.model.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

public record ProductDTO(
        @NotEmpty(message = "name is required")
        String name,
        @NotEmpty(message = "brand is required")
        String brand,
        @NotNull @Positive
        Double price,
        @NotNull @Positive
        Double quantityAvailable) {

    public Product transformToModel() {
        return new Product(
                this.name,
                this.brand,
                this.price,
                this.quantityAvailable
        );
    }
    public static ProductDTO fromModel(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getQuantity_available()
        );
    }
}
