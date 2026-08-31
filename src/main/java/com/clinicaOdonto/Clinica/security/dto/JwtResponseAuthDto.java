package com.clinicaOdonto.Clinica.security.dto;

import lombok.Data;

@Data
public class JwtResponseAuthDto {

    private String accessToken;
    private String tokenType = "Bearer ";

    public JwtResponseAuthDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
