package com.epam.cdp.m2.hw3.multithreading.task5;

import com.epam.cdp.m2.hw3.multithreading.task5.dao.UserDao;
import com.epam.cdp.m2.hw3.multithreading.task5.dao.UserDaoImpl;
import com.epam.cdp.m2.hw3.multithreading.task5.model.Currency;
import com.epam.cdp.m2.hw3.multithreading.task5.model.UserAccount;
import com.epam.cdp.m2.hw3.multithreading.task5.service.ExchangeService;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exchanger {
    public static void main(String[] args) throws InterruptedException {
        Exchanger exchanger = new Exchanger();
        exchanger.doExchange();
    }

    private final UserDao userDao;
    private final ExchangeService exchangeService;

    public Exchanger() {
        this.userDao = new UserDaoImpl();
        this.exchangeService = new ExchangeService();
    }

    public synchronized void doExchange() throws InterruptedException {
        Map<Currency, BigDecimal> currencyMap = new EnumMap<>(Currency.class);
        currencyMap.put(Currency.UAH, BigDecimal.valueOf(1000.0));
        currencyMap.put(Currency.USD, BigDecimal.valueOf(300.0));
        currencyMap.put(Currency.EUR, BigDecimal.valueOf(200.0));
        userDao.saveUser(new UserAccount("User1", currencyMap));

        currencyMap.put(Currency.UAH, BigDecimal.valueOf(555.0));
        currencyMap.put(Currency.USD, BigDecimal.valueOf(55.0));
        currencyMap.put(Currency.EUR, BigDecimal.valueOf(5.0));
        userDao.saveUser(new UserAccount("User2", currencyMap));

        Runnable runnable1 = () -> exchangeService.exchangeCurrency(
                "User1", BigDecimal.valueOf(20), Currency.UAH, Currency.EUR);

        Runnable runnable2 = () -> exchangeService.exchangeCurrency(
                "User2", BigDecimal.valueOf(50), Currency.UAH, Currency.USD);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(runnable1);
        executorService.submit(runnable2);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserAccount user1 = userDao.getUser("User1");
        System.out.println(user1.getCurrencyMap());

        UserAccount user2 = userDao.getUser("User2");
        System.out.println(user2.getCurrencyMap());
    }

}
