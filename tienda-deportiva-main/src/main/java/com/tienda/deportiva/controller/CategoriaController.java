package com.tienda.deportiva.controller;

import com.tienda.deportiva.model.Categoria;
import com.tienda.deportiva.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Operaciones CRUD para categorías de productos")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @Operation(summary = "Crear nueva categoría")
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoria));
    }

    @GetMapping
    @Operation(summary = "Obtener todas las categorías")
    public ResponseEntity<List<Categoria>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener categoría por ID")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar categorías por nombre")
    public ResponseEntity<List<Categoria>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar categoría")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, categoria));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}