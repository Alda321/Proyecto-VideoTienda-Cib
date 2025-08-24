package com.videotec.videotienda.Service.Impl;

import com.videotec.videotienda.Models.Producto;
import com.videotec.videotienda.Repository.ProductoRepository;
import com.videotec.videotienda.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImple implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
