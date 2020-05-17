package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.ReaderDto;
import com.github.dzieniu.libsysbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void signUp(@RequestBody ReaderDto readerDto){
        userService.signUp(readerDto);
    }
}
