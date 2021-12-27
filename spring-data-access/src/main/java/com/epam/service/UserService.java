package com.epam.service;

import com.epam.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(long userId);

    List<User> getAllUsers();

}
