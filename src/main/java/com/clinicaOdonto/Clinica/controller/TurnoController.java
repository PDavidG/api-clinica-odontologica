package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.service.ITurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @GetMapping
    public ResponseEntity<List<Turno>> showTurns() {
        return ResponseEntity.ok(turnoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> showTurn(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Turno> createTurn(@RequestBody Turno turn) {
        Turno saveTurno = turnoService.save(turn);
        return new ResponseEntity<>(saveTurno, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurn(@PathVariable Long id, @RequestBody Turno turn) {
        Turno updateTurno = turnoService.update(id, turn);
        return ResponseEntity.ok(updateTurno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurn(@PathVariable Long id) {
        turnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
