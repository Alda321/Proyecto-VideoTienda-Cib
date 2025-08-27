package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.Alquiler;

import java.util.List;
import java.util.Optional;

public interface AlquilerService {
    List<Alquiler> findAll();
    Optional<Alquiler> findById(Long id);
    Alquiler save(Alquiler alquiler);
    void deleteById(Long id);
}
