package com.proyecto.tienda.service.exceptions;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException(String message) {
        super(message);
    }

  public static ClientErrorException clientDniAlreadyRegistered(String dni) {
    return new ClientErrorException("Client dni " + dni + " already exists, cannot be saved.");
  }
}
