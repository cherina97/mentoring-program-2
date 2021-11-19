package service.impl;

import dao.UserDao;
import exception.UserNotFoundException;
import model.User;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        //todo fix to find max free id
        user.setId(4);
        userDao.createUser(user);

        return userDao.readUser(4);
    }

    @Override
    public User updateUser(long id, User user) {
        userDao.updateUser(id, user);

        return userDao.readUser(user.getId());
    }

    @Override
    public boolean deleteUser(long userId) {
        User deletedUser = userDao.deleteUser(userId);
        return deletedUser != null;
    }
}
