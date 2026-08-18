package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Responsable;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.ResponsableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsableService implements IResponsableService{

    private final ResponsableRepository responsableRepository;

    @Override
    public Responsable save(Responsable resp) {
        return responsableRepository.save(resp);
    }

    @Override
    public List<Responsable> findAll() {
        return responsableRepository.findAll();
    }

    @Override
    public Responsable findById(Long id) {
        return responsableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro el responsable con el id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        responsableRepository.deleteById(id);
    }

    @Override
    public Responsable update(Long id, Responsable resp) {
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
