package org.example.repositories;

import org.example.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT c.password FROM Client c WHERE c.email = :email")
    Optional<String> findPasswordByEmail(@Param("email") String email);
}
