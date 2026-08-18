package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.service.IHorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final IHorarioService horarioService;

    @GetMapping
    public ResponseEntity<List<Horario>> obtenerHorarios() {
        return ResponseEntity.ok(horarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horario> obtenerHorario(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Horario> crearHorario(@RequestBody Horario horario) {
        Horario createHorario = horarioService.save(horario);
        return new ResponseEntity<>(createHorario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody Horario horario) {
        Horario updatedHorario = horarioService.update(id, horario);
        return ResponseEntity.ok(updatedHorario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
