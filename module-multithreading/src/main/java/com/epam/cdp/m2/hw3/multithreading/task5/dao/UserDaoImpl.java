package com.epam.cdp.m2.hw3.multithreading.task5.dao;

import com.epam.cdp.m2.hw3.multithreading.task5.model.Currency;
import com.epam.cdp.m2.hw3.multithreading.task5.model.UserAccount;
import com.epam.cdp.m2.hw3.multithreading.task5.utilities.UserNotFoundException;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public void saveUser(UserAccount userAccount) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(userAccount.getLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        userAccount.getCurrencyMap().forEach((key, value) -> printWriter.print(key + " " + value + " "));
        LOG.info("Save user " + userAccount.getLogin());
        printWriter.close();
    }

    @Override
    public UserAccount getUser(String login) {
        LOG.info("Getting user " + login);

        Map<Currency, BigDecimal> currencyMap = new HashMap<>();
        try {
            FileReader file = new FileReader(login);
            Scanner sc = new Scanner(file);
            sc.useDelimiter(" ");

            while (sc.hasNext()) {
                currencyMap.put(Currency.valueOf(sc.next()), new BigDecimal(sc.next()));
            }
        } catch (FileNotFoundException e) {
            LOG.warning("Error while reading user");
            throw new UserNotFoundException(e.getMessage());
        }

        return new UserAccount(login, currencyMap);
    }
}
