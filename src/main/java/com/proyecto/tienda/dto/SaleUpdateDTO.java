package com.proyecto.tienda.dto;

import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;
import jakarta.validation.constraints.Min;
import lombok.Getter;


import lombok.Setter;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleUpdateDTO {

    @NotNull(message = "the date cannot be null")
    private LocalDate date_sale;

    @Min(value = 1, message = "The minimum quantity is 1")
    private Double total_amount;

    @NotNull(message = "The product list cannot be null")
    private List<Product> listProducts;

    @NotNull(message = "Client cannot be null")
    private Client client;

    public SaleUpdateDTO() {
    }


    public boolean verifyTotalAmount() {

        return Math.abs(this.getTotal_amount() - this.calculateTotalAmount()) < 0.0001;
    }

    public Double calculateTotalAmount() {
        return listProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
