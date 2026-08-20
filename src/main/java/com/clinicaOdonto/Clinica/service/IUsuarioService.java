package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;

import java.util.List;

public interface IUsuarioService {
    Usuario saveUser(UsuarioRequestDto requestDto);
    List<Usuario> findAll();
    Usuario findById(long idUsuario);
    void deleteById(long idUsuario);
    Usuario updateUser(Long id, UsuarioRequestDto requestDto);
}
