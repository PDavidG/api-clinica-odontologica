package com.clinicaOdonto.Clinica.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol;
}
