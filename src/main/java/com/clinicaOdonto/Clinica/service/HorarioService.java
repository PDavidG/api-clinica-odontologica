package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.HorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioService implements IHorarioService{

    private final HorarioRepository horarioRepository;

    @Override
    public Horario save(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    @Override
    public Horario findById(Long idHorario) {
        return horarioRepository.findById(idHorario)
                .orElseThrow( () ->
                        new ResourceNotFoundException("No se encontro un horario con el id: " + idHorario));
    }

    @Override
    public void deleteById(Long idHorario) {
        horarioRepository.deleteById(idHorario);
    }

    @Override
    public Horario update(Long id, Horario horario) {

        Horario updateHora = horarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro un horario con el id: " + id));
        updateHora.setHorarioInicio(horario.getHorarioInicio());
        updateHora.setHorarioFin(horario.getHorarioFin());

        return horarioRepository.save(updateHora);
    }
}
