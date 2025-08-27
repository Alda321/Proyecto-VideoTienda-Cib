package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.Pelicula;
import com.videotec.videotienda.Repository.PeliculaRepository;
import com.videotec.videotienda.Service.PeliculaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImple implements PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaServiceImple(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Override
    public List<Pelicula> listarTodas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> buscarPorId(Long id) {
        return peliculaRepository.findById(id);
    }

    @Override
    public Pelicula guardar(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula actualizar(Long id, Pelicula pelicula) {
        return peliculaRepository.findById(id).map(p -> {
            p.setTitulo(pelicula.getTitulo());
            p.setDirector(pelicula.getDirector());
            p.setGenero(pelicula.getGenero());
            p.setFechaEstreno(pelicula.getFechaEstreno());
            p.setPrecio(pelicula.getPrecio());
            return peliculaRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        peliculaRepository.deleteById(id);
    }
}
