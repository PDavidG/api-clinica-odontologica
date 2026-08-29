package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.dto.JwtResponseAuthDto;
import com.clinicaOdonto.Clinica.dto.LoginDto;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseAuthDto> authenticateUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new JwtResponseAuthDto(authService.authenticateUsuario(loginDto)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UsuarioRequestDto requestDto) {

        String respuesta = authService.registrarUsuario(requestDto);

        if (respuesta.startsWith("Usuario")) {
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
    }
}
