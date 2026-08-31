package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.dto.OdontologoRequestDto;
import com.clinicaOdonto.Clinica.dto.OdontologoResponseDto;
import com.clinicaOdonto.Clinica.mapper.OdontologoMapper;
import com.clinicaOdonto.Clinica.service.IOdontologoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/odontos")
@RequiredArgsConstructor
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<OdontologoResponseDto>> mostrarOdontologos() {
        List<OdontologoResponseDto> responseDtos = odontologoService.findAll()
                .stream()
                .map(OdontologoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<OdontologoResponseDto> mostrarOdontologo(@PathVariable Long id) {
        OdontologoResponseDto responseDto = OdontologoMapper.toDto(odontologoService.findById(id));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<OdontologoResponseDto> crearOdontologo(@Valid @RequestBody OdontologoRequestDto requestDto) {
        Odontologo odon = odontologoService.save(requestDto);
        OdontologoResponseDto responseDto = OdontologoMapper.toDto(odon);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<OdontologoResponseDto> actualizarOdontologo(@PathVariable Long id, @Valid @RequestBody OdontologoRequestDto requestDto) {
        Odontologo updateOdonto = odontologoService.update(id, requestDto);
        return ResponseEntity.ok(OdontologoMapper.toDto(updateOdonto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}