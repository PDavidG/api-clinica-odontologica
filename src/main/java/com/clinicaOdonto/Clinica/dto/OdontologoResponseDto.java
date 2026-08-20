package com.clinicaOdonto.Clinica.dto;

import com.clinicaOdonto.Clinica.domain.Turno;
import lombok.Data;
import java.util.Set;

@Data
public class OdontologoResponseDto extends PersonaDto{

    private String especialidad;
    private UsuarioResponseDto user;
    private String horarioInicio;
    private String horarioFin;
    private Set<Turno> listaTurnos;
}
