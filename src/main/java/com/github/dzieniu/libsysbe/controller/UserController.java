package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.ReaderDto;
import com.github.dzieniu.libsysbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    http://localhost:8080/users
*/

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
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
