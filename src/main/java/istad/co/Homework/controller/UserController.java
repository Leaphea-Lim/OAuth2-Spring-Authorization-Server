package istad.co.Homework.controller;

import istad.co.Homework.dto.User.CreatedUserRequest;
import istad.co.Homework.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //todo: Creates a new user based on the submitted request data
    @PostMapping()
    public ResponseEntity<String> addUser(@Valid @RequestBody CreatedUserRequest createdUser) {
        userService.createdUser(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

}