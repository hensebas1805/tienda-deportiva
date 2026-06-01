package com.tienda.deportiva.service;

import com.tienda.deportiva.exception.BusinessException;
import com.tienda.deportiva.exception.ResourceNotFoundException;
import com.tienda.deportiva.model.Categoria;
import com.tienda.deportiva.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria crearCategoria(Categoria categoria) {
        if (categoriaRepository.findByNombre(categoria.getNombre()).isPresent()) {
            throw new BusinessException("La categoría " + categoria.getNombre() + " ya existe");
        }
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));
    }

    public List<Categoria> buscarPorNombre(String nombre) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria categoriaNueva) {
        Categoria categoria = obtenerPorId(id);
        categoria.setNombre(categoriaNueva.getNombre());
        categoria.setDescripcion(categoriaNueva.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        Categoria categoria = obtenerPorId(id);
        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new BusinessException("No se puede eliminar una categoría que tiene productos asociados");
        }
        categoriaRepository.deleteById(id);
    }
}