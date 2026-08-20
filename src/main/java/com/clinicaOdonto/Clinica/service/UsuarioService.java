package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.UsuarioMapper;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRespository usuarioRespository;

    @Override
    public Usuario saveUser(UsuarioRequestDto usuario) {
        Usuario user = UsuarioMapper.toEntity(usuario);
        return usuarioRespository.save(user);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRespository.findAll();
    }

    @Override
    public Usuario findById(long idUsuario) {
        return usuarioRespository.findById(idUsuario)
                .orElseThrow( () ->
                        new ResourceNotFoundException("No se encontro al usuario con el id: " + idUsuario));
    }

    @Override
    public void deleteById(long idUsuario) {
        usuarioRespository.deleteById(idUsuario);
    }

    @Override
    public Usuario updateUser(Long id, UsuarioRequestDto requestDto) {

        Usuario user = UsuarioMapper.toEntity(requestDto);

        Usuario updateUser = usuarioRespository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No se encontrol al usuario con el id: " + id));

        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setRol(user.getRol());

        return usuarioRespository.save(updateUser);
    }
}
