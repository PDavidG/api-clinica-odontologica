package com.clinicaOdonto.Clinica.security.service;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.security.dto.LoginDto;
import com.clinicaOdonto.Clinica.security.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.mapper.UsuarioMapper;
import com.clinicaOdonto.Clinica.repository.RoleRepository;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import com.clinicaOdonto.Clinica.security.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UsuarioRespository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;


    public String authenticateUsuario(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtGenerator.generateToken(authentication);
    }

    public String registrarUsuario(UsuarioRequestDto requestDto) {
        if(usuarioRepository.existsByUsername(requestDto.getUsername())) {
            return "El nombre de usaurio ya existe";
        }

        Usuario user = usuarioMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        usuarioRepository.save(user);

        return "Usuario registrado correctamente...";
    }
}
