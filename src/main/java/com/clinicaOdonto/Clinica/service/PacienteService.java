package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.dto.PacienteDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.PacienteMapper;
import com.clinicaOdonto.Clinica.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService implements IPacienteService{

    private final PacienteRepository pacienteRepository;
    private final IResponsableService responsableService;

    @Override
    public Paciente save(PacienteDto requestDto) {

        System.out.println("El paciente es: "+ requestDto.toString());

        Paciente paciente = PacienteMapper.toEntity(requestDto);

        if (requestDto.getResponsableId() != null) {
            Responsable resp = responsableService.findById(requestDto.getResponsableId());
            paciente.setResp(resp);
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe el paciente con el id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente update(Long id, PacienteDto requestDto) {
        Paciente updatePaciente = pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe el paciente con el id: " + id));

        Paciente updatePacien = PacienteMapper.updatePacienteFromDto(updatePaciente, requestDto);

        if (requestDto.getResponsableId() != null) {
            Responsable resp = responsableService.findById(requestDto.getResponsableId());
            updatePacien.setResp(resp);
        }
        return pacienteRepository.save(updatePaciente);
    }
}
