package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.dto.ResponsableDto;
import com.clinicaOdonto.Clinica.mapper.ResponsableMapper;
import com.clinicaOdonto.Clinica.service.IResponsableService;
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
@RequestMapping("/api/v1/resp")
public class ResponsableController {

    private final IResponsableService responsableService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<ResponsableDto>> showResponsables() {
        List<ResponsableDto> listaResponsablesDtos = responsableService.findAll()
                                                    .stream()
                                                    .map(ResponsableMapper::toDto)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(listaResponsablesDtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ResponsableDto> showResponsable(@PathVariable Long id) {
        return ResponseEntity.ok(ResponsableMapper.toDto(responsableService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponsableDto> createResponsable(@Valid @RequestBody ResponsableDto resp) {
        Responsable respon = responsableService.save(resp);
        return new ResponseEntity<>(ResponsableMapper.toDto(respon), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponsableDto> updateResponsable(@PathVariable Long id, @Valid @RequestBody ResponsableDto resp) {
        Responsable respon = responsableService.update(id, resp);
        return ResponseEntity.ok(ResponsableMapper.toDto(respon));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteResponsable(@PathVariable Long id) {
        responsableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
