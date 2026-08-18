package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "odontologos")
public class Odontologo extends Persona{

    private String especialidad;

    @OneToMany(mappedBy = "odonto")
    private List<Turno> listaTurnos;

    @OneToOne
    private Usuario user;

    @OneToOne
    private Horario horario;
}
