package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.dto.PacienteDto;
import com.clinicaOdonto.Clinica.dto.PacienteSummaryDto;

public interface PacienteMapper {

    public static PacienteDto toDto(Paciente paciente) {

        if (paciente == null) {
            return null;
        }

        PacienteDto pacienteDto = new PacienteDto();
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setTelefono(paciente.getTelefono());
        pacienteDto.setDireccion(paciente.getDireccion());
        pacienteDto.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteDto.setTieneOs(paciente.isTieneOs());
        pacienteDto.setTipoSangre(paciente.getTipoSangre());
        if (paciente.getResp() != null) {
            pacienteDto.setResponsableId(paciente.getResp().getId());
        }

        return pacienteDto;
    }

    public static Paciente toEntity(PacienteDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(requestDto.getNombre());
        paciente.setApellido(requestDto.getApellido());
        paciente.setTelefono(requestDto.getTelefono());
        paciente.setDireccion(requestDto.getDireccion());
        paciente.setFechaNacimiento(requestDto.getFechaNacimiento());
        paciente.setTieneOs(requestDto.isTieneOs());
        paciente.setTipoSangre(requestDto.getTipoSangre());

        return paciente;
    }

    public static Paciente updatePacienteFromDto(Paciente paciente, PacienteDto requestDto) {

        if (paciente == null) {
            return null;
        }

        paciente.setNombre(requestDto.getNombre());
        paciente.setApellido(requestDto.getApellido());
        paciente.setTelefono(requestDto.getTelefono());
        paciente.setDireccion(requestDto.getDireccion());
        paciente.setFechaNacimiento(requestDto.getFechaNacimiento());
        paciente.setTieneOs(requestDto.isTieneOs());
        paciente.setTipoSangre(requestDto.getTipoSangre());

        return paciente;
    }

    public static PacienteSummaryDto toSummaryDto(Paciente paciente) {

        if (paciente == null) {
            return null;
        }

        PacienteSummaryDto pacient = new PacienteSummaryDto();
        pacient.setNombre(paciente.getNombre());
        pacient.setApellido(paciente.getApellido());

        return pacient;
    }
}