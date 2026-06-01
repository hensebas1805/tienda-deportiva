package com.tienda.deportiva.repository;

import com.tienda.deportiva.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByTelefono(String telefono);
}