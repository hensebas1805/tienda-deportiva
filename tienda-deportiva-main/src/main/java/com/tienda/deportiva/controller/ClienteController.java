package com.tienda.deportiva.controller;

import com.tienda.deportiva.model.Cliente;
import com.tienda.deportiva.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Operaciones CRUD para clientes")
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Crear nuevo cliente")
    public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(cliente));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID")
    public ResponseEntity<Cliente> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar clientes por nombre")
    public ResponseEntity<List<Cliente>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(clienteService.buscarPorNombre(nombre));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Obtener cliente por email")
    public ResponseEntity<Cliente> obtenerPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(clienteService.obtenerPorEmail(email));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}