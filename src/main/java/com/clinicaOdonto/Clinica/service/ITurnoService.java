package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.dto.TurnoRequestDto;

import java.util.List;

public interface ITurnoService {
    Turno save(TurnoRequestDto turno);
    List<Turno> findAll();
    Turno findById(Long id);
    void deleteById(Long id);
    Turno update(Long id, TurnoRequestDto tuno);
}
