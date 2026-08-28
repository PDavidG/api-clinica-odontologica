package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Role;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.JwtResponseAuthDto;
import com.clinicaOdonto.Clinica.dto.LoginDto;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.mapper.UsuarioMapper;
import com.clinicaOdonto.Clinica.repository.RoleRepository;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import com.clinicaOdonto.Clinica.security.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UsuarioRespository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseAuthDto> authenticateUser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponseAuthDto(token));

    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UsuarioRequestDto requestDto) {
        if(usuarioRepository.existsByUsername(requestDto.getUsername())) {
            return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        }

        Usuario user = UsuarioMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() ->
                        new ResourceNotFoundException("Error, no existe el rol de usuario: ROLE_USER"));

        user.setRoles(Collections.singleton(roles));

        usuarioRepository.save(user);

        return new ResponseEntity<>("Usuario registrado correctamente...", HttpStatus.CREATED);
    }
}
