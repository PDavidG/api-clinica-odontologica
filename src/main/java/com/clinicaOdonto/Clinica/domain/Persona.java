package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

}
