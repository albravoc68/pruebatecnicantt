package cl.alejandro.pruebatecnica.services;

import cl.alejandro.pruebatecnica.dto.CreateUserRequest;
import cl.alejandro.pruebatecnica.dto.LoginUserRequest;
import cl.alejandro.pruebatecnica.dto.TokenResponse;
import cl.alejandro.pruebatecnica.dto.UserDTO;
import cl.alejandro.pruebatecnica.exception.HTTPException;

import java.util.List;

public interface UserService {

    UserDTO createUser(CreateUserRequest request) throws HTTPException;

    TokenResponse login(LoginUserRequest request) throws HTTPException;

    List<String> getRegisteredEmails();

}
