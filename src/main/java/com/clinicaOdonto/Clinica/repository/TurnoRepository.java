package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}