package com.epam.integration;

import com.epam.config.WebConfiguration;
import com.epam.controller.UserController;
import com.epam.exception.GlobalApplicationException;
import com.epam.exception.UserNotFoundException;
import com.epam.facade.BookingFacade;
import com.epam.model.User;
import com.epam.model.impl.UserImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfiguration.class)
@WebAppConfiguration
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingFacade bookingFacade;

    @Test
    public void getUserByIdTest() throws Exception {
        User user = new UserImpl("userName", "userByIdTest@mail.com");
        bookingFacade.createUser(user);

        mvc.perform(get("/users/getById/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserById(user.getId())));
    }

    @Test
    public void getUserByIdFailTest() throws Exception {
        String expectedMassage = "User not found by id 2";

        mvc.perform(get("/users/getById/{id}", 2L))
                .andExpect(status().isNotFound())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof UserNotFoundException))
                .andExpect(result ->
                        assertEquals(expectedMassage, Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void createUserTest() throws Exception {
        User user = new UserImpl("userName", "email@mail.com");

        mvc.perform(post("/users/new")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createUserFailTest() throws Exception {
        User presentUser = new UserImpl(1L, "userName", "equalEmail@mail.com");
        bookingFacade.createUser(presentUser);

        User user = new UserImpl("newUser", "equalEmail@mail.com");

        mvc.perform(post("/users/new")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof GlobalApplicationException));
    }

    @Test
    public void updateUserTest() throws Exception {
        User presentUser = new UserImpl(1L, "userName", "update@mail.com");
        bookingFacade.createUser(presentUser);

        User userForUpdate = new UserImpl("userName", "update_new@mail.com");

        mvc.perform(post("/users/update/{id}", userForUpdate.getId())
                        .content(asJsonString(userForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        User user = new UserImpl("userName", "getUserByEmail@mail.com");
        bookingFacade.createUser(user);

        mvc.perform(get("/users/getByEmail/{email}", user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserByEmail(user.getEmail())));
    }

    @Test
    public void getUsersByNameTest() throws Exception {
        String name = "userName";
        User user = new UserImpl("userName", "getUsersByName1@mail.com");
        User user2 = new UserImpl("userName", "getUsersByName2@mail.com");
        bookingFacade.createUser(user);
        bookingFacade.createUser(user2);

        mvc.perform(get("/users/{name}", name))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getUsersByName(name, 10, 1)));
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User user = new UserImpl("userName", "getAllUsers1@mail.com");
        User user2 = new UserImpl("userName", "getAllUsers2@mail.com");
        bookingFacade.createUser(user);
        bookingFacade.createUser(user2);

        mvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attributeExists("userModel"))
                .andExpect(model().attribute("userModel", bookingFacade.getAllUsers()));
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new UserImpl("userName", "deleteUserTest@mail.com");
        bookingFacade.createUser(user);

        mvc.perform(delete("/users/delete/{id}", user.getId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final User user) {
        try {
            return new ObjectMapper().writeValueAsString(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
