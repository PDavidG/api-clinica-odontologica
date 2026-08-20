package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OdontologoRequestDto extends PersonaDto{

    @NotBlank(message = "La especialidad no puede estar vacia")
    private String especialidad;

    @NotNull(message = "El id del usuario es obligatorio no puede estar nulo")
    private Long userId;

    @NotNull(message = "El id del horario es obligatorio no puede estar nulo")
    private Long horarioId;

}