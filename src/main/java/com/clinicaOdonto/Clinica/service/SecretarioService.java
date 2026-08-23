package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.SecretarioDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.SecretarioMapper;
import com.clinicaOdonto.Clinica.repository.SecretarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretarioService implements ISecretarioService{

    private final SecretarioRepository secretarioRepository;
    private final IUsuarioService usuarioService;

    @Override
    public Secretario save(SecretarioDto requesDto) {

        Secretario secret = SecretarioMapper.toEntity(requesDto);

        if (requesDto.getUserId() != null) {
            Usuario user = usuarioService.findById(requesDto.getUserId());
            secret.setUser(user);
        }

        return secretarioRepository.save(secret);
    }

    @Override
    public List<Secretario> findAll() {
        return secretarioRepository.findAll();
    }

    @Override
    public Secretario findById(Long id) {
        return secretarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro al secretario con el id: "+ id));
    }

    @Override
    public void deleteById(Long id) {
        secretarioRepository.deleteById(id);
    }

    @Override
    public Secretario update(Long id, SecretarioDto requestDto) {

        Secretario secret = secretarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro al secretario con el id: "+ id));

        Secretario updateSecret = SecretarioMapper.updateSecretarioFromDto(secret, requestDto);

        if (requestDto.getUserId() != null) {
            Usuario user = usuarioService.findById(requestDto.getUserId());
            updateSecret.setUser(user);
        }

        return secretarioRepository.save(updateSecret);
    }
}