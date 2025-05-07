package cl.alejandro.pruebatecnica.controller;

import cl.alejandro.pruebatecnica.dto.CreateUserRequest;
import cl.alejandro.pruebatecnica.dto.LoginUserRequest;
import cl.alejandro.pruebatecnica.dto.TokenResponse;
import cl.alejandro.pruebatecnica.dto.UserDTO;
import cl.alejandro.pruebatecnica.exception.HTTPException;
import cl.alejandro.pruebatecnica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserRequest createRequest) throws HTTPException {
        return new ResponseEntity<>(userService.createUser(createRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginUserRequest loginRequest) throws HTTPException {
        TokenResponse response = userService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/registeredEmails")
    @ResponseBody
    public ResponseEntity<List<String>> getAllRegisteredEmails() {
        return new ResponseEntity<>(userService.getRegisteredEmails(), HttpStatus.OK);
    }

}
