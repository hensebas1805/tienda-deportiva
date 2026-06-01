package com.tienda.deportiva.dto;

import com.tienda.deportiva.model.Venta.EstadoVenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long id;
    private Long clienteId;
    private String clienteNombre;
    private LocalDateTime fecha;
    private Double total;
    private EstadoVenta estado;
    private List<DetalleVentaDTO> detalles;
}