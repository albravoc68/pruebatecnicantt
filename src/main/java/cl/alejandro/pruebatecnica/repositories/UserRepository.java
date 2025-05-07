package cl.alejandro.pruebatecnica.repositories;

import cl.alejandro.pruebatecnica.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

}
