package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
}
