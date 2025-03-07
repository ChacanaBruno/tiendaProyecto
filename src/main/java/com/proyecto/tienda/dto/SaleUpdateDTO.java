package com.proyecto.tienda.dto;

import com.proyecto.tienda.model.Client;
import com.proyecto.tienda.model.Product;

import java.time.LocalDate;
import java.util.List;

public class SaleUpdateDTO {
    private Long code_sale;
    private LocalDate date_sale;
    private Double total_amount;
    private List<Product> listProducts;
    private Client client;
}
