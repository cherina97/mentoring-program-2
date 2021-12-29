package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookingFacade bookingFacade;

    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User("userName", "userByIdTest@mail.com");
        bookingFacade.createUser(user);

        mvc.perform(get("/users/getById/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserById(user.getId())));
    }

    @Test
    public void createUserTest() throws Exception {
        User user = new User("userName", "email@mail.com");

        mvc.perform(post("/users/new")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception {
        User presentUser = new User(1L, "userName", "update@mail.com");
        bookingFacade.createUser(presentUser);

        User userForUpdate = new User("userName", "update_new@mail.com");

        mvc.perform(post("/users/update")
                        .content(asJsonString(userForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        User user = new User("userName", "getUserByEmail@mail.com");
        bookingFacade.createUser(user);

        mvc.perform(get("/users/getByEmail/{email}", user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attribute("userModel", bookingFacade.getUserByEmail(user.getEmail())));
    }

    @Test
    public void getUsersByNameTest() throws Exception {
        String name = "userName";
        User user = new User("userName", "getUsersByName1@mail.com");
        User user2 = new User("userName", "getUsersByName2@mail.com");
        bookingFacade.createUser(user);
        bookingFacade.createUser(user2);

        mvc.perform(get("/users/{name}", name))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attribute("userModel", bookingFacade.getUsersByName(name)));
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User user = new User("userName", "getAllUsers1@mail.com");
        User user2 = new User("userName", "getAllUsers2@mail.com");
        bookingFacade.createUser(user);
        bookingFacade.createUser(user2);

        mvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"))
                .andExpect(model().attribute("userModel", bookingFacade.getAllUsers()));
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new User("userName", "deleteUserTest@mail.com");
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
