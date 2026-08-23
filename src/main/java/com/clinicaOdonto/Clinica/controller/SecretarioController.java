package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.dto.SecretarioDto;
import com.clinicaOdonto.Clinica.mapper.SecretarioMapper;
import com.clinicaOdonto.Clinica.service.ISecretarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/secrets")
public class SecretarioController {

    private final ISecretarioService secretarioService;

    @GetMapping
    public ResponseEntity<List<SecretarioDto>> showSecretarios() {
        List<SecretarioDto> listaSecretarios = secretarioService.findAll()
                .stream()
                .map(SecretarioMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaSecretarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretarioDto> showSecretario(@PathVariable Long id) {
        Secretario secret = secretarioService.findById(id);
        return ResponseEntity.ok(SecretarioMapper.toDto(secret));
    }

    @PostMapping
    public ResponseEntity<SecretarioDto> createSecretario(@Valid @RequestBody SecretarioDto requestDto) {
        Secretario savedSecret = secretarioService.save(requestDto);
        return new ResponseEntity<>(SecretarioMapper.toDto(savedSecret), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecretarioDto> updateSecretario(@PathVariable Long id, @Valid @RequestBody SecretarioDto requestDto) {
        Secretario updatedSecret = secretarioService.update(id, requestDto);
        return ResponseEntity.ok(SecretarioMapper.toDto(updatedSecret));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecretario(@PathVariable Long id) {
        secretarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}