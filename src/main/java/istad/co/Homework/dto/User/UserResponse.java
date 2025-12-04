package istad.co.Homework.dto.User;

import lombok.Builder;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Builder
public record UserResponse(

        String id,
        Long userId,
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String fullName,
        String phoneNumber,
        String gender,
        LocalDate dateOfBirth,
        String profilePicture,
        String convertImageUrl,

        boolean enabled,
        boolean emailVerified,

        List<String> roles,

        Instant createdAt,
        Instant updatedAt
) {


}
