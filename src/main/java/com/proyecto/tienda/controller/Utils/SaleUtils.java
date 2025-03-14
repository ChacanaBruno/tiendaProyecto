package com.proyecto.tienda.controller.Utils;

import com.proyecto.tienda.dto.SaleUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.repository.IProductRepository;
import com.proyecto.tienda.service.product.IProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component  // Permite la inyección de esta clase en otros componentes de Spring
@Getter
@Setter
public class SaleUtils {

    private IProductRepository productRepository;

    private IProductService productService;

    public SaleUtils(IProductRepository productRepository, IProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public void verifyProducts(SaleUpdateDTO saleDTO) {
        for (Product product : saleDTO.getListProducts()) {
            Product dbProduct = productRepository.findById(product.getCode_product())
                    .orElseThrow(() -> new IllegalArgumentException("Product no found: " + product.getCode_product()));

            if (dbProduct.getQuantity_available() <= 0) {
                throw new IllegalStateException("The product " + dbProduct.getName() + " no stock available.");
            }


            // Restar stock
            this.getProductService().updateStock(dbProduct,dbProduct.getQuantity_available() - 1);
            //validProducts.add(dbProduct);
        }
    }
}
