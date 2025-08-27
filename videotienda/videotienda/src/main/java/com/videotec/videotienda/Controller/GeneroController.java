package com.videotec.videotienda.Controller;

import com.videotec.videotienda.Models.Genero;
import com.videotec.videotienda.Service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public List<Genero> listar() {
        return generoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> obtenerPorId(@PathVariable Long id) {
        return generoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Genero crear(@RequestBody Genero genero) {
        return generoService.guardar(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> actualizar(@PathVariable Long id, @RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.actualizar(id, genero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        generoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
