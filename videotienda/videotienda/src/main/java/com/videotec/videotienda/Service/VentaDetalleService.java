package com.videotec.videotienda.Service;

import com.videotec.videotienda.Models.VentaDetalle;

import java.util.List;
import java.util.Optional;

public interface VentaDetalleService {
    List<VentaDetalle> findAll();
    Optional<VentaDetalle> findById(Long id);
    VentaDetalle save(VentaDetalle detalle);
    void deleteById(Long id);
}
