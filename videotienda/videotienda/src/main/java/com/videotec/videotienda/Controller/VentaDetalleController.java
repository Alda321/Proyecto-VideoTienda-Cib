package com.videotec.videotienda.Controller;

import com.videotec.videotienda.Models.VentaDetalle;
import com.videotec.videotienda.Service.VentaDetalleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/api/detalles")
public class VentaDetalleController {

    private final VentaDetalleService detalleService;

    public VentaDetalleController(VentaDetalleService detalleService) {
        this.detalleService = detalleService;
    }

    @GetMapping
    public List<VentaDetalle> getAllDetalles() {
        return detalleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDetalle> getDetalleById(@PathVariable Long id) {
        return detalleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public VentaDetalle createDetalle(@RequestBody VentaDetalle detalle) {
        return detalleService.save(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDetalle> updateDetalle(@PathVariable Long id, @RequestBody VentaDetalle detalleDetails) {
        return detalleService.findById(id)
                .map(detalle -> {
                    detalle.setCantidad(detalleDetails.getCantidad());
                    detalle.setPrecioUnitario(detalleDetails.getPrecioUnitario());
                    detalle.setVenta(detalleDetails.getVenta());
                    detalle.setPelicula(detalleDetails.getPelicula());
                    return ResponseEntity.ok(detalleService.save(detalle));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        if (detalleService.findById(id).isPresent()) {
            detalleService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
