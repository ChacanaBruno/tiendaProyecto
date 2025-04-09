package com.proyecto.tienda.service.product;

import com.proyecto.tienda.dto.product.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.repository.IProductRepository;
import com.proyecto.tienda.service.exceptions.OutOfStockException;
import com.proyecto.tienda.service.exceptions.ProductErrorException;
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
        if(productRepository.existsByName(product.getName()) && productRepository.existsByBrand(product.getBrand())) {
            throw ProductErrorException.productAlreadyRegistered(product.getName(), product.getBrand());
        }
        else {
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.deleteById(id);

    }

    @Override
    public Product findProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductErrorException("Product id " + id + " no found"));
    }

    @Override
    public void editProduct(Long id_original, ProductUpdateDTO productUpdateDTO) {
        // podria buscarlo por nombre y marca en vez de por id
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
                    .orElseThrow(() -> new ProductErrorException("Product not found: " + product.getName()));

            if (dbProduct.getQuantity_available() <= 0) {
                throw new OutOfStockException("The product " + dbProduct.getName() + " has no stock available.");
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



