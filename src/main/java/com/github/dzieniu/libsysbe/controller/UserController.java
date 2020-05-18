package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.ReaderDto;
import com.github.dzieniu.libsysbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    http://localhost:8080/users
*/

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        rejestracja uzytkownika
        przyklad: http://localhost:8080/users
        {
            "email":"dawidxdzien@gmail.com",
            "password":"madafaka",
            "firstName":"Dawid",
            "lastName":"Dzien"
        }
     */
    @PostMapping
    public void signUp(@RequestBody ReaderDto readerDto){
        userService.signUp(readerDto);
    }
}
