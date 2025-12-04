package istad.co.Homework.service;

import istad.co.Homework.dto.User.CreatedUserRequest;
import istad.co.Homework.dto.User.UpdatedUserRequest;
import istad.co.Homework.dto.User.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    UserResponse getById(String id);

    void createdUser(CreatedUserRequest created);

    void updatedUser(UpdatedUserRequest updated);

    void deletedUser(String id);

}
