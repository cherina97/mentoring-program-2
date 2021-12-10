package com.epam.dao;

import com.epam.model.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    User readUser(long id);

    User updateUser(long id, User user);

    User deleteUser(long id);

    List<User> getAllUsers();
}
