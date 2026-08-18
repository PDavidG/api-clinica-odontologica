package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}