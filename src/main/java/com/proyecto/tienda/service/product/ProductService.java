package com.proyecto.tienda.service.product;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private IProductRepository productRepository;

    //inyeccion de dependencias
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {

        List<Product> products = productRepository.findAll();

        return products;
    }

    @Override
    public void saveProduct(Product product) {

        productRepository.save(product);

    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public Product findProductById(Long id) {

        Product product = productRepository.findById(id).orElse(null);

        return product;
    }

    @Override
    public void editProduct(Long id_original, ProductUpdateDTO productUpdateDTO) {

        Product product = this.findProductById(id_original);

        // Actualiza los valores del producto con los datos del DTO
        product.updateFromDTO(productUpdateDTO);

        this.saveProduct(product);
    }


}
