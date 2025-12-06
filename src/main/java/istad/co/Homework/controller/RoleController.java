package istad.co.Homework.controller;

import istad.co.Homework.dto.Role.CreatedRoleRequest;
import istad.co.Homework.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    //todo: Creates a new role using the provided request data
    @PostMapping()
    public ResponseEntity<String> addRole(@RequestBody CreatedRoleRequest createdRoleRequest) {
        roleService.addRole(createdRoleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Role created successfully");
    }
}