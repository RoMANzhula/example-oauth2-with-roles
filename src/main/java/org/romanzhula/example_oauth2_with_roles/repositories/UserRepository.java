package org.romanzhula.example_oauth2_with_roles.repositories;

import org.romanzhula.example_oauth2_with_roles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
