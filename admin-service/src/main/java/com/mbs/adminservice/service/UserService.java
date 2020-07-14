package com.mbs.adminservice.service;


import com.mbs.adminservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User addUser(User newUser);

    User updateUser(User updatedUser, Long userId);

    void deleteUserById(Long userId);
}
