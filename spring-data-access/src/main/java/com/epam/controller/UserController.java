package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final BookingFacade bookingFacade;
    public static final String TEMPLATE = "userPage";

    public UserController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        bookingFacade.createUser(user);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        bookingFacade.updateUser(user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ModelAndView getUserById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("userModel", bookingFacade.getUserById(id));

        return modelAndView;
    }

    @GetMapping("/getByEmail/{email}")
    public ModelAndView getUserByEmail(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("userModel", bookingFacade.getUserByEmail(email));

        return modelAndView;
    }

    @GetMapping("/{name}")
    public ModelAndView getUsersByName(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("userModel", bookingFacade.getUsersByName(name));

        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        bookingFacade.deleteUser(id);
        modelAndView.addObject("userModel", "User is deleted by id = " + id);

        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("userModel", bookingFacade.getAllUsers());

        return modelAndView;
    }
}
