package com.epam.service;

import com.epam.model.UserAccount;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class UserAccountServiceImplTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void createUserAccountTest() {
        UserAccount userAccount = new UserAccount(1L, 3, BigDecimal.valueOf(100));
        Assertions.assertEquals(userAccount, userAccountService.createUserAccount(userAccount));
    }

    @Test
    public void getUserAccountByIdTest() {
        Assertions.assertEquals(1L, userAccountService.getUserAccountById(1L).getId());
    }

    @Test
    public void getUserAccountByUserIdTest() {
        Assertions.assertEquals(3L, userAccountService.getUserAccountByUserId(3L).getId());
    }

    @Test
    public void topUpUserAccountTest() {
        Assertions.assertEquals(BigDecimal.valueOf(200),
                userAccountService.topUpUserAccount(3L, BigDecimal.valueOf(100)).getMoney());
    }

    @Test
    public void withdrawMoneyFromAccountTest() {
        Assertions.assertEquals(BigDecimal.valueOf(50),
                userAccountService.withdrawMoneyFromAccount(6L, BigDecimal.valueOf(50)).getMoney());
    }

}
