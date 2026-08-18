package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.service.IResponsableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resp")
public class ResponsableController {

    private final IResponsableService responsableService;

    @GetMapping
    public ResponseEntity<List<Responsable>> showResponsables() {
        return ResponseEntity.ok(responsableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsable> showResponsable(@PathVariable Long id) {
        return ResponseEntity.ok(responsableService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Responsable> createResponsable(@RequestBody Responsable resp) {
        return new ResponseEntity<>(responsableService.save(resp), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsable> updateResponsable(@PathVariable Long id, @RequestBody Responsable resp) {
        Responsable respon = responsableService.update(id, resp);
        return ResponseEntity.ok(respon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponsable(@PathVariable Long id) {
        responsableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
