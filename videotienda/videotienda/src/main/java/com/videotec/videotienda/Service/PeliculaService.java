package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> listarTodas();
    Optional<Pelicula> buscarPorId(Long id);
    Pelicula guardar(Pelicula pelicula);
    Pelicula actualizar(Long id, Pelicula pelicula);
    void eliminar(Long id);
}
