package cl.alejandro.pruebatecnica.dto;

import lombok.Data;

@Data
public class LoginUserRequest {

    private String email;
    private String password;

}
