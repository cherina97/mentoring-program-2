package service.impl;

import dao.UserDao;
import exception.UserNotFoundException;
import model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);
    private final UserDao userDao;

    //constructor injection
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(long userId) {
        LOGGER.info("getUserById " + userId);
        return userDao.readUser(userId);
    }

    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        LOGGER.info("getUserByEmail " + email);
        return userDao.getAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found by email " + email));
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        LOGGER.info("getUsersByName " + name);
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
        LOGGER.info("creating user  " + user);

        user.setId(userDao.getAllUsers().stream()
                .max(Comparator.comparing(User::getId))
                .map(User::getId)
                .orElse(0L) + 1L);

        userDao.createUser(user);
        LOGGER.info("user was created  " + user);
        return userDao.readUser(user.getId());
    }

    @Override
    public User updateUser(long id, User user) {
        LOGGER.info("updateUser " + user);
        userDao.updateUser(id, user);
        LOGGER.info("user was updated " + user);
        return userDao.readUser(user.getId());
    }

    @Override
    public boolean deleteUser(long userId) {
        LOGGER.info("delete User " + userId);
        User deletedUser = userDao.deleteUser(userId);
        LOGGER.info("delete User " + userId);
        return deletedUser != null;
    }
}
