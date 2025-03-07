package com.proyecto.tienda.model;

import com.proyecto.tienda.dto.ClientUpdateDTO;
import com.proyecto.tienda.dto.ProductUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    public Client() {

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
