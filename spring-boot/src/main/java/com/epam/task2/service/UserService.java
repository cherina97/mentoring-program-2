package com.epam.task2.service;

import com.epam.task2.model.User;

import java.util.List;

public interface UserService {

    User getUserById(long userId);

    User getUserByEmail(String email);

    User createUser(User user);

    User updateUser(User user);

    User deleteUser(long userId);

    List<User> getAllUsers();

}
