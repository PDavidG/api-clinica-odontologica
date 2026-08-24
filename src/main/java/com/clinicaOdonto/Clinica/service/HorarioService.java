package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.dto.HorarioRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.HorarioMapper;
import com.clinicaOdonto.Clinica.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService implements IHorarioService{

    private final HorarioRepository horarioRepository;

    @Override
    @Transactional
    public Horario save(HorarioRequestDto horarioRequest) {
        Horario hora = HorarioMapper.toEntity(horarioRequest);
        return horarioRepository.save(hora);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Horario findById(Long idHorario) {
        return horarioRepository.findById(idHorario)
                .orElseThrow( () ->
                        new ResourceNotFoundException("No se encontro un horario con el id: " + idHorario));
    }

    @Override
    @Transactional
    public void deleteById(Long idHorario) {
        horarioRepository.deleteById(idHorario);
    }

    @Override
    @Transactional
    public Horario update(Long id, HorarioRequestDto horario) {

        Horario updateHora = horarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro un horario con el id: " + id));
        updateHora.setHorarioInicio(horario.getHorarioInicio());
        updateHora.setHorarioFin(horario.getHorarioFin());

        return horarioRepository.save(updateHora);
    }
}
