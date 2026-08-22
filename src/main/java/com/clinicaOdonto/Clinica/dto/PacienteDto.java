package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PacienteDto extends PersonaDto{

    @NotNull(message = "Debe indicar si el paciente tiene obra social no puede ser nulo")
    private boolean tieneOs;

    @NotBlank(message = "El tipo de sangre no puede esta vario")
    private String tipoSangre;

    private Long responsableId;
}
