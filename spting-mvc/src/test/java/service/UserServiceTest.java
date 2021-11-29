package service;

import exception.UserNotFoundException;
import model.User;
import model.impl.UserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import storage.Storage;

import static org.junit.Assert.*;

public class UserServiceTest {

    ApplicationContext context;
    UserService userService;
    User user;
    Storage storage;

    @Before
    public void setup() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = context.getBean(UserService.class);
        storage = context.getBean("storage", Storage.class);
        user = Mockito.mock(User.class);
    }

    @Test
    public void getUserByIdTest() {
        User userById = userService.getUserById(1);

        assertNotNull(userById);
        assertEquals(1, userById.getId());
    }

    @Test(expected = UserNotFoundException.class)
    public void getNotExistingUserByEmailTest() throws UserNotFoundException {
        User user = userService.getUserByEmail("wrong_email");

        assertNull(user);
    }

    @Test
    public void getUserByNameTest() {
        assertNotNull(userService.getUsersByName("Name1", 1, 1));
    }

    @Test
    public void createUserTest() throws UserNotFoundException {
        User user = new UserImpl("name", "email");

        Assert.assertEquals(user, userService.createUser(user));
    }

    @Test
    public void updateUserTest() {
        User updated = new UserImpl("update", "update");
        updated.setId(1L);

        assertEquals(updated, userService.updateUser(1L, updated));
    }

    @Test
    public void deleteUserTest() {
        Assert.assertTrue(userService.deleteUser(1));
    }
}
