package com.tienda.deportiva.service;

import com.tienda.deportiva.dto.DetalleVentaDTO;
import com.tienda.deportiva.dto.VentaDTO;
import com.tienda.deportiva.exception.BusinessException;
import com.tienda.deportiva.exception.ResourceNotFoundException;
import com.tienda.deportiva.model.*;
import com.tienda.deportiva.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final VentaRepository ventaRepository;
    private final ClienteService clienteService;
    private final ProductoService productoService;

    @Transactional
    public Venta crearVenta(VentaDTO ventaDTO) {
        Cliente cliente = clienteService.obtenerPorId(ventaDTO.getClienteId());

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setEstado(Venta.EstadoVenta.PENDIENTE);

        List<DetalleVenta> detalles = ventaDTO.getDetalles().stream()
                .map(detalleDTO -> {
                    Producto producto = productoService.obtenerPorId(detalleDTO.getProductoId());

                    if (!producto.estaDisponible(detalleDTO.getCantidad())) {
                        throw new BusinessException("Stock insuficiente para: " + producto.getNombre());
                    }

                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setVenta(venta);
                    detalle.setProducto(producto);
                    detalle.setCantidad(detalleDTO.getCantidad());
                    detalle.setPrecioUnitario(producto.getPrecio());
                    detalle.setSubtotal(detalle.getCantidad() * detalle.getPrecioUnitario());

                    return detalle;
                })
                .collect(Collectors.toList());

        venta.setDetalles(detalles);
        venta.calcularTotal();

        return ventaRepository.save(venta);
    }

    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    public Venta obtenerPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con ID: " + id));
    }

    public List<Venta> obtenerPorCliente(Long clienteId) {
        clienteService.obtenerPorId(clienteId);
        return ventaRepository.findByClienteId(clienteId);
    }

    public List<Venta> obtenerPorEstado(Venta.EstadoVenta estado) {
        return ventaRepository.findByEstado(estado);
    }

    public List<Venta> obtenerPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }

    @Transactional
    public Venta completarVenta(Long id) {
        Venta venta = obtenerPorId(id);

        if (venta.getEstado() == Venta.EstadoVenta.CANCELADA) {
            throw new BusinessException("No se puede completar una venta cancelada");
        }

        venta.getDetalles().forEach(detalle ->
                productoService.reducirStock(detalle.getProducto().getId(), detalle.getCantidad())
        );

        venta.setEstado(Venta.EstadoVenta.COMPLETADA);
        return ventaRepository.save(venta);
    }

    @Transactional
    public Venta cancelarVenta(Long id) {
        Venta venta = obtenerPorId(id);

        if (venta.getEstado() == Venta.EstadoVenta.COMPLETADA) {
            throw new BusinessException("No se puede cancelar una venta completada");
        }

        venta.setEstado(Venta.EstadoVenta.CANCELADA);
        return ventaRepository.save(venta);
    }

    public Double obtenerIngresTotales() {
        return ventaRepository.findByEstado(Venta.EstadoVenta.COMPLETADA)
                .stream()
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    public Double obtenerPromedioVentas() {
        List<Venta> ventas = ventaRepository.findByEstado(Venta.EstadoVenta.COMPLETADA);
        if (ventas.isEmpty()) {
            return 0.0;
        }
        return ventas.stream()
                .mapToDouble(Venta::getTotal)
                .average()
                .orElse(0.0);
    }
}