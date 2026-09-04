package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public Horario toEntity(HorarioRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Horario horario = new Horario();
        horario.setHorarioInicio(requestDto.getHorarioInicio());
        horario.setHorarioFin(requestDto.getHorarioFin());
        return horario;
    }
}