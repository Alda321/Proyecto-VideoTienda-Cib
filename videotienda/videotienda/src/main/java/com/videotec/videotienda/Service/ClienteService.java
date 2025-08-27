package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarTodos();
    Optional<Cliente> buscarPorId(Long id);
    Cliente guardar(Cliente cliente);
    Cliente actualizar(Long id, Cliente cliente);
    void eliminar(Long id);
}
