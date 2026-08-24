package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Horario;
import com.clinicaOdonto.Clinica.domain.Odontologo;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.OdontologoRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.OdontologoMapper;
import com.clinicaOdonto.Clinica.repository.OdontologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OdontologoService implements IOdontologoService{

    private final OdontologoRepository odontologoRepository;
    private final IUsuarioService usuarioService;
    private final IHorarioService horarioService;

    @Override
    @Transactional
    public Odontologo save(OdontologoRequestDto requestDto) {

        Odontologo odontologo = OdontologoMapper.toEntity(requestDto);

        Usuario user = usuarioService.findById(requestDto.getUserId());
        Horario hora = horarioService.findById(requestDto.getHorarioId());

        odontologo.setUser(user);
        odontologo.setHorario(hora);
        return odontologoRepository.save(odontologo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Odontologo> findAll() {
        return odontologoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Odontologo findById(Long id) {
        return odontologoRepository.findById(id)
                .orElseThrow(  () ->
                    new ResourceNotFoundException("No se encontro al odontologo con el id: " + id)
                );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Odontologo update(Long id, OdontologoRequestDto requestDto) {
        Odontologo updateOdonto = odontologoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro al odontologo con el id: " + id));

        updateOdonto.setNombre(requestDto.getNombre());
        updateOdonto.setApellido(requestDto.getApellido());
        updateOdonto.setTelefono(requestDto.getTelefono());
        updateOdonto.setDireccion(requestDto.getDireccion());
        updateOdonto.setFechaNacimiento(requestDto.getFechaNacimiento());
        updateOdonto.setEspecialidad(requestDto.getEspecialidad());

        if (!updateOdonto.getUser().getIdUsuario().equals(requestDto.getUserId())) {
            Usuario user = usuarioService.findById(requestDto.getUserId());
            updateOdonto.setUser(user);
        }

        if (!updateOdonto.getHorario().getIdHorario().equals(requestDto.getHorarioId())) {
            Horario hora = horarioService.findById(requestDto.getHorarioId());
            updateOdonto.setHorario(hora);
        }

        return odontologoRepository.save(updateOdonto);
    }
}