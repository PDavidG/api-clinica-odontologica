package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idTurno;

    @Column(nullable = false)
    private LocalDate fechaTurno;

    @Column(nullable = false)
    private String horaTurno;

    @Column(nullable = false)
    private String afeccion;

    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    private Odontologo odonto;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente pacien;
}
