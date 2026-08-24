package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.dto.TurnoRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.TurnoMapper;
import com.clinicaOdonto.Clinica.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService implements ITurnoService{

    private final TurnoRepository turnoRepository;
    private final IPacienteService pacienteService;
    private final IOdontologoService odontologoService;

    @Override
    @Transactional
    public Turno save(TurnoRequestDto requestDto) {

        Turno turno = TurnoMapper.toEntity(requestDto);

        if (requestDto.getPacienteId() != null) {
            Paciente pacient = pacienteService.findById(requestDto.getPacienteId());
            turno.setPacien(pacient);
        }

        if (requestDto.getOdontologoId() != null) {
            Odontologo odon = odontologoService.findById(requestDto.getOdontologoId());
            turno.setOdonto(odon);
        }

        return turnoRepository.save(turno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Turno findById(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro un turno con el id: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Turno update(Long id, TurnoRequestDto requestDto) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro el turno con el id: " + id));

        Turno updateTurno = TurnoMapper.updateTurnoFromDto(turno, requestDto);

        if (requestDto.getPacienteId() != null) {
            Paciente pacient = pacienteService.findById(requestDto.getPacienteId());
            updateTurno.setPacien(pacient);
        }

        if (requestDto.getOdontologoId() != null) {
            Odontologo odonto = odontologoService.findById(requestDto.getOdontologoId());
            updateTurno.setOdonto(odonto);
        }
        turnoRepository.save(updateTurno);

        return updateTurno;
    }
}