package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Turno;
import java.util.List;

public interface ITurnoService {
    Turno save(Turno turno);
    List<Turno> findAll();
    Turno findById(Long id);
    void deleteById(Long id);
    Turno update(Long id, Turno tuno);
}
