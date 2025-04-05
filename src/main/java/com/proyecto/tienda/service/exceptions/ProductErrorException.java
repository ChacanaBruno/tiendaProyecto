package com.proyecto.tienda.service.exceptions;

public class ProductErrorException extends RuntimeException {

  public ProductErrorException(String message) {
    super(message);
  }

  public static ProductErrorException notFoundById(Long id) {
    return new ProductErrorException("Product with ID " + id + " was not found.");
  }

  public static ProductErrorException notFoundByName(String name) {
    return new ProductErrorException("Product with name '" + name + "' was not found.");
  }

  public static ProductErrorException idAlreadyRegistered(Long id) {
    return new ProductErrorException("Product with ID " + id + " already exists, cannot be saved.");
  }

  public static ProductErrorException productAlreadyRegistered(String name, String brand) {
    return new ProductErrorException("Product with name '" + name + "' already exists, cannot be saved.");
  }
}



