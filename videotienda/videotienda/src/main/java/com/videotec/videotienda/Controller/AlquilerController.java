package com.videotec.videotienda.Controller;

import com.videotec.videotienda.Models.Alquiler;
import com.videotec.videotienda.Service.AlquilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;

    public AlquilerController(AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @GetMapping
    public List<Alquiler> getAllAlquileres() {
        return alquilerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> getAlquilerById(@PathVariable Long id) {
        return alquilerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alquiler createAlquiler(@RequestBody Alquiler alquiler) {
        return alquilerService.save(alquiler);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alquiler> updateAlquiler(@PathVariable Long id, @RequestBody Alquiler alquilerDetails) {
        return alquilerService.findById(id)
                .map(alquiler -> {
                    alquiler.setFechaInicio(alquilerDetails.getFechaInicio());
                    alquiler.setFechaFin(alquilerDetails.getFechaFin());
                    alquiler.setDevuelto(alquilerDetails.isDevuelto());
                    alquiler.setCliente(alquilerDetails.getCliente());
                    Alquiler updated = alquilerService.save(alquiler);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlquiler(@PathVariable Long id) {
        if (alquilerService.findById(id).isPresent()) {
            alquilerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}