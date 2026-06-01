package com.tienda.deportiva.service;

import com.tienda.deportiva.exception.BusinessException;
import com.tienda.deportiva.exception.ResourceNotFoundException;
import com.tienda.deportiva.model.Producto;
import com.tienda.deportiva.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Transactional
    public Producto crearProducto(Producto producto) {
        if (productoRepository.findBySku(producto.getSku()).isPresent()) {
            throw new BusinessException("El SKU " + producto.getSku() + " ya existe");
        }
        if (producto.getStock() < 0) {
            throw new BusinessException("El stock no puede ser negativo");
        }
        if (producto.getPrecio() < 0) {
            throw new BusinessException("El precio no puede ser negativo");
        }
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> obtenerPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    @Transactional
    public Producto actualizarProducto(Long id, Producto productoNuevo) {
        Producto producto = obtenerPorId(id);
        producto.setNombre(productoNuevo.getNombre());
        producto.setDescripcion(productoNuevo.getDescripcion());
        producto.setPrecio(productoNuevo.getPrecio());
        producto.setStock(productoNuevo.getStock());
        producto.setCategoria(productoNuevo.getCategoria());
        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id);
        productoRepository.deleteById(id);
    }

    @Transactional
    public void reducirStock(Long productoId, Integer cantidad) {
        Producto producto = obtenerPorId(productoId);
        if (!producto.estaDisponible(cantidad)) {
            throw new BusinessException("Stock insuficiente para: " + producto.getNombre() + 
                    ". Disponible: " + producto.getStock() + ", Solicitado: " + cantidad);
        }
        producto.reducirStock(cantidad);
        productoRepository.save(producto);
    }

    public List<Producto> obtenerProductosConStockBajo(Integer minimo) {
        return productoRepository.findAll().stream()
                .filter(p -> p.getStock() < minimo)
                .toList();
    }
}