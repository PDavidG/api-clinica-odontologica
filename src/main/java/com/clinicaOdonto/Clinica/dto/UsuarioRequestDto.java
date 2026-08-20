package com.clinicaOdonto.Clinica.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    @NotBlank(message = "El username no puede estar vacio")
    private String username;

    @NotBlank(message = "El password no puede estar vacio")
    @Size(min= 6, max = 10, message = "La contraseña debe tener entre 6 y 10 caracteres")
    private String password;

    @NotBlank(message = "El rol no puede estar vacio")
    private String rol;
}
