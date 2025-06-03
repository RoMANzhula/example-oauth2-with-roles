package org.romanzhula.example_oauth2_with_roles.dto.requests;

import lombok.Data;


@Data
public class LoginRequest {

    private String email;
    private String password;

}
