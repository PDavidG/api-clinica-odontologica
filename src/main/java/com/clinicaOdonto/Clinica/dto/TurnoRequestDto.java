package com.clinicaOdonto.Clinica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoRequestDto {

    @NotNull(message = "La feha del turno no puede estar nulo")
    private LocalDate fechaTurno;

    @NotBlank(message = "La hora del turno no puede estar vacio")
    private String horaTurno;

    @NotBlank(message = "La afeccion no puede estar en blanco")
    private String afeccion;

    @NotNull(message = "El odontologo no puede ser nulo")
    private Long odontologoId;

    @NotNull(message = "El paciente no puede ser nulo")
    private Long pacienteId;
}