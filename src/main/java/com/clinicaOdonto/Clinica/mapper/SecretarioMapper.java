package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.dto.SecretarioDto;

public interface SecretarioMapper {

    public static SecretarioDto toDto(Secretario secret) {

        if (secret == null) {
            return null;
        }

        SecretarioDto responseDto = new SecretarioDto();
        responseDto.setNombre(secret.getNombre());
        responseDto.setApellido(secret.getApellido());
        responseDto.setTelefono(secret.getTelefono());
        responseDto.setDireccion(secret.getDireccion());
        responseDto.setFechaNacimiento(secret.getFechaNacimiento());
        responseDto.setSector(secret.getSector());
        responseDto.setUserId(secret.getUser().getIdUsuario());
        return responseDto;
    }

    public static Secretario toEntity(SecretarioDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Secretario secret = new Secretario();
        secret.setNombre(requestDto.getNombre());
        secret.setApellido(requestDto.getApellido());
        secret.setTelefono(requestDto.getTelefono());
        secret.setDireccion(requestDto.getDireccion());
        secret.setFechaNacimiento(requestDto.getFechaNacimiento());
        secret.setSector(requestDto.getSector());

        return secret;
    }

    public static Secretario updateSecretarioFromDto(Secretario secret, SecretarioDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        secret.setNombre(requestDto.getNombre());
        secret.setApellido(requestDto.getApellido());
        secret.setTelefono(requestDto.getTelefono());
        secret.setDireccion(requestDto.getDireccion());
        secret.setFechaNacimiento(requestDto.getFechaNacimiento());
        secret.setSector(requestDto.getSector());

        return secret;
    }
}
