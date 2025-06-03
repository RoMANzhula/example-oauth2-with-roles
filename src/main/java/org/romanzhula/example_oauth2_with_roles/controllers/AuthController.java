package org.romanzhula.example_oauth2_with_roles.controllers;

import lombok.RequiredArgsConstructor;
import org.romanzhula.example_oauth2_with_roles.dto.requests.LoginRequest;
import org.romanzhula.example_oauth2_with_roles.dto.responses.LoginResponse;
import org.romanzhula.example_oauth2_with_roles.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = authService.login(loginRequest);

            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(401)
                    .body(new LoginResponse(e.getMessage(), loginRequest.getEmail()))
            ;
        }
    }

}
