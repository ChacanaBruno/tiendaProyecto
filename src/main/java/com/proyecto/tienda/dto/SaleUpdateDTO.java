package com.proyecto.tienda.dto;

import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleUpdateDTO {

    private LocalDate date_sale;
    private Double total_amount;
    private List<Product> listProducts;
    private Client client;

    public SaleUpdateDTO() {

    }
}
