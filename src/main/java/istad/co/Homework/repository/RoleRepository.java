package istad.co.Homework.repository;

import istad.co.Homework.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean exitsByName(String name);

    Optional<Role> findByName(String roleName);
}
