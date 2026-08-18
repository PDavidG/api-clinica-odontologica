package com.clinicaOdonto.Clinica.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "secretarios")
public class Secretario extends Persona{

    private String sector;

    @OneToOne
    private Usuario user;
}
