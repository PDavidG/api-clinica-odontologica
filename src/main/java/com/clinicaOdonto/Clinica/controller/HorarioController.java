package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;
import com.clinicaOdonto.Clinica.service.IHorarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/horarios")
@RequiredArgsConstructor
public class HorarioController {

    private final IHorarioService horarioService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Horario>> obtenerHorarios() {
        return ResponseEntity.ok(horarioService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Horario> obtenerHorario(@PathVariable Long id) {
        return ResponseEntity.ok(horarioService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Horario> crearHorario(@RequestBody HorarioRequestDto horarioRequest) {
        Horario createHorario = horarioService.save(horarioRequest);
        return new ResponseEntity<>(createHorario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Horario> updateHorario(@PathVariable Long id, @RequestBody HorarioRequestDto horarioRequest) {
        Horario updatedHorario = horarioService.update(id, horarioRequest);
        return ResponseEntity.ok(updatedHorario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        horarioService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
