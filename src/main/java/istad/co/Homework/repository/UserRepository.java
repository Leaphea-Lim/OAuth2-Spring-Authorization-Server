package istad.co.Homework.repository;

import istad.co.Homework.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean exitsByUsername(String username);

    Optional<User> findByUsername(String username);

    boolean exitsByEmail(@NotBlank(message = "Email is Require")
        @Email(message = "Invalid Email Format") String email);

}
