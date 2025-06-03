package org.romanzhula.example_oauth2_with_roles.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private String email;

}
