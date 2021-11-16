package dao;

import model.User;

import java.util.List;

public interface UserDao {

    User createUser(User user);

    User readUser(long id);

    User updateUser(User user);

    User deleteUser(long id);

    List<User> getAllUsers();
}
