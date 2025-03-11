package com.proyecto.tienda.service.exceptions;

public class TotalAmountErrorException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Total amount error";
    }
}
