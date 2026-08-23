package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.dto.TurnoRequestDto;
import com.clinicaOdonto.Clinica.dto.TurnoResponseDto;

public interface TurnoMapper {

    public static TurnoResponseDto toDto(Turno turno) {

        if (turno == null) {
            return  null;
        }

        TurnoResponseDto turnoDto = new TurnoResponseDto();
        turnoDto.setFechaTurno(turno.getFechaTurno());
        turnoDto.setHoraTurno(turno.getHoraTurno());
        turnoDto.setAfeccion(turno.getAfeccion());
        turnoDto.setOdontologoDto(OdontologoMapper.toSummaryDto(turno.getOdonto()));
        turnoDto.setPacienteDto(PacienteMapper.toSummaryDto(turno.getPacien()));
        return turnoDto;
    }

    public static Turno toEntity(TurnoRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Turno turn = new Turno();
        turn.setFechaTurno(requestDto.getFechaTurno());
        turn.setHoraTurno(requestDto.getHoraTurno());
        turn.setAfeccion(requestDto.getAfeccion());

        return turn;
    }

    public static Turno updateTurnoFromDto(Turno turno, TurnoRequestDto requestDto) {

        if (turno == null) {
            return null;
        }
        turno.setFechaTurno(requestDto.getFechaTurno());
        turno.setHoraTurno(requestDto.getHoraTurno());
        turno.setAfeccion(requestDto.getAfeccion());

        return turno;
    }

}