package org.romanzhula.example_oauth2_with_roles.services;

import lombok.RequiredArgsConstructor;
import org.romanzhula.example_oauth2_with_roles.dto.requests.LoginRequest;
import org.romanzhula.example_oauth2_with_roles.dto.responses.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;


    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            return new LoginResponse("Login successful", request.getEmail());

        } catch (AuthenticationException e) {
            throw new RuntimeException("Login failed: Invalid email or password");
        }
    }

}
