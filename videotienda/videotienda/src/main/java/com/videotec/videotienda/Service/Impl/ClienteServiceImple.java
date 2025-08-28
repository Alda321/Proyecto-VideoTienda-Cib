package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.Cliente;
import com.videotec.videotienda.Repository.ClienteRepository;
import com.videotec.videotienda.Service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImple implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImple(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(c -> {
            c.setNombre(clienteActualizado.getNombre());
            c.setCorreo(clienteActualizado.getCorreo());
            c.setTelefono(clienteActualizado.getTelefono());
            return clienteRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente login(String correo, String password) {
        return clienteRepository.findByCorreo(correo)
                .filter(c -> c.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
    }

}