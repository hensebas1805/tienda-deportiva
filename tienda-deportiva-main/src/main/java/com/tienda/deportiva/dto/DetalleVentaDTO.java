package com.tienda.deportiva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}