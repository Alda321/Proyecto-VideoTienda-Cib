package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.Genero;

import java.util.List;
import java.util.Optional;


public interface GeneroService {
    List<Genero> listarTodas();
    Optional<Genero> buscarPorId(Long id);
    Genero guardar(Genero genero);
    Genero actualizar(Long id, Genero genero);
    void eliminar(Long id);
}
