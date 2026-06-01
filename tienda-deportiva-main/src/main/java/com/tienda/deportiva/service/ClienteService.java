package com.tienda.deportiva.service;

import com.tienda.deportiva.exception.BusinessException;
import com.tienda.deportiva.exception.ResourceNotFoundException;
import com.tienda.deportiva.model.Cliente;
import com.tienda.deportiva.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new BusinessException("El email " + cliente.getEmail() + " ya está registrado");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Cliente obtenerPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con email: " + email));
    }

    @Transactional
    public Cliente actualizarCliente(Long id, Cliente clienteNuevo) {
        Cliente cliente = obtenerPorId(id);
        cliente.setNombre(clienteNuevo.getNombre());
        cliente.setEmail(clienteNuevo.getEmail());
        cliente.setTelefono(clienteNuevo.getTelefono());
        cliente.setDireccion(clienteNuevo.getDireccion());
        cliente.setCiudad(clienteNuevo.getCiudad());
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerPorId(id);
        if (cliente.getVentas() != null && !cliente.getVentas().isEmpty()) {
            throw new BusinessException("No se puede eliminar un cliente que tiene ventas registradas");
        }
        clienteRepository.deleteById(id);
    }
}