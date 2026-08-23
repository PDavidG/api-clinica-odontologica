package com.clinicaOdonto.Clinica.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TurnoResponseDto {

    private LocalDate fechaTurno;
    private String horaTurno;
    private String afeccion;
    private OdontologoSummaryDto odontologoDto;
    private PacienteSummaryDto pacienteDto;
}