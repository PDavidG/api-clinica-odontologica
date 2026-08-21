package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.dto.ResponsableDto;

import java.util.List;

public interface IResponsableService {
    Responsable save(ResponsableDto resp);
    List<Responsable> findAll();
    Responsable findById(Long id);
    void deleteById(Long id);
    Responsable update(Long id, ResponsableDto resp);
}
