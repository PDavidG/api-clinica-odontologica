package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.dto.OdontologoRequestDto;
import com.clinicaOdonto.Clinica.dto.OdontologoResponseDto;

public interface OdontologoMapper {

    public static OdontologoResponseDto toDto(Odontologo odonto) {

        if (odonto == null) {
            return null;
        }

        OdontologoResponseDto odontoDto = new OdontologoResponseDto();
        odontoDto.setNombre(odonto.getNombre());
        odontoDto.setApellido(odonto.getApellido());
        odontoDto.setTelefono(odonto.getTelefono());
        odontoDto.setDireccion(odonto.getDireccion());
        odontoDto.setFechaNacimiento(odonto.getFechaNacimiento());
        odontoDto.setEspecialidad(odonto.getEspecialidad());

        odontoDto.setUser(UsuarioMapper.toDto(odonto.getUser()));
        odontoDto.setHorarioInicio(odonto.getHorario().getHorarioInicio());
        odontoDto.setHorarioFin(odonto.getHorario().getHorarioFin());
        return odontoDto;
    }

    public static Odontologo toEntity(OdontologoRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Odontologo odonto = new Odontologo();
        odonto.setNombre(requestDto.getNombre());
        odonto.setApellido(requestDto.getApellido());
        odonto.setTelefono(requestDto.getTelefono());
        odonto.setDireccion(requestDto.getDireccion());
        odonto.setFechaNacimiento(requestDto.getFechaNacimiento());
        odonto.setEspecialidad(requestDto.getEspecialidad());

        return odonto;
    }
}
