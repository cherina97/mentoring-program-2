package com.epam.service.impl;

import com.epam.exception.GlobalApplicationException;
import com.epam.exception.UserAccountNotFoundException;
import com.epam.exception.UserNotFoundException;
import com.epam.model.UserAccount;
import com.epam.repository.UserAccountRepository;
import com.epam.repository.UserRepository;
import com.epam.service.UserAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

        if (userAccountRepository.findByUserId(userAccount.getUserId()).isPresent()){
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

    @Override
    public UserAccount topUpUserAccount(long userId, BigDecimal putMoney) {
        log.info("top up account for user id " + userId);

        Optional<UserAccount> userAccountById = userAccountRepository.findByUserId(userId);

        if (userAccountById.isPresent() && putMoney.intValue() > 0) {
            userAccountById.get().setMoney(userAccountById.get().getMoney().add(putMoney));

            userAccountRepository.save(userAccountById.get());
            return userAccountById.get();
        } else {
            throw new GlobalApplicationException("UserAccount not found by id " + userId + "or put money < 0");
        }
    }

    @Override
    public UserAccount withdrawMoneyFromAccount(long userId, BigDecimal getMoney) {
        log.info("withdraw money from account for user id " + userId);

        Optional<UserAccount> userAccountById = userAccountRepository.findByUserId(userId);

        if (userAccountById.isPresent() && getMoney.intValue() > 0 && checkEnoughMoneyForWithdraw(userAccountById.get(), getMoney)) {
            userAccountById.get().setMoney(userAccountById.get().getMoney().subtract(getMoney));

            userAccountRepository.save(userAccountById.get());
            return userAccountById.get();
        } else {
            throw new GlobalApplicationException("UserAccount not found by id " + userId + " or not enough money for booking ticket");
        }
    }

    private boolean checkEnoughMoneyForWithdraw(UserAccount userAccount, BigDecimal getMoney) {
        return userAccount.getMoney().compareTo(getMoney) > 0;
    }
}
