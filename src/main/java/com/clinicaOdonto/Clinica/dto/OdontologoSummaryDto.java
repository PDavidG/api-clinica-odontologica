package com.clinicaOdonto.Clinica.dto;

import lombok.Data;

@Data
public class OdontologoSummaryDto {

    private String nombre;
    private String apellido;
    private String especialidad;
    private String horarioInicio;
    private String horarioFin;
}
