package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.dto.ResponsableDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.ResponsableMapper;
import com.clinicaOdonto.Clinica.repository.ResponsableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsableService implements IResponsableService{

    private final ResponsableRepository responsableRepository;

    @Override
    @Transactional
    public Responsable save(ResponsableDto resp) {
        Responsable respon = ResponsableMapper.toEntity(resp);
        return responsableRepository.save(respon);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Responsable> findAll() {
        return responsableRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Responsable findById(Long id) {
        return responsableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro el responsable con el id: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        responsableRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Responsable update(Long id, ResponsableDto resp) {
        Responsable respons = responsableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro el responsable con el id: " + id));
        respons.setNombre(resp.getNombre());
        respons.setApellido(resp.getApellido());
        respons.setTelefono(resp.getTelefono());
        respons.setDireccion(resp.getDireccion());
        respons.setFechaNacimiento(resp.getFechaNacimiento());
        respons.setTipoResp(resp.getTipoResp());

        return responsableRepository.save(respons);
    }
}
