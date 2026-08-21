package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResponsableDto extends PersonaDto {

    @NotBlank(message = "El tipo de responsable no debe estar vacio")
    private String tipoResp;
}