package com.epam.controller;

import com.epam.facade.BookingFacade;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/accounts")
public class UserAccountController {

    private final BookingFacade bookingFacade;
    public static final String TEMPLATE = "accountPage";


    public UserAccountController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<HttpStatus> createUserAccount(@RequestBody UserAccount userAccount) {
        bookingFacade.createUserAccount(userAccount);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ModelAndView getUserAccountById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("accountModel", bookingFacade.getUserAccountById(id));

        return modelAndView;
    }

    @GetMapping("/getByUserId/{userId}")
    public ModelAndView getUserAccountByUserId(@PathVariable long userId) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("accountModel", bookingFacade.getUserAccountByUserId(userId));

        return modelAndView;
    }

    @PostMapping("/topUp")
    public ModelAndView topUpAnUserAccount(@RequestBody UserAccount userAccount) {
        ModelAndView modelAndView = new ModelAndView(TEMPLATE);
        modelAndView.addObject("accountModel",
                bookingFacade.topUpUserAccount(userAccount.getUserId(), userAccount.getMoney()));

        return modelAndView;
    }
}
