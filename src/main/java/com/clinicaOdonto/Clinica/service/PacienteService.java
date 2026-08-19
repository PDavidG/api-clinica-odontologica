package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Paciente;
import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.PacienteRepository;
import com.clinicaOdonto.Clinica.repository.ResponsableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService implements IPacienteService{

    private final PacienteRepository pacienteRepository;
    private final ResponsableRepository responsableRepository;

    @Override
    public Paciente save(Paciente pacient) {

        if (pacient.getResp() != null) {
            Responsable resp = responsableRepository.findById(pacient.getResp().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No se encontro al reponsable con el id: "+
                                    (pacient.getResp().getId())));

            pacient.setResp(resp);
        }

        return pacienteRepository.save(pacient);
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
    public Paciente update(Long id, Paciente pacient) {
        Paciente updatePaciente = pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No existe el paciente con el id: " + id));

        updatePaciente.setNombre(pacient.getNombre());
        updatePaciente.setApellido(pacient.getApellido());
        updatePaciente.setTelefono(pacient.getTelefono());
        updatePaciente.setDireccion(pacient.getDireccion());
        updatePaciente.setFechaNacimiento(pacient.getFechaNacimiento());
        updatePaciente.setTieneOs(pacient.isTieneOs());
        updatePaciente.setTipoSangre(pacient.getTipoSangre());

        if (pacient.getResp() != null) {
            Responsable resp = responsableRepository.findById(pacient.getResp().getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No se encontro el responsable con el id: " +
                                    pacient.getResp().getId()));
            updatePaciente.setResp(resp);
        }
        return pacienteRepository.save(updatePaciente);
    }
}
