package com.proyecto.tienda.dto.client;

import com.proyecto.tienda.model.Client;
import jakarta.validation.constraints.NotEmpty;


public record ClientDTO(
        @NotEmpty(message = "first_name is required") String first_name,
        @NotEmpty(message = "last_name is required") String last_name,
        @NotEmpty(message = "dni is required") String dni
) {
    public Client transformToModel() {
        return new Client(first_name, last_name, dni);
    }

    public static ClientDTO fromModel(Client client) {
        return new ClientDTO(client.getFirst_name(), client.getLast_name(), client.getDni());
    }
}