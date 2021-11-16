package service.impl;

import dao.UserDao;
import exception.UserNotFoundException;
import model.User;
import service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    //constructor injection
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(long userId) {
        return userDao.readUser(userId);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userDao.getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found by email " + email));
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userDao.getAllUsers()
                .stream()
                .filter(user -> user.getName().equals(name))
                .sorted(Comparator.comparing(User::getId))
                .skip(((long) pageSize * pageNum) - pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        User deletedUser = userDao.deleteUser(userId);
        return deletedUser != null;
    }
}
