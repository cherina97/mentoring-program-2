//package com.epam.service.impl;
//
//import com.epam.dao.UserRepository;
//import com.epam.exception.GlobalApplicationException;
//import com.epam.exception.UserNotFoundException;
//import com.epam.model.User;
//import com.epam.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userDao;
//
//    public UserServiceImpl(UserRepository userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public User getUserById(long userId) {
//        log.info("getting user by id  " + userId);
//
//        return userDao.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User not found by id " + userId));
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        log.info("getting user by email " + email);
//
//        return userDao.findByEmail(email)
//                .orElseThrow(() -> new UserNotFoundException("User not found by email " + email));
//    }
//
//    @Override
//    public List<User> getUsersByName(String name) {
//        log.info("getting users by name " + name);
//        return userDao.findAllByName(name);
//    }
//
//    @Override
//    public User createUser(User user) {
//        log.info("creating user:  " + user);
//
//        if (userDao.findByEmail(user.getEmail()).isPresent()) {
//            log.error("Email is not unique");
//            throw new GlobalApplicationException("User with such email is already present");
//        }
//
//        return userDao.save(user);
//    }
//
//    @Override
//    public User updateUser(User user) {
//        log.info("updateUser " + user);
//
//        if (userDao.findByEmail(user.getEmail()).isPresent()) {
//            log.error("Email is not unique");
//            throw new GlobalApplicationException("User with such email is already present");
//        }
//
//        Optional<User> userById = userDao.findById(user.getId());
//        if (userById.isPresent()){
//            userById.get().setEmail(user.getEmail());
//            userById.get().setName(user.getName());
//
//            return userDao.save(userById.get());
//        } else {
//            throw new GlobalApplicationException("User updating error");
//        }
//    }
//
//    @Override
//    public void deleteUser(long userId) {
//        log.info("deleting user by id " + userId);
//
//        userDao.deleteById(userId);
//    }
//
//    public List<User> getAllUsers() {
//        log.info("getting all users");
//        return (List<User>) userDao.findAll();
//    }
//}
