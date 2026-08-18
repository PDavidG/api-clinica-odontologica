package com.clinicaOdonto.Clinica.service;

import com.clinicaOdonto.Clinica.domain.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario saveUser(Usuario usuario);
    List<Usuario> findAll();
    Usuario findById(long idUsuario);
    void deleteById(long idUsuario);
    Usuario updateUser(Long id, Usuario user);
}
