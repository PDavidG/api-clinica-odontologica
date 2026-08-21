package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.dto.ResponsableDto;

public interface ResponsableMapper {

    public static ResponsableDto toDto(Responsable resp) {

        if (resp == null) {
            return null;
        }

        ResponsableDto responDto = new ResponsableDto();
        responDto.setNombre(resp.getNombre());
        responDto.setApellido(resp.getApellido());
        responDto.setTelefono(resp.getTelefono());
        responDto.setDireccion(resp.getDireccion());
        responDto.setFechaNacimiento(resp.getFechaNacimiento());
        responDto.setTipoResp(resp.getTipoResp());

        return responDto;
    }

    public static Responsable toEntity(ResponsableDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Responsable resp = new Responsable();
        resp.setNombre(requestDto.getNombre());
        resp.setApellido(requestDto.getApellido());
        resp.setTelefono(requestDto.getTelefono());
        resp.setDireccion(requestDto.getDireccion());
        resp.setFechaNacimiento(requestDto.getFechaNacimiento());
        resp.setTipoResp(requestDto.getTipoResp());

        return resp;
    }
}
