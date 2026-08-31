package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.dto.TurnoRequestDto;
import com.clinicaOdonto.Clinica.dto.TurnoResponseDto;
import com.clinicaOdonto.Clinica.mapper.TurnoMapper;
import com.clinicaOdonto.Clinica.service.ITurnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/turnos")
public class TurnoController {

    private final ITurnoService turnoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<TurnoResponseDto>> showTurns() {
        List<TurnoResponseDto> listaTurnos = turnoService.findAll()
                .stream()
                .map(TurnoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaTurnos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<TurnoResponseDto> showTurn(@PathVariable Long id) {
        Turno turno = turnoService.findById(id);
        return ResponseEntity.ok(TurnoMapper.toDto(turno));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TurnoResponseDto> createTurn(@Valid @RequestBody TurnoRequestDto requestDto) {
        Turno saveTurno = turnoService.save(requestDto);
        return new ResponseEntity<>(TurnoMapper.toDto(saveTurno), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TurnoResponseDto> updateTurn(@PathVariable Long id, @Valid @RequestBody TurnoRequestDto requestDto) {
        Turno updateTurno = turnoService.update(id, requestDto);
        return ResponseEntity.ok(TurnoMapper.toDto(updateTurno));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteTurn(@PathVariable Long id) {
        turnoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
