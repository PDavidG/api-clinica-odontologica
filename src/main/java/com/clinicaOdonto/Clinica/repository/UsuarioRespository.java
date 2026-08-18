package com.clinicaOdonto.Clinica.repository;

import com.clinicaOdonto.Clinica.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Long> {
}
