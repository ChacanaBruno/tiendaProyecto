package com.proyecto.tienda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code_sale;
    private LocalDate date_sale;
    private Double total_amount;
    private List<Product> listProducts;
    private Client client;

    public Sale() {
    }

    public Sale(Long code_sale, LocalDate date_sale, Double total_amount, Client client) {
        this.code_sale = code_sale;
        this.date_sale = date_sale;
        this.total_amount = total_amount;
        this.client = client;
    }
}
