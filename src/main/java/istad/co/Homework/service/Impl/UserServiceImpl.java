package istad.co.Homework.service.Impl;

import istad.co.Homework.domain.Role;
import istad.co.Homework.domain.User;
import istad.co.Homework.dto.User.CreatedUserRequest;
import istad.co.Homework.dto.User.UpdatedUserRequest;
import istad.co.Homework.dto.User.UserResponse;
import istad.co.Homework.repository.RoleRepository;
import istad.co.Homework.repository.UserRepository;
import istad.co.Homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserResponse> getAll() {
        return List.of();
    }

    @Override
    public UserResponse getById(String id) {
        return null;
    }

    @Override
    public void createdUser(CreatedUserRequest created) {
        // Check duplicates
        if (userRepository.exitsByUsername(created.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.exitsByEmail(created.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }
        List<Role> roleEntities = Optional.ofNullable(created.roles())
                .filter(roles -> !roles.isEmpty())
                .map(roles -> roles.stream()
                        .map(this::toFullRoleName)
                        .distinct()
                        .map(fullName -> roleRepository.findByName(fullName)
                                .orElseThrow(() -> new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Role not found: " + fullName
                                )))
                        .toList())
                .orElseGet(() -> {
                    // Default fallback
                    return List.of(roleRepository.findByName("ROLE_USER")
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.INTERNAL_SERVER_ERROR,
                                    "Default role ROLE_USER not found"
                            )));
                });
        // Generate UUID (if not using @PrePersist in entity)
        String uuid = UUID.randomUUID().toString();

        // Build user using Builder pattern
        User user = User.builder()
                .uuid(uuid)
                .username(created.username())
                .password(passwordEncoder.encode(created.password()))
                .email(created.email())
                .firstName(created.firstName())
                .lastName(created.lastName())
                .phoneNumber(created.phoneNumber())
                .gender(created.gender())
                .dob(created.dob())
                .provider("local")           // explicit for local accounts
                .roles(roleEntities)
                .enabled(true)
                .emailVerified(false)        // require email verification later
                .build();

        User savedUser = userRepository.save(user);

        // Map to response DTO
        UserResponse.builder()
                .id(savedUser.getUuid())
                .userId(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .fullName(savedUser.getGivenName() + " " + savedUser.getFamilyName())
                .phoneNumber(savedUser.getPhoneNumber())
                .gender(savedUser.getGender())
                .dateOfBirth(savedUser.getDob())
                .enabled(savedUser.isEnabled())
                .emailVerified(savedUser.getEmailVerified())
                .roles(savedUser.getRoles().stream()
                        .map(Role::getName)
                        .toList())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
    }

    @Override
    public void updatedUser(UpdatedUserRequest updated) {

    }

    @Override
    public void deletedUser(String id) {

    }


    private String toFullRoleName(String input) {
        if (input == null) return null;
        String trimmed = input.trim().toUpperCase();
        return trimmed.startsWith("ROLE_") ? trimmed : "ROLE_" + trimmed;
    }
}
