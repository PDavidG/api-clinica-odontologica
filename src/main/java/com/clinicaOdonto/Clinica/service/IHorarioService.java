package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;

import java.util.List;

public interface IHorarioService {
    Horario save(HorarioRequestDto requestDto);
    List<Horario> findAll();
    Horario findById(Long idHorario);
    void deleteById(Long idHorario);
    Horario update(Long id, HorarioRequestDto requestDto);
}
