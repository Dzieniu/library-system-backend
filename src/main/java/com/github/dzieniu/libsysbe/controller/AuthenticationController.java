package com.github.dzieniu.libsysbe.controller;

import com.github.dzieniu.libsysbe.dto.UserToken;
import com.github.dzieniu.libsysbe.dto.mapper.UserTokenMapper;
import com.github.dzieniu.libsysbe.entity.User;
import com.github.dzieniu.libsysbe.repository.UserRepository;
import com.github.dzieniu.libsysbe.security.token.JwtTokenUtil;
import com.github.dzieniu.libsysbe.security.user.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/*
    http://localhost:8080/authenticate
*/

@RestController
@RequestMapping("authenticate")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    /*
        zwrócenie tokenu wraz z zalogowanym użytkownikiem
        przyklad: http://localhost:8080/authenticate/usertoken
        {
	        "username": "jacekp@gmail.com",
	        "password": "jacekp"
        }
     */
    @PostMapping("usertoken")
    public UserToken getAuthenticationTokenWithUser(@RequestBody UserCredentials userCredentials) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userCredentials.getUsername(),
                        userCredentials.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userRepository.findByEmail(userCredentials.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return UserTokenMapper.toDto(user, token);
    }
}