package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;
}
