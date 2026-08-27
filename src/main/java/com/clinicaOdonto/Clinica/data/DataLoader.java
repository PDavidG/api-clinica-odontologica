package com.clinicaOdonto.Clinica.data;

import com.clinicaOdonto.Clinica.domain.Role;
import com.clinicaOdonto.Clinica.domain.Usuario;
import com.clinicaOdonto.Clinica.repository.RoleRepository;
import com.clinicaOdonto.Clinica.repository.UsuarioRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UsuarioRespository usuarioRespository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRol = new Role();
                    newRol.setName("ROLE_USER");
                    return roleRepository.save(newRol);
                });

        if (usuarioRespository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));

            Set<Role> listRoles = new HashSet<>();
            listRoles.add(adminRole);
            listRoles.add(userRole);

            admin.setRoles(listRoles);
            usuarioRespository.save(admin);
            System.out.println("Usuario admin creado.");
        }

        if (usuarioRespository.findByUsername("user").isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123"));

            Set<Role> userRol = new HashSet<>();
            userRol.add(userRole);

            user.setRoles(userRol);
            usuarioRespository.save(user);
            System.out.println("Usuario user creado.");
        }


    }
}
