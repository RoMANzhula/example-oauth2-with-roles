package org.romanzhula.example_oauth2_with_roles.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.romanzhula.example_oauth2_with_roles.dto.RegistrationRequest;
import org.romanzhula.example_oauth2_with_roles.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegistrationRequest registrationRequest
    ) {
        registrationService.registerNewUser(registrationRequest);
        return ResponseEntity.ok("User registered successfully");
    }

}
