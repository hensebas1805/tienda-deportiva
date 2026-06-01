package com.tienda.deportiva.controller;

import com.tienda.deportiva.dto.VentaDTO;
import com.tienda.deportiva.model.Venta;
import com.tienda.deportiva.model.Venta.EstadoVenta;
import com.tienda.deportiva.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
@Tag(name = "Ventas", description = "Operaciones CRUD para ventas")
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    @Operation(summary = "Crear nueva venta")
    public ResponseEntity<Venta> crearVenta(@Valid @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crearVenta(ventaDTO));
    }

    @GetMapping
    @Operation(summary = "Obtener todas las ventas")
    public ResponseEntity<List<Venta>> obtenerTodas() {
        return ResponseEntity.ok(ventaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID")
    public ResponseEntity<Venta> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Obtener ventas por cliente")
    public ResponseEntity<List<Venta>> obtenerPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ventaService.obtenerPorCliente(clienteId));
    }

    @GetMapping("/estado")
    @Operation(summary = "Obtener ventas por estado")
    public ResponseEntity<List<Venta>> obtenerPorEstado(@RequestParam EstadoVenta estado) {
        return ResponseEntity.ok(ventaService.obtenerPorEstado(estado));
    }

    @GetMapping("/fecha-rango")
    @Operation(summary = "Obtener ventas por rango de fechas")
    public ResponseEntity<List<Venta>> obtenerPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(ventaService.obtenerPorFecha(inicio, fin));
    }

    @PutMapping("/{id}/completar")
    @Operation(summary = "Completar venta y reducir stock")
    public ResponseEntity<Venta> completarVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.completarVenta(id));
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar venta")
    public ResponseEntity<Venta> cancelarVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.cancelarVenta(id));
    }

    @GetMapping("/reportes/ingresos-totales")
    @Operation(summary = "Obtener ingresos totales")
    public ResponseEntity<Map<String, Double>> obtenerIngresTotales() {
        return ResponseEntity.ok(Map.of("ingresos_totales", ventaService.obtenerIngresTotales()));
    }

    @GetMapping("/reportes/promedio-ventas")
    @Operation(summary = "Obtener promedio de ventas")
    public ResponseEntity<Map<String, Double>> obtenerPromedioVentas() {
        return ResponseEntity.ok(Map.of("promedio_ventas", ventaService.obtenerPromedioVentas()));
    }
}