package dao.impl;

import dao.UserDao;
import model.User;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {

    private Storage storage;

    @Override
    public User createUser(User user) {
        return storage.getUsers().put(user.getId(), user);
    }

    @Override
    public User readUser(long id) {
        return storage.getUsers().get(id);
    }

    @Override
    public User updateUser(User user) {
        return storage.getUsers().replace(user.getId(), user);
    }

    @Override
    public User deleteUser(long id) {
        return storage.getUsers().remove(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(storage.getUsers().values());
    }

    //setter injection in xml
    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
