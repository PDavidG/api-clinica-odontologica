package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.dto.UsuarioResponseDto;
import com.clinicaOdonto.Clinica.mapper.UsuarioMapper;
import com.clinicaOdonto.Clinica.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> guardarUsuario(@Valid @RequestBody UsuarioRequestDto requestDto) {
        Usuario user = usuarioService.saveUser(requestDto);
        UsuarioResponseDto userResponseDto = UsuarioMapper.toDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> actualizarUsuario(@PathVariable long id, @Valid @RequestBody UsuarioRequestDto requestDto) {

        Usuario usuario = usuarioService.updateUser(id, requestDto);
        UsuarioResponseDto userResponseDto = UsuarioMapper.toDto(usuario);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
