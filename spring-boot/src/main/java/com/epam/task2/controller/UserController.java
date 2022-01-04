package com.epam.task2.controller;

import com.epam.task2.dto.UserDto;
import com.epam.task2.model.User;
import com.epam.task2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<HttpStatus> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDtoToUser(userDto));

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDtoToUser(userDto));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User userById = userService.getUserById(id);

        return ResponseEntity.ok(userById);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User userByEmail = userService.getUserByEmail(email);

        return ResponseEntity.ok(userByEmail);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        User deletedUser = userService.deleteUser(id);

        return ResponseEntity.ok(deletedUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    private User userDtoToUser(UserDto userDto) {
        return new User(userDto.getName(), userDto.getEmail());
    }
}
