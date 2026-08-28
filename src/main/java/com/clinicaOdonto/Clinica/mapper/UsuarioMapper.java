package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.dto.UsuarioResponseDto;

public interface UsuarioMapper {

    public static UsuarioResponseDto toDto(Usuario user) {

        if (user == null) {
            return null;
        }

        UsuarioResponseDto userDto = new UsuarioResponseDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public static Usuario toEntity(UsuarioRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Usuario user = new Usuario();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        return user;
    }
}
