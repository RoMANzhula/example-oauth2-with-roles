package org.romanzhula.example_oauth2_with_roles.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.romanzhula.example_oauth2_with_roles.dto.requests.RegistrationRequest;
import org.romanzhula.example_oauth2_with_roles.models.Role;
import org.romanzhula.example_oauth2_with_roles.models.User;
import org.romanzhula.example_oauth2_with_roles.repositories.RoleRepository;
import org.romanzhula.example_oauth2_with_roles.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@Slf4j
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public void registerNewUser(RegistrationRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            log.error("Passwords do not match!");
            throw new IllegalArgumentException("Passwords do not match!");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email already in use!");
            throw new IllegalArgumentException("Email already in use!");
        }

        Role defaultRole = roleRepository.findByName("ROLE_CLIENT")
                .orElseThrow(() -> new RuntimeException("Default role CLIENT not found!"))
        ;

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(defaultRole))
                .build()
        ;

        userRepository.save(user);
    }

}
