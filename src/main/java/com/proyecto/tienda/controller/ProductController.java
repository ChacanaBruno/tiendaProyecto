package com.proyecto.tienda.controller;

import com.proyecto.tienda.dto.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.service.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/fetch")
    public List<Product> getProducts() {

        return productService.getProducts();
    }

    @PostMapping("/products/create")
    public String createProduct(@RequestBody Product product) {

        productService.saveProduct(product);

        return "Successfully created product";
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "Successfully deleted product";
    }

    @GetMapping("/products/find/{id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PutMapping("product/edit/{code_product}")
    public Product editProduct(@PathVariable Long code_product,
                               @RequestBody ProductUpdateDTO productUpdateDTO) {

        productService.editProduct(code_product, productUpdateDTO);

        return productService.findProductById(code_product);
    }

    /*Si es necesario se puede crear una metodo para solo cambiar la id, pero es mala practica.*/
}
