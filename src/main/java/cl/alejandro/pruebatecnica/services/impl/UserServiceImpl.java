package cl.alejandro.pruebatecnica.services.impl;

import cl.alejandro.pruebatecnica.config.UserConfig;
import cl.alejandro.pruebatecnica.config.security.JWTAuthtenticationConfig;
import cl.alejandro.pruebatecnica.dto.*;
import cl.alejandro.pruebatecnica.exception.HTTPException;
import cl.alejandro.pruebatecnica.entities.PhoneEntity;
import cl.alejandro.pruebatecnica.entities.UserEntity;
import cl.alejandro.pruebatecnica.repositories.PhoneRepository;
import cl.alejandro.pruebatecnica.repositories.UserRepository;
import cl.alejandro.pruebatecnica.services.UserService;
import cl.alejandro.pruebatecnica.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private UserConfig userConfig;

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtentication;

    @Override
    public UserDTO createUser(CreateUserRequest request) throws HTTPException {
        ValidationUtil.validateCreateUserRequest(request, userConfig.getPasswordPattern());
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Email ya se encuentra registrado.");
        }

        List<PhoneEntity> phones = null;
        if (request.getPhones() != null && !request.getPhones().isEmpty()) {
            phones = request.getPhones().stream().map(PhoneEntity::new).collect(Collectors.toList());
        }

        Date now = new Date();
        UserEntity newUser = new UserEntity();
        newUser.setCreationDatetime(now);
        newUser.setModifiedDatetime(now);
        newUser.setLastLogin(now);
        newUser.setIsActive(true);
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());

        userRepository.save(newUser);
        if (phones != null && !phones.isEmpty()) {
            phones.forEach(p -> p.setUser(newUser));
            phoneRepository.saveAll(phones);
        }

        String token = jwtAuthtentication.getJWTToken(newUser.getEmail());
        return newUser.toDTO(request.getPhones(), token);
    }

    @Override
    public TokenResponse login(LoginUserRequest request) throws HTTPException {
        if (!StringUtils.hasText(request.getEmail()) || !StringUtils.hasText(request.getPassword())) {
            throw new HTTPException(HttpStatus.BAD_REQUEST, "Debe ingresar Email y Password.");
        }
        if (!userRepository.existsByEmailAndPassword(request.getEmail(), request.getPassword())) {
            throw new HTTPException(HttpStatus.UNAUTHORIZED, "Email y/o password incorrectos.");
        }

        return new TokenResponse(request.getEmail(), jwtAuthtentication.getJWTToken(request.getEmail()));
    }

    @Override
    public List<String> getRegisteredEmails() {
        List<UserEntity> entities = userRepository.findAll();
        return entities.stream().map(UserEntity::getEmail).collect(Collectors.toList());
    }

}
