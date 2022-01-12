package com.epam.service.impl;

import com.epam.exception.GlobalApplicationException;
import com.epam.exception.UserAccountNotFoundException;
import com.epam.model.UserAccount;
import com.epam.repository.UserAccountRepository;
import com.epam.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        log.info("creating UserAccount");

        if (userAccountRepository.findByUserId(userAccount.getUserId()).isPresent()) {
            throw new GlobalApplicationException("UserAccount with for this user is already present");
        }
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount getUserAccountById(long id) {
        log.info("getting UserAccount by id " + id);

        return userAccountRepository.findById(id)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount not found by id " + id));
    }

    @Override
    public UserAccount getUserAccountByUserId(long userId) {
        log.info("getting UserAccount by user id " + userId);

        return userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UserAccountNotFoundException("UserAccount not found by user id " + userId));
    }

    @Transactional
    @Override
    public void topUpUserAccount(long userId, BigDecimal putMoney) {
        log.info("top up account for user id " + userId);

        if (putMoney.intValue() > 0) {
            throw new GlobalApplicationException("Put money less or equal 0");
        }
        UserAccount userAccount = getUserAccountByUserId(userId);
        userAccount.setMoney(userAccount.getMoney().add(putMoney));
    }

    @Transactional
    @Override
    public void withdrawMoneyFromAccount(long userId, BigDecimal getMoney) {
        log.info("withdraw money from account for user id " + userId);

        UserAccount userAccount = getUserAccountByUserId(userId);

        if (!(userAccount.getMoney().compareTo(getMoney) > 0)) {
            throw new GlobalApplicationException("Not enough money for booking ticket");
        } else {
            userAccount.setMoney(userAccount.getMoney().subtract(getMoney));
        }
    }
}
