package com.proyecto.tienda.model;

import com.proyecto.tienda.dto.SaleUpdateDTO;
import jakarta.persistence.*;
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
    @OneToMany
    private List<Product> listProducts;

    @ManyToOne // Relación con Client (muchas ventas para un cliente)
    @JoinColumn(name = "client_id") //Se especifica la columna de la clave foránea
    private Client client;

    public Sale() {
    }

    public Sale(Long code_sale, LocalDate date_sale, Double total_amount, Client client) {
        this.code_sale = code_sale;
        this.date_sale = date_sale;
        this.total_amount = total_amount;
        this.client = client;
    }

    public void updateFromDTO(SaleUpdateDTO saleUpdateDTO) {
        if(saleUpdateDTO.getDate_sale() != null) {
            this.setDate_sale(saleUpdateDTO.getDate_sale());
        }
        if(saleUpdateDTO.getTotal_amount() != null) {
            this.setTotal_amount(saleUpdateDTO.getTotal_amount());
        }
        if(saleUpdateDTO.getListProducts() != null) {
            this.setListProducts(saleUpdateDTO.getListProducts());
        }
        if(saleUpdateDTO.getClient() != null) {
            this.setClient(saleUpdateDTO.getClient());
        }
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