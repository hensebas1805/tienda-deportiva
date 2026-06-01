package com.tienda.deportiva.repository;

import com.tienda.deportiva.model.Venta;
import com.tienda.deportiva.model.Venta.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByClienteId(Long clienteId);
    List<Venta> findByEstado(EstadoVenta estado);
    List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}