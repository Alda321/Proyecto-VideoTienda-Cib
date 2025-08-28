package com.videotec.videotienda.Controller;

import com.videotec.videotienda.Models.Venta;
import com.videotec.videotienda.Service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342", methods = RequestMethod.GET)

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<Venta> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();

        // Solo acceder a getTitulo() si existe la película
        ventas.forEach(v ->
                v.getDetalles().forEach(d -> {
                    if (d.getPelicula() != null) {
                        d.getPelicula().getTitulo();
                    }
                })
        );

        return ventas;
    }



    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venta createVenta(@RequestBody Venta venta) {
        return ventaService.save(venta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
        return ventaService.findById(id)
                .map(venta -> {
                    venta.setFecha(ventaDetails.getFecha());
                    venta.setTotal(ventaDetails.getTotal());
                    venta.setCliente(ventaDetails.getCliente());
                    venta.setDetalles(ventaDetails.getDetalles());
                    venta.getDetalles().forEach(detalle -> detalle.setVenta(venta));
                    Venta updated = ventaService.save(venta);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        if (ventaService.findById(id).isPresent()) {
            ventaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }




}