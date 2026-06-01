package com.tienda.deportiva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "El cliente es obligatorio")
    private Cliente cliente;

    private LocalDateTime fecha = LocalDateTime.now();

    private Double total = 0.0;

    @Enumerated(EnumType.STRING)
    private EstadoVenta estado = EstadoVenta.PENDIENTE;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    public enum EstadoVenta {
        PENDIENTE, COMPLETADA, CANCELADA
    }

    public void calcularTotal() {
        this.total = this.detalles.stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();
    }
}