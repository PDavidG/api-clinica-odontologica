package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "odontologos")
public class Odontologo extends Persona{

    private String especialidad;

    @OneToMany(mappedBy = "odonto")
    private Set<Turno> listaTurnos = new HashSet<>();

    @OneToOne
    private Usuario user;

    @OneToOne
    private Horario horario;
}
