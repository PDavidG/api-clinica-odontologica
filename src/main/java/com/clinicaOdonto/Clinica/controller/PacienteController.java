package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.dto.PacienteDto;
import com.clinicaOdonto.Clinica.mapper.PacienteMapper;
import com.clinicaOdonto.Clinica.service.IPacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteDto>> showPacients() {
        List<PacienteDto> listaPacientes = pacienteService.findAll()
                .stream()
                .map(PacienteMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaPacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> showPacient(@PathVariable Long id) {
        return ResponseEntity.ok(PacienteMapper.toDto(pacienteService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PacienteDto> createPacient(@Valid @RequestBody PacienteDto requestDto) {
        Paciente paciente = pacienteService.save(requestDto);
        return new ResponseEntity<>(PacienteMapper.toDto(paciente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> updatePacient(@PathVariable Long id, @Valid @RequestBody PacienteDto requestDto) {
        Paciente updatePaciente = pacienteService.update(id, requestDto);
        return ResponseEntity.ok(PacienteMapper.toDto(updatePaciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
