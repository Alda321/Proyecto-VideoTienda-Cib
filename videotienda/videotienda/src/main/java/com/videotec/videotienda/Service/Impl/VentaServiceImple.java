package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.Venta;
import com.videotec.videotienda.Repository.VentaRepository;
import com.videotec.videotienda.Service.VentaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImple implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImple(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta save(Venta venta) {
        venta.getDetalles().forEach(detalle -> detalle.setVenta(venta));
        return ventaRepository.save(venta);
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }
}
