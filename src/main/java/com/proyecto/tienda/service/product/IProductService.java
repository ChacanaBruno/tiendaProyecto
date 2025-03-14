package com.proyecto.tienda.service.product;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Product;

import java.util.List;

public interface
IProductService {

    public List<Product> getProducts();

    public void saveProduct(Product product);

    public void deleteProductById(Long id);

    public Product findProductById(Long id);

    public void editProduct(Long id_original, ProductUpdateDTO productUpdateDTO);

    public void updateStock(Product product, Double stock);

    public List<Product> verifyProducts(List<Product> products);


}
