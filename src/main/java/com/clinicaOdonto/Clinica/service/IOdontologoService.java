package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import java.util.List;

public interface IOdontologoService {
    Odontologo save(Odontologo odonto);
    List<Odontologo> findAll();
    Odontologo findById(Long id);
    void deleteById(Long id);
    Odontologo update(Long id, Odontologo odonto);
}
