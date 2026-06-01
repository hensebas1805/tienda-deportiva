package com.tienda.deportiva.controller;

import com.tienda.deportiva.model.Producto;
import com.tienda.deportiva.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones CRUD para productos")
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(producto));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos")
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar productos por nombre")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Obtener productos por categoría")
    public ResponseEntity<List<Producto>> obtenerPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoriaId));
    }

    @GetMapping("/stock-bajo")
    @Operation(summary = "Obtener productos con stock bajo")
    public ResponseEntity<List<Producto>> obtenerProductosConStockBajo(
            @RequestParam(defaultValue = "10") Integer minimo) {
        return ResponseEntity.ok(productoService.obtenerProductosConStockBajo(minimo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}