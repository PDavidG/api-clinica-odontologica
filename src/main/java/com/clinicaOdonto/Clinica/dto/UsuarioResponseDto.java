package com.clinicaOdonto.Clinica.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioResponseDto {
    private String username;
    private List<RoleDto> roleDtos;
}
