package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserAccountController.class)
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingFacade bookingFacade;

    private UserAccount userAccount;

    @Before
    public void setUp() {
        userAccount = new UserAccount(1L, 3L, BigDecimal.valueOf(100));
        bookingFacade.createUserAccount(userAccount);
    }

    @Test
    public void createUserAccountTest() throws Exception {
        mvc.perform(post("/accounts/new")
                        .content(asJsonString(userAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserAccountByIdTest() throws Exception {
        mvc.perform(get("/accounts/getById/{id}", userAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("accountPage"))
                .andExpect(model().attribute("accountModel", bookingFacade.getUserAccountById(userAccount.getId())));
    }

    @Test
    public void getUserAccountByUserIdTest() throws Exception {
        mvc.perform(get("/accounts/getByUserId/{userId}", userAccount.getUserId()))
                .andExpect(status().isOk())
                .andExpect(view().name("accountPage"))
                .andExpect(model().attribute("accountModel", bookingFacade.getUserAccountByUserId(userAccount.getUserId())));
    }

    @Test
    public void topUpAnUserAccountTest() throws Exception {
        mvc.perform(post("/accounts/topUp")
                        .content(asJsonString(userAccount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final UserAccount userAccount) {
        try {
            return new ObjectMapper().writeValueAsString(userAccount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
