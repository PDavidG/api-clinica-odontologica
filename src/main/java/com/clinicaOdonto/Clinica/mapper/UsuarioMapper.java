package com.clinicaOdonto.Clinica.mapper;

import com.clinicaOdonto.Clinica.domain.Role;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.security.dto.UsuarioRequestDto;
import com.clinicaOdonto.Clinica.dto.UsuarioResponseDto;
import com.clinicaOdonto.Clinica.exception.ResourceNotFoundException;
import com.clinicaOdonto.Clinica.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final RoleRepository roleRepository;

    public static UsuarioResponseDto toDto(Usuario user) {

        if (user == null) {
            return null;
        }

        UsuarioResponseDto userDto = new UsuarioResponseDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public Usuario toEntity(UsuarioRequestDto requestDto) {

        if (requestDto == null) {
            return null;
        }

        Usuario user = new Usuario();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());

        user.setRoles(this.mapRolesStringsToRoles(requestDto.getRoles()));
        return user;
    }

    private Set<Role> mapRolesStringsToRoles(Set<String> roleNames) {

        if (roleNames == null || roleNames.isEmpty()) {
            return ifRolesNamesIsEmptyOrNullReturnSetRoles(roleNames);
        }

        return roleNames.stream()
                .map(roleName ->
                        roleRepository.findByName(roleName)
                                .orElseThrow(() ->
                                        new ResourceNotFoundException("Error: Rol no encontrado: " + roleName)
                                ))
                .collect(Collectors.toSet());
    }

    private Set<Role> ifRolesNamesIsEmptyOrNullReturnSetRoles(Set<String> roleNames) {
            return roleRepository.findByName("ROLE_USER")
                    .map(Collections::singleton)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Error: Rol 'ROLE_USER' no encontrado en la " +
                                    "base de datos. Asegúrese de que el ROLE_USER exista al iniciar " +
                                    "la aplicación"));

    }
}
