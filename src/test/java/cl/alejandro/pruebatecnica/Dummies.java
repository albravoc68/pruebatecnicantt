package cl.alejandro.pruebatecnica;

import cl.alejandro.pruebatecnica.dto.CreateUserRequest;
import cl.alejandro.pruebatecnica.dto.PhoneDTO;

import java.util.Collections;

public class Dummies {

    public static CreateUserRequest buildCreateUserRequestDummy() {
        CreateUserRequest dummy = new CreateUserRequest();
        dummy.setEmail("asd@asd.asd");
        dummy.setName("Dummy");
        dummy.setPassword("asdasd");
        dummy.setPhones(Collections.singletonList(buildPhoneDTODummy()));

        return dummy;
    }

    public static PhoneDTO buildPhoneDTODummy() {
        PhoneDTO dummy = new PhoneDTO();
        dummy.setCitycode("01");
        dummy.setContrycode("01");
        dummy.setNumber("1234567");

        return dummy;
    }

}
