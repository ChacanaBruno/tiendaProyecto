package com.proyecto.tienda.service.exceptions;

public class TotalAmountErrorException extends RuntimeException {

    public TotalAmountErrorException(String message) {
        super(message);
    }
}
