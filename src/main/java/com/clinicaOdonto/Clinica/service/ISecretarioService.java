package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Secretario;
import java.util.List;

public interface ISecretarioService {
    Secretario save(Secretario secret);
    List<Secretario> findAll();
    Secretario findById(Long id);
    void deleteById(Long id);
    Secretario update(Long id, Secretario secret);
}