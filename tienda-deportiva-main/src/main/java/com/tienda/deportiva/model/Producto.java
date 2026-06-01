package com.tienda.deportiva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "La categoría es obligatoria")
    private Categoria categoria;

    private String sku;

    public boolean estaDisponible(Integer cantidad) {
        return this.stock >= cantidad;
    }

    public void reducirStock(Integer cantidad) {
        if (!estaDisponible(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente para: " + this.nombre);
        }
        this.stock -= cantidad;
    }
}