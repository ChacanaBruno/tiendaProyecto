package com.proyecto.tienda.controller;

import com.proyecto.tienda.dto.product.ProductDTO;
import com.proyecto.tienda.dto.product.ProductUpdateDTO;
import com.proyecto.tienda.model.Product;
import com.proyecto.tienda.service.product.IProductService;
import jakarta.validation.Valid;
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

    @PostMapping("/products/save")
    public String createProduct(@Valid @RequestBody ProductDTO productDto) {

        Product productModel = productDto.transformToModel();

        productService.saveProduct(productModel);

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
                               @RequestBody ProductUpdateDTO productEditDTO) {

        // productEditDTO tiene los campos a editar indicados desde el front
        productService.editProduct(code_product, productEditDTO);

        return productService.findProductById(code_product);
    }

    /*Si es necesario se puede crear una metodo para solo cambiar la id, pero es mala practica.*/
}
