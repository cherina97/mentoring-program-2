package com.epam.dao.impl;

import com.epam.dao.UserDao;
import com.epam.model.User;
import org.springframework.stereotype.Repository;
import com.epam.storage.Storage;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final Storage storage;

    public UserDaoImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public User createUser(User user) {
        return storage.getUsers().put(user.getId(), user);
    }

    @Override
    public User readUser(long id) {
        return storage.getUsers().get(id);
    }

    @Override
    public User updateUser(long id, User user) {
        return storage.getUsers().replace(id, user);
    }

    @Override
    public User deleteUser(long id) {
        return storage.getUsers().remove(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.getUsers().values());
    }

}
