package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.HorarioRepository;
import com.clinicaOdonto.Clinica.repository.OdontologoRepository;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OdontologoService implements IOdontologoService{

    private final OdontologoRepository odontologoRepository;
    private final UsuarioRespository usuarioRespository;
    private final HorarioRepository horarioRepository;

    @Override
    public Odontologo save(Odontologo odonto) {

        Usuario user = usuarioRespository.findById(odonto.getUser().getIdUsuario())
                .orElseThrow(() ->
                        new ResourceNotFoundException("no existe el id: " + odonto.getUser().getIdUsuario()));

        Horario hora = horarioRepository.findById(odonto.getHorario().getIdHorario())
                .orElseThrow(() ->
                        new ResourceNotFoundException("no existe el horario con el id: "+ odonto.getHorario().getIdHorario()));

        odonto.setUser(user);
        odonto.setHorario(hora);
        return odontologoRepository.save(odonto);
    }

    @Override
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo findById(Long id) {
        return odontologoRepository.findById(id)
                .orElseThrow(  () ->
                    new ResourceNotFoundException("No se encontro al odontologo con el id: " + id)
                );
    }

    @Override
    public void deleteById(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public Odontologo update(Long id, Odontologo odonto) {
        Odontologo updateOdonto = odontologoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro al odontologo con el id: " + id));

        updateOdonto.setNombre(odonto.getNombre());
        updateOdonto.setApellido(odonto.getApellido());
        updateOdonto.setTelefono(odonto.getTelefono());
        updateOdonto.setDireccion(odonto.getDireccion());
        updateOdonto.setFechaNacimiento(odonto.getFechaNacimiento());
        updateOdonto.setEspecialidad(odonto.getEspecialidad());
        updateOdonto.setHorario(odonto.getHorario());
        updateOdonto.setUser(odonto.getUser());

        return odontologoRepository.save(updateOdonto);
    }
}