package istad.co.Homework.service.Impl;

import istad.co.Homework.domain.Role;
import istad.co.Homework.dto.Role.CreatedRoleRequest;
import istad.co.Homework.repository.RoleRepository;
import istad.co.Homework.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void addRole(CreatedRoleRequest createRole) {
        if(roleRepository.existsByName(createRole.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role already exists");
        }

        Role role = new Role();
        role.setName(createRole.name());
        // Let JPA generate the ID
        roleRepository.save(role);
    }
}