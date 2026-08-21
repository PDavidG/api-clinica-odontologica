package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "responsables")
public class Responsable extends Persona{

    @Column(nullable = false)
    private String tipoResp;
}
