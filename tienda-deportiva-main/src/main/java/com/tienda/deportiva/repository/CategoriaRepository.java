package com.tienda.deportiva.repository;

import com.tienda.deportiva.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
    Optional<Categoria> findByNombre(String nombre);
}