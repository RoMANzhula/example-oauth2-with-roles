package org.romanzhula.example_oauth2_with_roles.repositories;

import org.romanzhula.example_oauth2_with_roles.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String client);

}
