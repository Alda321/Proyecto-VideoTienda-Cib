package com.videotec.videotienda.Service.Impl;


import com.videotec.videotienda.Models.Alquiler;
import com.videotec.videotienda.Repository.AlquilerRepository;
import com.videotec.videotienda.Service.AlquilerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlquilerServiceImple implements AlquilerService {

    private final AlquilerRepository alquilerRepository;

    public AlquilerServiceImple(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @Override
    public List<Alquiler> findAll() {
        return alquilerRepository.findAll();
    }

    @Override
    public Optional<Alquiler> findById(Long id) {
        return alquilerRepository.findById(id);
    }

    @Override
    public Alquiler save(Alquiler alquiler) {
        return alquilerRepository.save(alquiler);
    }

    @Override
    public void deleteById(Long id) {
        alquilerRepository.deleteById(id);
    }
}