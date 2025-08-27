package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.Genero;
import com.videotec.videotienda.Repository.GeneroRepository;
import com.videotec.videotienda.Service.GeneroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImple implements GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroServiceImple(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<Genero> listarTodas() {
        return generoRepository.findAll();
    }

    @Override
    public Optional<Genero> buscarPorId(Long id) {
        return generoRepository.findById(id);
    }

    @Override
    public Genero guardar(Genero genero) {
        return generoRepository.save(genero);
    }

    @Override
    public Genero actualizar(Long id, Genero genero) {
        return generoRepository.findById(id).map(g -> {
            g.setNombre(genero.getNombre());
            return generoRepository.save(g);
        }).orElseThrow(() -> new RuntimeException("Genero no encontrado con id " + id));
    }

    @Override
    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }
}
