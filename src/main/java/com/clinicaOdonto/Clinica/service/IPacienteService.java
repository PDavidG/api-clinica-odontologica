package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.dto.PacienteDto;
import java.util.List;

public interface IPacienteService {
    Paciente save(PacienteDto pacient);
    List<Paciente> findAll();
    Paciente findById(Long id);
    void deleteById(Long id);
    Paciente update(Long id, PacienteDto pacient);
}
