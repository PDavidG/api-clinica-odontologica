package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;

import java.util.List;

public interface IHorarioService {
    Horario save(Horario horario);
    List<Horario> findAll();
    Horario findById(Long idHorario);
    void deleteById(Long idHorario);
    Horario update(Long id, Horario horario);
}
