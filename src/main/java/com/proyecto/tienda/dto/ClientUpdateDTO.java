package com.proyecto.tienda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUpdateDTO {
    // atributos que se pueden actualizar:
    private String first_name ;
    private String last_name;
    private String dni;

}
