package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Responsable;

import java.util.List;

public interface IResponsableService {
    Responsable save(Responsable resp);
    List<Responsable> findAll();
    Responsable findById(Long id);
    void deleteById(Long id);
    Responsable update(Long id, Responsable resp);
}
