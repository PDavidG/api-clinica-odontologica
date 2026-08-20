package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaDto {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(message = "El telefono no puede estar vacio")
    @Size(min = 10, message = "El telefono debe tener minimo diez digitos")
    private String telefono;

    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    @NotNull(message = "La fecha de nacimiento no puede ser nulo")
    private LocalDate fechaNacimiento;
}
