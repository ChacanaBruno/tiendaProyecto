package com.proyecto.tienda.service;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getProducts();

    public void saveProduct(Product product);

    public void deleteProductById(Long id);

    public Product findProductById(Long id);

    public void editProduct(Long id_original, ProductUpdateDTO productUpdateDTO);
}
