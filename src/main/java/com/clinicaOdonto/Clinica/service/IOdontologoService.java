package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.dto.OdontologoRequestDto;
import java.util.List;

public interface IOdontologoService {
    Odontologo save(OdontologoRequestDto odonto);
    List<Odontologo> findAll();
    Odontologo findById(Long id);
    void deleteById(Long id);
    Odontologo update(Long id, OdontologoRequestDto odonto);
}
