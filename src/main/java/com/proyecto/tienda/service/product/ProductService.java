package com.proyecto.tienda.service.product;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void updateStock(Product product, Double stock) {
        product.setQuantity_available(stock);
    }

    @Override
    public List<Product> verifyProducts(List<Product> products) {

        List<Product> validProducts = new ArrayList<>();

        for (Product product : products) {
            Product dbProduct = productRepository.findById(product.getCode_product())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + product.getCode_product()));

            if (dbProduct.getQuantity_available() <= 0) {
                throw new IllegalStateException("The product " + dbProduct.getName() + " has no stock available.");
            }

            validProducts.add(dbProduct);
        }

        return validProducts;
    }

    public void updateStockAfterSale(List<Product> products) {
        for (Product product : products) {
            Double newStock = product.getQuantity_available() - 1;
            updateStock(product, newStock);
        }
    }
}



