package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Secretario;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.SecretarioRepository;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecretarioService implements ISecretarioService{

    private final SecretarioRepository secretarioRepository;
    private final UsuarioRespository usuarioRespository;

    @Override
    public Secretario save(Secretario secret) {

        if (secret.getUser() != null) {
            Usuario user = usuarioRespository.findById(secret.getUser().getIdUsuario())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No se encontro al usuario con el id: "+
                                    secret.getUser().getIdUsuario()));
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
    public Secretario update(Long id, Secretario secret) {

        Secretario updateSecret = secretarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontro al secretario con el id: "+ id));

        updateSecret.setNombre(secret.getNombre());
        updateSecret.setApellido(secret.getApellido());
        updateSecret.setTelefono(secret.getTelefono());
        updateSecret.setDireccion(secret.getDireccion());
        updateSecret.setFechaNacimiento(secret.getFechaNacimiento());
        updateSecret.setSector(secret.getSector());

        if (secret.getUser() != null) {
            Usuario user = usuarioRespository.findById(secret.getUser().getIdUsuario())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("No se encontro al usuario con el id: "+
                                    secret.getUser().getIdUsuario()));
            updateSecret.setUser(user);
        }

        return secretarioRepository.save(updateSecret);
    }
}