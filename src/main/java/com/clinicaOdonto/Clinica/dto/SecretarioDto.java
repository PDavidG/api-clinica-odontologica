package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SecretarioDto extends PersonaDto{

    @NotBlank(message = "El campo sector no puede estar vacio")
    private String sector;

    @NotNull(message = "El id del usuario no puede estar nulo")
    private Long userId;
}