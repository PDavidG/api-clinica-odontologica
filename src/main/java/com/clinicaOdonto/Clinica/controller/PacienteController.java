package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.service.IPacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> showPacients() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> showPacient(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> createPacient(@RequestBody Paciente pacient) {
        return new ResponseEntity<>(pacienteService.save(pacient), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePacient(@PathVariable Long id, @RequestBody Paciente pacient) {
        return ResponseEntity.ok(pacienteService.update(id, pacient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
