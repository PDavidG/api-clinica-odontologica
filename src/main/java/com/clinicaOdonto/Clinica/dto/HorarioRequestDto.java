package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HorarioRequestDto {

    @NotBlank(message = "El horario de inicio no debe estar vacio")
    private String horarioInicio;

    @NotBlank(message = "El horario de fin no debe estar vacio")
    private String horarioFin;
}
