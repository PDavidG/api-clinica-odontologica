package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.dto.SecretarioDto;

import java.util.List;

public interface ISecretarioService {
    Secretario save(SecretarioDto secret);
    List<Secretario> findAll();
    Secretario findById(Long id);
    void deleteById(Long id);
    Secretario update(Long id, SecretarioDto secret);
}