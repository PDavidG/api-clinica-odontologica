package com.clinicaOdonto.Clinica.controller;

import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.security.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.dto.UsuarioResponseDto;
import com.clinicaOdonto.Clinica.mapper.UsuarioMapper;
import com.clinicaOdonto.Clinica.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UsuarioResponseDto>> obtenerUsuarios() {
        List<UsuarioResponseDto> listaUsu = usuarioService.findAll()
                .stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsu);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UsuarioResponseDto> obtenerUsuario(@PathVariable long id) {
        Usuario user = usuarioService.findById(id);
        UsuarioResponseDto responseDto = UsuarioMapper.toDto(user);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDto> actualizarUsuario(@PathVariable long id, @Valid @RequestBody UsuarioRequestDto requestDto) {

        Usuario usuario = usuarioService.updateUser(id, requestDto);
        UsuarioResponseDto userResponseDto = UsuarioMapper.toDto(usuario);
        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
