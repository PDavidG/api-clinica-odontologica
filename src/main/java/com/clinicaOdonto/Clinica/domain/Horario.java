package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @Column(nullable = false)
    private String horarioInicio;

    @Column(nullable = false)
    private String horarioFin;
}
