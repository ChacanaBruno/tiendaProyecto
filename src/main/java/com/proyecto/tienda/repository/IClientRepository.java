package com.proyecto.tienda.repository;

import com.proyecto.tienda.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findByDni(String dni);
}
