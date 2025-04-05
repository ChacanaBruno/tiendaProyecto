package com.proyecto.tienda.repository;

import com.proyecto.tienda.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    boolean existsByName(String name);

    boolean existsByBrand(String brand);
}
