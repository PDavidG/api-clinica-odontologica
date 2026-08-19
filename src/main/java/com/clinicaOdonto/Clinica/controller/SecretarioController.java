package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.service.ISecretarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/secrets")
public class SecretarioController {

    private final ISecretarioService secretarioService;

    @GetMapping
    public ResponseEntity<List<Secretario>> showSecretarios() {
        return ResponseEntity.ok(secretarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Secretario> showSecretario(@PathVariable Long id) {
        return ResponseEntity.ok(secretarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Secretario> createSecretario(@RequestBody Secretario secret) {
        Secretario savedSecret = secretarioService.save(secret);
        return new ResponseEntity<>(savedSecret, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Secretario> updateSecretario(@PathVariable Long id, @RequestBody Secretario secret) {
        Secretario updatedSecret = secretarioService.update(id, secret);
        return ResponseEntity.ok(updatedSecret);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecretario(@PathVariable Long id) {
        secretarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}