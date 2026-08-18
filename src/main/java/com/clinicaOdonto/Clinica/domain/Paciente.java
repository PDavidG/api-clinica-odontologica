package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pacientes")
public class Paciente extends Persona{

    @Column(nullable = false)
    private boolean tieneOs;

    @Column(nullable = false)
    private String tipoSangre;

    @OneToMany(mappedBy = "pacien")
    private List<Turno> listaTurnos;

    @OneToOne
    private Responsable resp;
}
