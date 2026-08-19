package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Paciente;
import java.util.List;

public interface IPacienteService {
    Paciente save(Paciente pacient);
    List<Paciente> findAll();
    Paciente findById(Long id);
    void deleteById(Long id);
    Paciente update(Long id, Paciente pacient);
}
