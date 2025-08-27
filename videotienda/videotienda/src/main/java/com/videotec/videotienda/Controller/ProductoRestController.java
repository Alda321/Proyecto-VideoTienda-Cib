package com.videotec.videotienda.Controller;

import com.videotec.videotienda.Models.PeliculaReponse;
import com.videotec.videotienda.Models.Producto;
import com.videotec.videotienda.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.videotec.videotienda.Service.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private final ProductoService productoService;
    private final ApiService apiService;

    public ProductoRestController(ProductoService productoService, ApiService apiService) {
        this.productoService = productoService;
        this.apiService = apiService;
    }

    @GetMapping
    public List<Producto> listar() {
        return productoService.listarTodos();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return productoService.guardar(producto);
    }

    @GetMapping("/id/{id}")
    public Producto obtener(@PathVariable Long id) {
        return productoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }


    @GetMapping("/pelicula/{titulo}")
    public PeliculaReponse obtenerPelicula(@PathVariable String titulo) {
        return apiService.obtenerPelicula(titulo);
    }

}
