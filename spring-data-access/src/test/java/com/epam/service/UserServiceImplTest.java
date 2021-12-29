package com.epam.service;

import com.epam.model.User;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@NoArgsConstructor
public class UserServiceImplTest {

    @Autowired
    private UserService userService;


    @Test
    public void getUserByIdTest() {
        Assertions.assertEquals(3L, userService.getUserById(3L).getId());
    }

    @Test
    public void getUserByEmailTest() {
        Assertions.assertEquals("user3@mail.com", userService.getUserByEmail("user3@mail.com").getEmail());
    }

    @Test
    public void createUserTest() {
        User user = new User("user", "email22");
        Assertions.assertEquals(user, userService.createUser(user));
    }

    @Test
    public void updateUserTest() {
        User userById = userService.getUserById(6L);
        userById.setEmail("update");

        Assertions.assertEquals(userById, userService.updateUser(userById));
    }

    @Test
    public void deleteUserTest() {
        int size = userService.getAllUsers().size();
        userService.deleteUser(14);
        Assertions.assertEquals(3, size - 1);
    }
}
