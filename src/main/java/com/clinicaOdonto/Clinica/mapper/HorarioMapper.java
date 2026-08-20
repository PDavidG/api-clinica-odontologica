package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;

public interface HorarioMapper {

    public static Horario toEntity(HorarioRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Horario horario = new Horario();
        horario.setHorarioInicio(requestDto.getHorarioInicio());
        horario.setHorarioFin(requestDto.getHorarioFin());
        return horario;
    }
}