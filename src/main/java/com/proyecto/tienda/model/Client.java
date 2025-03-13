package com.proyecto.tienda.model;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.dto.ProductUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id_client;
    private String first_name ;
    private String last_name;
    private String dni;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales = new ArrayList<>();


    public Client() {

    }

    public Client(String first_name, String last_name, String dni, List<Sale> sales) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dni = dni;
        this.sales = sales;
    }

    public void updateFromDTO(ClientUpdateDTO dto) {

            if (dto.getFirst_name() != null) {
                this.first_name = dto.getFirst_name();
            }
            if (dto.getLast_name() != null) {
                this.last_name = dto.getLast_name();
            }
            if (dto.getDni() != null) {
                this.dni = dto.getDni();
            }
    }
}
