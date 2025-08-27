package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.VentaDetalle;
import com.videotec.videotienda.Repository.VentaDetalleRepository;
import com.videotec.videotienda.Service.VentaDetalleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaDetalleServiceImpl implements VentaDetalleService {

    private final VentaDetalleRepository detalleRepository;

    public VentaDetalleServiceImpl(VentaDetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }

    @Override
    public List<VentaDetalle> findAll() {
        return detalleRepository.findAll();
    }

    @Override
    public Optional<VentaDetalle> findById(Long id) {
        return detalleRepository.findById(id);
    }

    @Override
    public VentaDetalle save(VentaDetalle detalle) {
        return detalleRepository.save(detalle);
    }

    @Override
    public void deleteById(Long id) {
        detalleRepository.deleteById(id);
    }
}