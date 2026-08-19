package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Secretario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretarioRepository extends JpaRepository<Secretario, Long> {
}