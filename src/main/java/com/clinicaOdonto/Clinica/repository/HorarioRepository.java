package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
}
