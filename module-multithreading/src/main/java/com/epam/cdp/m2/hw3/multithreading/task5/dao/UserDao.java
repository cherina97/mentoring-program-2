package com.epam.cdp.m2.hw3.multithreading.task5.dao;

import com.epam.cdp.m2.hw3.multithreading.task5.model.UserAccount;

public interface UserDao {

    void saveUser(UserAccount userAccount);

    UserAccount getUser(String login);
}
