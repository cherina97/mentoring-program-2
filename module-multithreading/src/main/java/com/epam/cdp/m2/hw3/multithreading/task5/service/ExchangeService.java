package com.epam.cdp.m2.hw3.multithreading.task5.service;

import com.epam.cdp.m2.hw3.multithreading.task5.dao.UserDaoImpl;
import com.epam.cdp.m2.hw3.multithreading.task5.model.Currency;
import com.epam.cdp.m2.hw3.multithreading.task5.model.UserAccount;
import com.epam.cdp.m2.hw3.multithreading.task5.utilities.UserNotFoundException;
import com.epam.cdp.m2.hw3.multithreading.task5.utilities.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class ExchangeService {

    private static final Logger LOG = Logger.getLogger(ExchangeService.class.getName());
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final UserDaoImpl userDao = new UserDaoImpl();

    public void saveUser(UserAccount userAccount) {
        userDao.saveUser(userAccount);
    }

    public Optional<UserAccount> getUserAccount(String login) {
        lock.readLock().lock();
        Optional<UserAccount> user = Optional.ofNullable(userDao.getUser(login));
        lock.readLock().unlock();

        return user;
    }

    public void exchangeCurrency(String login, BigDecimal amount, Currency from, Currency to) {
        lock.writeLock().lock();
        LOG.info("Start exchange operation for user " + login);
        Optional<UserAccount> userAccount = getUserAccount(login);

        //check if user is present
        if (!userAccount.isPresent()) {
            throw new UserNotFoundException("User with login " + login + " is not present");
        }

        //check if enough money
        if (checkIfEnoughMoneyForExchange(userAccount.get().getCurrencyMap(), amount, from)) {
            throw new NotEnoughMoneyException("Not enough money for exchange " + from.name() +
                    ", amount = " + amount + " for user " + userAccount.get().getLogin());
        }

        //exchange operation
        exchangeOperation(userAccount.get(), from, to, amount);

        lock.writeLock().unlock();
    }

    //example: from UAH to USD amount = 20.0
    private void exchangeOperation(UserAccount userAccount, Currency from, Currency to, BigDecimal amount) {
        BigDecimal result = amount
                .multiply(BigDecimal.valueOf(from.getRate()))
                .divide(BigDecimal.valueOf(to.getRate()), 2, RoundingMode.HALF_UP);
        userAccount.getCurrencyMap().put(to, result);

        BigDecimal newValueAfterExchange = new BigDecimal(
                String.valueOf(userAccount.getCurrencyMap().get(from).subtract(amount)));
        userAccount.getCurrencyMap().put(from, newValueAfterExchange);

        saveUser(userAccount);
        LOG.info("User: " + userAccount.getLogin() + " exchange " + from.name() + " to " + to.name() +
                " value amount: " + amount);
    }

    private boolean checkIfEnoughMoneyForExchange(Map<Currency, BigDecimal> currencyMap, BigDecimal amount, Currency from) {
        return currencyMap.get(from).compareTo(amount) < 0;
    }
}
