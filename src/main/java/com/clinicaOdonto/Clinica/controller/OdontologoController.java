package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.service.IOdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/odontos")
@RequiredArgsConstructor
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> mostrarOdontologos() {
        return ResponseEntity.ok(odontologoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> mostrarOdontologo(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odonto) {
        Odontologo odon = odontologoService.save(odonto);
        return new ResponseEntity<>(odon, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo odonto) {
        Odontologo updateOdonto = odontologoService.update(id, odonto);
        return ResponseEntity.ok(updateOdonto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}