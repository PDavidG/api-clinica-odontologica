package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.domain.Turno;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.OdontologoRepository;
import com.clinicaOdonto.Clinica.repository.PacienteRepository;
import com.clinicaOdonto.Clinica.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoService implements ITurnoService{

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;

    @Override
    public Turno save(Turno turno) {

        if (turno.getPacien() != null) {
            Paciente paciente = pacienteRepository.findById(turno.getPacien().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No existe el paciente con el id: " + turno.getPacien().getId()));

            turno.setPacien(paciente);
        }

        if (turno.getOdonto() != null) {
            Odontologo odonto = odontologoRepository.findById(turno.getOdonto().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No existe el odontologo con el turno: " + turno.getOdonto().getId()));

            turno.setOdonto(odonto);
        }

        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> findAll() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno findById(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro un turno con el id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Turno update(Long id, Turno turno) {
        Turno updateTurno = turnoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro el turno con el id: " + id));

        updateTurno.setFechaTurno(turno.getFechaTurno());
        updateTurno.setHoraTurno(turno.getHoraTurno());
        updateTurno.setAfeccion(turno.getAfeccion());

        if (turno.getPacien() != null) {
            Paciente paciente = pacienteRepository.findById(turno.getPacien().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No existe el paciente con el id: " + turno.getPacien().getId()));

            updateTurno.setPacien(paciente);
        }

        if (turno.getOdonto() != null) {
            Odontologo odonto = odontologoRepository.findById(turno.getOdonto().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No existe el odontologo con el turno: " + turno.getOdonto().getId()));

            updateTurno.setOdonto(odonto);
        }
        return updateTurno;
    }
}